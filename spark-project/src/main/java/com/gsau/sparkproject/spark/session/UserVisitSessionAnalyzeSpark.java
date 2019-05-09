package com.gsau.sparkproject.spark.session;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.spark.Accumulator;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFlatMapFunction;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.hive.HiveContext;
import org.apache.spark.storage.StorageLevel;

import scala.Tuple2;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Optional;
import com.gsau.sparkproject.conf.ConfigurationManager;
import com.gsau.sparkproject.constant.Constants;
import com.gsau.sparkproject.dao.ISessionAggrStatDAO;
import com.gsau.sparkproject.dao.ISessionDetailDAO;
import com.gsau.sparkproject.dao.ISessionRandomExtractDAO;
import com.gsau.sparkproject.dao.ITaskDAO;
import com.gsau.sparkproject.dao.ITop10CategoryDAO;
import com.gsau.sparkproject.dao.ITop10SessionDAO;
import com.gsau.sparkproject.dao.factory.DAOFactory;
import com.gsau.sparkproject.domain.SessionAggrStat;
import com.gsau.sparkproject.domain.SessionDetail;
import com.gsau.sparkproject.domain.SessionRandomExtract;
import com.gsau.sparkproject.domain.Task;
import com.gsau.sparkproject.domain.Top10Category;
import com.gsau.sparkproject.domain.Top10Session;
import com.gsau.sparkproject.test.MockData;
import com.gsau.sparkproject.util.DateUtils;
import com.gsau.sparkproject.util.NumberUtils;
import com.gsau.sparkproject.util.ParamUtils;
import com.gsau.sparkproject.util.SparkUtils;
import com.gsau.sparkproject.util.StringUtils;
import com.gsau.sparkproject.util.ValidUtils;

/**
 * 用户访问session分析Spark作业
 * <p>
 * 接收用户创建的分析任务，用户可能指定的条件如下：
 * <p>
 * 1、时间范围：起始日期~结束日期
 * 2、性别：男或女
 * 3、年龄范围
 * 4、职业：多选
 * 5、城市：多选
 * 6、搜索词：多个搜索词，只要某个session中的任何一个action搜索过指定的关键词，那么session就符合条件
 * 7、点击品类：多个品类，只要某个session中的任何一个action点击过某个品类，那么session就符合条件
 * <p>
 * 我们的spark作业如何接受用户创建的任务？
 * <p>
 * J2EE平台在接收用户创建任务的请求之后，会将任务信息插入MySQL的task表中，任务参数以JSON格式封装在task_param
 * 字段中
 * <p>
 * 接着J2EE平台会执行我们的spark-submit shell脚本，并将taskid作为参数传递给spark-submit shell脚本
 * spark-submit shell脚本，在执行时，是可以接收参数的，并且会将接收的参数，传递给Spark作业的main函数
 * 参数就封装在main函数的args数组中
 * <p>
 * 这是spark本身提供的特性
 *
 * @author Administrator
 */
@SuppressWarnings("unused")
public class UserVisitSessionAnalyzeSpark {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf()                                                        // 构建Spark上下文
                .setAppName(Constants.SPARK_APP_NAME_SESSION)
//				.set("spark.default.parallelism", "100")                                        //task的并行度(只能在没有使用spark sql的stage中生效)
                .set("spark.storage.memoryFraction", "0.5")                                     //设置缓存操作的内存占比
                .set("spark.shuffle.consolidateFiles", "true")                                  //是否在shuffle的时候，在map端开启合并小文件的操作
                .set("spark.shuffle.file.buffer", "64")                                         //reduce端的缓存（默认是32《k》）
                .set("spark.shuffle.memoryFraction", "0.3")                                     //设置reduce端聚合内存的比例（因为reduce是一边从map端拉取文件，一边聚合）
                .set("spark.reducer.maxSizeInFlight", "24")                                     //设置reduce task 每次拉取的数据量（每次拉24M）
                .set("spark.shuffle.io.maxRetries", "60")                                       //reduce task read端向wirte端读数据的时候，如果网络异常要重试的次数（60次）
                .set("spark.shuffle.io.retryWait", "60")                                        //每次shuffle拉数据的重试时间间隔（1分钟）
                .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")          //序列化，使用Kryoservializer
                .registerKryoClasses(new Class[]{
                        CategorySortKey.class,
                        IntList.class});
        SparkUtils.setMaster(conf);                                                               //设置master的类型
        JavaSparkContext sc = new JavaSparkContext(conf);                                         //构建JavaSparkContext
//		sc.checkpointFile("hdfs://");                                                             //checkpoint，就是说，会将RDD的数据，持久化一份到容错的文件系统上（比如hdfs),这个是用性能换稳定的操作
        SQLContext sqlContext = getSQLContext(sc.sc());                                           //构建SqlContext

        SparkUtils.mockData(sc, sqlContext);                                                       //如果是本地模式，那么就生成模拟数据，如果是集群模式那么就不用生成模拟数据
        ITaskDAO taskDAO = DAOFactory.getTaskDAO();                                                // 创建需要使用的DAO组件
        long taskid = ParamUtils.getTaskIdFromArgs(args, Constants.SPARK_LOCAL_TASKID_SESSION);    // 首先得查询出来指定的任务，并获取任务的查询参数
        Task task = taskDAO.findById(taskid);                                                      //获取用户指定的分析任务
        if (task == null) {
            System.out.println(new Date() + ": cannot find this task with id [" + taskid + "].");
            return;
        }

        JSONObject taskParam = JSONObject.parseObject(task.getTaskParam());

        JavaRDD<Row> actionRDD = SparkUtils.getActionRDDByDateRange(sqlContext, taskParam);                             //指定日期范围内的用户行为数据RDD
        JavaPairRDD<String, Row> sessionid2actionRDD = getSessionid2ActionRDD(actionRDD);                               //转化<row>=><sessionID,row>
        sessionid2actionRDD = sessionid2actionRDD.persist(StorageLevel.MEMORY_ONLY());                                  //对RDD<sessionID,row>，进行持久化
//		sessionid2actionRDD.checkpoint();

        JavaPairRDD<String, String> sessionid2AggrInfoRDD = aggregateBySession(sc, sqlContext, sessionid2actionRDD);     //（<sessionid,(sessionid,searchKeywords,clickCategoryIds,age,professional,city,sex)>）
        Accumulator<String> sessionAggrStatAccumulator = sc.accumulator("", new SessionAggrStatAccumulator());// 重构，同时进行过滤和统计

        JavaPairRDD<String, String> filteredSessionid2AggrInfoRDD = filterSessionAndAggrStat(sessionid2AggrInfoRDD, taskParam, sessionAggrStatAccumulator);   //过滤后的Session
        filteredSessionid2AggrInfoRDD = filteredSessionid2AggrInfoRDD.persist(StorageLevel.MEMORY_ONLY());                                                    //持久化过滤后的Session

        // 生成公共的RDD：通过筛选条件的session的访问明细数据
        JavaPairRDD<String, Row> sessionid2detailRDD = getSessionid2detailRDD(filteredSessionid2AggrInfoRDD, sessionid2actionRDD);                            // 重构：sessionid2detailRDD，就是代表了通过筛选的session对应的访问明细数据
        sessionid2detailRDD = sessionid2detailRDD.persist(StorageLevel.MEMORY_ONLY());

        randomExtractSession(sc, task.getTaskid(), filteredSessionid2AggrInfoRDD, sessionid2detailRDD);                     //随机抽取筛选过的session，把抽取道的session插入到mysql，并且把它对应的session明细也插入到表中
        calculateAndPersistAggrStat(sessionAggrStatAccumulator.value(), task.getTaskid());                                  // 计算出各个范围的session占比，并写入MySQL

        List<Tuple2<CategorySortKey, String>> top10CategoryList = getTop10Category(task.getTaskid(), sessionid2detailRDD);  // 获取top10热门品类
        getTop10Session(sc, task.getTaskid(), top10CategoryList, sessionid2detailRDD);                                      // 获取top10活跃session
        sc.close();                                                                                                         // 关闭Spark上下文
    }

    /**
     * @Description: 获取SQLContext
     * 1. 如果是在本地测试环境的话，那么就生成SQLContext对象
     * 2. 如果是在生产环境运行的话，那么就生成HiveContext对象
     * @author: wangguoqing
     * @date: 2018/10/30 15:27
     */
    private static SQLContext getSQLContext(SparkContext sc) {
        boolean local = ConfigurationManager.getBoolean(Constants.SPARK_LOCAL);
        if (local) {
            return new SQLContext(sc);
        } else {
            return new HiveContext(sc);
        }
    }

    /**
     * @Description: 生成模拟数据（只有本地模式，才会去生成模拟数据）
     * @author: wangguoqing
     * @date: 2018/10/29 10:46
     */
    private static void mockData(JavaSparkContext sc, SQLContext sqlContext) {
        boolean local = ConfigurationManager.getBoolean(Constants.SPARK_LOCAL);
        if (local) {
            MockData.mock(sc, sqlContext);
        }
    }

    /**
     * 获取指定日期范围内的用户访问行为数据
     *
     * @param sqlContext SQLContext
     * @param taskParam  任务参数
     * @return 行为数据RDD
     */
    private static JavaRDD<Row> getActionRDDByDateRange(
            SQLContext sqlContext, JSONObject taskParam) {
        String startDate = ParamUtils.getParam(taskParam, Constants.PARAM_START_DATE);
        String endDate = ParamUtils.getParam(taskParam, Constants.PARAM_END_DATE);

        String sql =
                "select * "
                        + "from user_visit_action "
                        + "where date>='" + startDate + "' "
                        + "and date<='" + endDate + "'";
//				+ "and session_id not in('','','')"

        DataFrame actionDF = sqlContext.sql(sql);
//		return actionDF.javaRDD().repartition(1000);                                                                   //调优：显现设置的task的并行度，在当前的stage会不起作用，所以要重新分区

        return actionDF.javaRDD();
    }

    /**
     * @Description: 获取sessionid2到访问行为数据的映射的RDD
     * @author: wangguoqing
     * @date: 2018/10/30 14:01
     */
    public static JavaPairRDD<String, Row> getSessionid2ActionRDD(JavaRDD<Row> actionRDD) {
        return actionRDD.mapPartitionsToPair(new PairFlatMapFunction<Iterator<Row>, String, Row>() {                   //普通的map,每条数据都会传入function中进行计算一次，而使用MapPartitions时，function会一次接受所有partition的数据出入到function中计算一次，性能较高
            @Override
            public Iterable<Tuple2<String, Row>> call(Iterator<Row> iterator) throws Exception {
                List<Tuple2<String, Row>> list = new ArrayList<Tuple2<String, Row>>();                                 //存储的是<sessionID,row>
                while (iterator.hasNext()) {
                    Row row = iterator.next();
                    list.add(new Tuple2<String, Row>(row.getString(2), row));
                }
                return list;
            }
        });
    }

    /**
     * @Description: 对行为数据按session粒度进行聚合
     * @author: wangguoqing
     * @date: 2018/10/29 14:05
     * @Return: <sessionID,row>=><userID,userActionInfo>
     */
    private static JavaPairRDD<String, String> aggregateBySession(JavaSparkContext sc, SQLContext sqlContext, JavaPairRDD<String, Row> sessinoid2actionRDD) {
        JavaPairRDD<String, Iterable<Row>> sessionid2ActionsRDD = sessinoid2actionRDD.groupByKey();                    // 对行为数据按session粒度进行分组
        JavaPairRDD<Long, String> userid2PartAggrInfoRDD = sessionid2ActionsRDD.mapToPair(                             // 对每一个session分组进行聚合，将session中所有的搜索词和点击品类都聚合起来，到此为止，获取的数据格式，如下：<userid,partAggrInfo(sessionid,searchKeywords,clickCategoryIds)>
                new PairFunction<
                        Tuple2<String, Iterable<Row>>,                                                                 //输入的数据类型
                        Long,                                                                                          //输出的数据类型Key
                        String>() {                                                                                    //数据的数据类型Value
                    @Override
                    public Tuple2<Long, String> call(Tuple2<String, Iterable<Row>> tuple)
                            throws Exception {
                        String sessionid = tuple._1;                                                                   //sessionID
                        Iterator<Row> iterator = tuple._2.iterator();                                                  //每个session对应的Row

                        StringBuffer searchKeywordsBuffer = new StringBuffer("");
                        StringBuffer clickCategoryIdsBuffer = new StringBuffer("");
                        Long userid = null;
                        Date startTime = null;                                                                         // session的起始和结束时间
                        Date endTime = null;                                                                           //session的结束时间
                        int stepLength = 0;                                                                            // session的访问步长
                        while (iterator.hasNext()) {                                                                   // 遍历session所有的访问行为
                            Row row = iterator.next();                                                                 // 提取每个访问行为的搜索词字段和点击品类字段
                            if (userid == null) {
                                userid = row.getLong(1);
                            }
                            String searchKeyword = row.getString(5);
                            Long clickCategoryId = row.getLong(6);
                            if (StringUtils.isNotEmpty(searchKeyword)) {                                               //判断是否有搜索关键词
                                if (!searchKeywordsBuffer.toString().contains(searchKeyword)) {
                                    searchKeywordsBuffer.append(searchKeyword + ",");
                                }
                            }
                            if (clickCategoryId != null) {                                                             //判断是否有点击品类关键词
                                if (!clickCategoryIdsBuffer.toString().contains(
                                        String.valueOf(clickCategoryId))) {
                                    clickCategoryIdsBuffer.append(clickCategoryId + ",");
                                }
                            }
                            Date actionTime = DateUtils.parseTime(row.getString(4));                                // 计算session开始和结束时间
                            if (startTime == null) {
                                startTime = actionTime;
                            }
                            if (endTime == null) {
                                endTime = actionTime;
                            }

                            if (actionTime.before(startTime)) {
                                startTime = actionTime;
                            }
                            if (actionTime.after(endTime)) {
                                endTime = actionTime;
                            }
                            stepLength++;                                                                              // 计算session访问步长，有一个row就加1
                        }
                        String searchKeywords = StringUtils.trimComma(searchKeywordsBuffer.toString());                //搜索的关键词
                        String clickCategoryIds = StringUtils.trimComma(clickCategoryIdsBuffer.toString());            //点击品类的关键词
                        long visitLength = (endTime.getTime() - startTime.getTime()) / 1000;                           // 计算session访问时长（秒）

                        String partAggrInfo = Constants.FIELD_SESSION_ID + "=" + sessionid + "|"
                                + Constants.FIELD_SEARCH_KEYWORDS + "=" + searchKeywords + "|"
                                + Constants.FIELD_CLICK_CATEGORY_IDS + "=" + clickCategoryIds + "|"
                                + Constants.FIELD_VISIT_LENGTH + "=" + visitLength + "|"
                                + Constants.FIELD_STEP_LENGTH + "=" + stepLength + "|"
                                + Constants.FIELD_START_TIME + "=" + DateUtils.formatTime(startTime);

                        return new Tuple2<Long, String>(userid, partAggrInfo);                                         //聚合的结构，<userid,info>
                    }

                });

        String sql = "select * from user_info";                                                                        // 查询所有用户数据，并映射成<userid,Row>的格式
        JavaRDD<Row> userInfoRDD = sqlContext.sql(sql).javaRDD();
        JavaPairRDD<Long, Row> userid2InfoRDD = userInfoRDD.mapToPair(
                new PairFunction<Row, Long, Row>() {

                    @Override
                    public Tuple2<Long, Row> call(Row row) throws Exception {
                        return new Tuple2<Long, Row>(row.getLong(0), row);                                          //<userID,userInfo>
                    }

                });
        JavaPairRDD<Long, Tuple2<String, Row>> userid2FullInfoRDD = userid2PartAggrInfoRDD.join(userid2InfoRDD);       // 将session粒度聚合数据，与用户信息进行join

        JavaPairRDD<String, String> sessionid2FullAggrInfoRDD = userid2FullInfoRDD.mapToPair(                          // 对join起来的数据进行拼接，并且返回<sessionid,fullAggrInfo>格式的数据<sessionID,userInfo+sessionInfo>
                new PairFunction<Tuple2<Long, Tuple2<String, Row>>, String, String>() {
                    @Override
                    public Tuple2<String, String> call(Tuple2<Long, Tuple2<String, Row>> tuple) throws Exception {
                        String partAggrInfo = tuple._2._1;                                                             //
                        Row userInfoRow = tuple._2._2;

                        String sessionid = StringUtils.getFieldFromConcatString(partAggrInfo, "|", Constants.FIELD_SESSION_ID);              //从拼接的字符串中提取sessionid

                        int age = userInfoRow.getInt(3);
                        String professional = userInfoRow.getString(4);
                        String city = userInfoRow.getString(5);
                        String sex = userInfoRow.getString(6);

                        String fullAggrInfo = partAggrInfo + "|"
                                + Constants.FIELD_AGE + "=" + age + "|"
                                + Constants.FIELD_PROFESSIONAL + "=" + professional + "|"
                                + Constants.FIELD_CITY + "=" + city + "|"
                                + Constants.FIELD_SEX + "=" + sex;

                        return new Tuple2<String, String>(sessionid, fullAggrInfo);
                    }
                });
        return sessionid2FullAggrInfoRDD;
    }

    /**
     * 过滤session数据，并进行聚合统计
     *
     * @param sessionid2AggrInfoRDD
     * @return
     */
    private static JavaPairRDD<String, String> filterSessionAndAggrStat(JavaPairRDD<String, String> sessionid2AggrInfoRDD, final JSONObject taskParam, final Accumulator<String> sessionAggrStatAccumulator) {
        String startAge = ParamUtils.getParam(taskParam, Constants.PARAM_START_AGE);                                   //检索条件：开始年龄
        String endAge = ParamUtils.getParam(taskParam, Constants.PARAM_END_AGE);                                       //检索条件：结束年龄
        String professionals = ParamUtils.getParam(taskParam, Constants.PARAM_PROFESSIONALS);                          //检索条件：专业
        String cities = ParamUtils.getParam(taskParam, Constants.PARAM_CITIES);                                        //检索条件：城市
        String sex = ParamUtils.getParam(taskParam, Constants.PARAM_SEX);                                              //检索条件：性别
        String keywords = ParamUtils.getParam(taskParam, Constants.PARAM_KEYWORDS);                                    //检索条件：关键词
        String categoryIds = ParamUtils.getParam(taskParam, Constants.PARAM_CATEGORY_IDS);                             //检索条件：点击词

        String _parameter =                                                                                            //拼接检索的关键词
                (startAge != null ? Constants.PARAM_START_AGE + "=" + startAge + "|" : "")
                        + (endAge != null ? Constants.PARAM_END_AGE + "=" + endAge + "|" : "")
                        + (professionals != null ? Constants.PARAM_PROFESSIONALS + "=" + professionals + "|" : "")
                        + (cities != null ? Constants.PARAM_CITIES + "=" + cities + "|" : "")
                        + (sex != null ? Constants.PARAM_SEX + "=" + sex + "|" : "")
                        + (keywords != null ? Constants.PARAM_KEYWORDS + "=" + keywords + "|" : "")
                        + (categoryIds != null ? Constants.PARAM_CATEGORY_IDS + "=" + categoryIds : "");

        if (_parameter.endsWith("\\|")) {
            _parameter = _parameter.substring(0, _parameter.length() - 1);
        }

        final String parameter = _parameter;

        JavaPairRDD<String, String> filteredSessionid2AggrInfoRDD = sessionid2AggrInfoRDD.filter(                      // 根据筛选参数进行过滤(<sessionID,aggrInfo>)
                new Function<Tuple2<String, String>, Boolean>() {
                    @Override
                    public Boolean call(Tuple2<String, String> tuple) throws Exception {
                        String aggrInfo = tuple._2;                                                                    // 首先，从tuple中，获取聚合数据
                        if (!ValidUtils.between(aggrInfo, Constants.FIELD_AGE,                                         // 按照年龄范围进行过滤（startAge、endAge）
                                parameter, Constants.PARAM_START_AGE, Constants.PARAM_END_AGE)) {
                            return false;
                        }
                        if (!ValidUtils.in(aggrInfo, Constants.FIELD_PROFESSIONAL,                                     //按照职业范围进行过滤（professionals）
                                parameter, Constants.PARAM_PROFESSIONALS)) {
                            return false;
                        }
                        if (!ValidUtils.in(aggrInfo, Constants.FIELD_CITY,                                             // 按照城市范围进行过滤（cities）
                                parameter, Constants.PARAM_CITIES)) {
                            return false;
                        }
                        if (!ValidUtils.equal(aggrInfo, Constants.FIELD_SEX,                                           // 按照性别进行过滤
                                parameter, Constants.PARAM_SEX)) {
                            return false;
                        }
                        if (!ValidUtils.in(aggrInfo, Constants.FIELD_SEARCH_KEYWORDS,                                  //按照搜索词进行过滤
                                parameter, Constants.PARAM_KEYWORDS)) {
                            return false;
                        }
                        if (!ValidUtils.in(aggrInfo, Constants.FIELD_CLICK_CATEGORY_IDS,                               // 按照点击品类id进行过滤
                                parameter, Constants.PARAM_CATEGORY_IDS)) {
                            return false;
                        }
                        sessionAggrStatAccumulator.add(Constants.SESSION_COUNT);                                       // 主要走到这一步，那么就是需要计数的session
                        long visitLength = Long.valueOf(StringUtils.getFieldFromConcatString(aggrInfo, "|", Constants.FIELD_VISIT_LENGTH));    //计算访问时长
                        long stepLength = Long.valueOf(StringUtils.getFieldFromConcatString(aggrInfo, "|", Constants.FIELD_STEP_LENGTH));      //计算访问步长
                        calculateVisitLength(visitLength);
                        calculateStepLength(stepLength);
                        return true;
                    }

                    /**
                     * @Description: 计算访问时长范围
                     * @author: wangguoqing
                     * @date: 2018/10/29 17:03
                     */
                    private void calculateVisitLength(long visitLength) {
                        if (visitLength >= 1 && visitLength <= 3) {
                            sessionAggrStatAccumulator.add(Constants.TIME_PERIOD_1s_3s);
                        } else if (visitLength >= 4 && visitLength <= 6) {
                            sessionAggrStatAccumulator.add(Constants.TIME_PERIOD_4s_6s);
                        } else if (visitLength >= 7 && visitLength <= 9) {
                            sessionAggrStatAccumulator.add(Constants.TIME_PERIOD_7s_9s);
                        } else if (visitLength >= 10 && visitLength <= 30) {
                            sessionAggrStatAccumulator.add(Constants.TIME_PERIOD_10s_30s);
                        } else if (visitLength > 30 && visitLength <= 60) {
                            sessionAggrStatAccumulator.add(Constants.TIME_PERIOD_30s_60s);
                        } else if (visitLength > 60 && visitLength <= 180) {
                            sessionAggrStatAccumulator.add(Constants.TIME_PERIOD_1m_3m);
                        } else if (visitLength > 180 && visitLength <= 600) {
                            sessionAggrStatAccumulator.add(Constants.TIME_PERIOD_3m_10m);
                        } else if (visitLength > 600 && visitLength <= 1800) {
                            sessionAggrStatAccumulator.add(Constants.TIME_PERIOD_10m_30m);
                        } else if (visitLength > 1800) {
                            sessionAggrStatAccumulator.add(Constants.TIME_PERIOD_30m);
                        }
                    }

                    /**
                     * @Description: 计算访问步长范围
                     * @author: wangguoqing
                     * @date: 2018/10/29 17:03
                     */
                    private void calculateStepLength(long stepLength) {
                        if (stepLength >= 1 && stepLength <= 3) {
                            sessionAggrStatAccumulator.add(Constants.STEP_PERIOD_1_3);
                        } else if (stepLength >= 4 && stepLength <= 6) {
                            sessionAggrStatAccumulator.add(Constants.STEP_PERIOD_4_6);
                        } else if (stepLength >= 7 && stepLength <= 9) {
                            sessionAggrStatAccumulator.add(Constants.STEP_PERIOD_7_9);
                        } else if (stepLength >= 10 && stepLength <= 30) {
                            sessionAggrStatAccumulator.add(Constants.STEP_PERIOD_10_30);
                        } else if (stepLength > 30 && stepLength <= 60) {
                            sessionAggrStatAccumulator.add(Constants.STEP_PERIOD_30_60);
                        } else if (stepLength > 60) {
                            sessionAggrStatAccumulator.add(Constants.STEP_PERIOD_60);
                        }
                    }

                });

        return filteredSessionid2AggrInfoRDD;
    }

    /**
     * @Description: 获取通过筛选条件的session的访问明细数据RDD
     * @author: wangguoqing
     * @date: 2018/10/30 14:07
     */
    private static JavaPairRDD<String, Row> getSessionid2detailRDD(JavaPairRDD<String, String> sessionid2aggrInfoRDD, JavaPairRDD<String, Row> sessionid2actionRDD) {
        JavaPairRDD<String, Row> sessionid2detailRDD = sessionid2aggrInfoRDD
                .join(sessionid2actionRDD)
                .mapToPair(new PairFunction<Tuple2<String, Tuple2<String, Row>>, String, Row>() {

                    @Override
                    public Tuple2<String, Row> call(
                            Tuple2<String, Tuple2<String, Row>> tuple) throws Exception {
                        return new Tuple2<String, Row>(tuple._1, tuple._2._2);
                    }
                });
        return sessionid2detailRDD;
    }

    /**
     * @Description: 随机抽取session
     * @author: wangguoqing
     * @date: 2018/10/30 10:33
     */
    private static void randomExtractSession(JavaSparkContext sc, final long taskid, JavaPairRDD<String, String> sessionid2AggrInfoRDD, JavaPairRDD<String, Row> sessionid2actionRDD) {
        JavaPairRDD<String, String> time2sessionidRDD = sessionid2AggrInfoRDD.mapToPair(                               //第一步，计算出每天每小时的session数量<yyyy-MM-dd_HH,aggrInfo>
                new PairFunction<Tuple2<String, String>, String, String>() {
                    @Override
                    public Tuple2<String, String> call(Tuple2<String, String> tuple) throws Exception {
                        String aggrInfo = tuple._2;
                        String startTime = StringUtils.getFieldFromConcatString(aggrInfo, "\\|", Constants.FIELD_START_TIME);
                        String dateHour = DateUtils.getDateHour(startTime);
                        return new Tuple2<String, String>(dateHour, aggrInfo);
                    }
                });
        Map<String, Object> countMap = time2sessionidRDD.countByKey();                                                 //第二步，<HH,<aggrInfo,aggrInfo,...>>
        Map<String, Map<String, Long>> dateHourCountMap = new HashMap<String, Map<String, Long>>();                                            //第三步，使用按时间比例随机抽取算法，计算出每天每小时要抽取session的索引<yyyy-MM-dd,<HH,count>>

        for (Map.Entry<String, Object> countEntry : countMap.entrySet()) {                                             //每天对应一个map，map中存储每个小时的session的总量
            String dateHour = countEntry.getKey();
            String date = dateHour.split("_")[0];
            String hour = dateHour.split("_")[1];
            long count = Long.valueOf(String.valueOf(countEntry.getValue()));
            Map<String, Long> hourCountMap = dateHourCountMap.get(date);
            if (hourCountMap == null) {
                hourCountMap = new HashMap<String, Long>();
                dateHourCountMap.put(date, hourCountMap);
            }
            hourCountMap.put(hour, count);
        }
        // 开始实现我们的按时间比例随机抽取算法
        int extractNumberPerDay = 100 / dateHourCountMap.size();                                                       // 总共要抽取100个session，先按照天数，进行平分得出每天要抽取的session的数量

        Map<String, Map<String, List<Integer>>> dateHourExtractMap = new HashMap<String, Map<String, List<Integer>>>();                                 //<date,<hour,<0点，1点，2点，3点，。。。。>>>
        Random random = new Random();

        for (Map.Entry<String, Map<String, Long>> dateHourCountEntry : dateHourCountMap.entrySet()) {
            String date = dateHourCountEntry.getKey();
            Map<String, Long> hourCountMap = dateHourCountEntry.getValue();
            long sessionCount = 0L;                                                                                    // 计算出这一天的session总数
            for (long hourCount : hourCountMap.values()) {
                sessionCount += hourCount;
            }
            Map<String, List<Integer>> hourExtractMap = dateHourExtractMap.get(date);                                  //
            if (hourExtractMap == null) {
                hourExtractMap = new HashMap<String, List<Integer>>();
                dateHourExtractMap.put(date, hourExtractMap);
            }
            for (Map.Entry<String, Long> hourCountEntry : hourCountMap.entrySet()) {                                   // 遍历每个小时
                String hour = hourCountEntry.getKey();
                long count = hourCountEntry.getValue();                                                                //每个小时要抽取的session的总数

                int hourExtractNumber = (int) (((double) count / (double) sessionCount)                                // 计算每个小时的session数量，占据当天总session数量的比例，直接乘以每天要抽取的数量，就可以计算出，当前小时需要抽取的session数量
                        * extractNumberPerDay);
                if (hourExtractNumber > count) {
                    hourExtractNumber = (int) count;
                }
                List<Integer> extractIndexList = hourExtractMap.get(hour);                                             // 先获取当前小时的存放随机数的list
                if (extractIndexList == null) {
                    extractIndexList = new ArrayList<Integer>();
                    hourExtractMap.put(hour, extractIndexList);                                                        //每个小时抽取的session数据（就是真正的数据先保存到List中）
                }
                for (int i = 0; i < hourExtractNumber; i++) {                                                          // 生成上面计算出来的数量的随机数
                    int extractIndex = random.nextInt((int) count);
                    while (extractIndexList.contains(extractIndex)) {
                        extractIndex = random.nextInt((int) count);
                    }
                    extractIndexList.add(extractIndex);
                }
            }
        }

        /**
         * fastutil的使用，很简单，比如List<Integer>的list，对应到fastutil，就是IntList
         * 使用fastutil是对，集合操作的一种优化
         */
        Map<String, Map<String, IntList>> fastutilDateHourExtractMap = new HashMap<String, Map<String, IntList>>();


        for (Map.Entry<String, Map<String, List<Integer>>> dateHourExtractEntry :
                dateHourExtractMap.entrySet()) {
            String date = dateHourExtractEntry.getKey();
            Map<String, List<Integer>> hourExtractMap = dateHourExtractEntry.getValue();
            Map<String, IntList> fastutilHourExtractMap = new HashMap<String, IntList>();
            for (Map.Entry<String, List<Integer>> hourExtractEntry : hourExtractMap.entrySet()) {
                String hour = hourExtractEntry.getKey();
                List<Integer> extractList = hourExtractEntry.getValue();
                IntList fastutilExtractList = new IntArrayList();
                for (int i = 0; i < extractList.size(); i++) {
                    fastutilExtractList.add(extractList.get(i));
                }
                fastutilHourExtractMap.put(hour, fastutilExtractList);
            }
            fastutilDateHourExtractMap.put(date, fastutilHourExtractMap);
        }
        final Broadcast<Map<String, Map<String, IntList>>> dateHourExtractMapBroadcast = sc.broadcast(fastutilDateHourExtractMap);  //广播变量
        JavaPairRDD<String, Iterable<String>> time2sessionsRDD = time2sessionidRDD.groupByKey();                                    //第四步：遍历每天每小时的session，然后根据随机索引进行抽取,执行groupByKey算子，得到<dateHour,(session aggrInfo)>

        /*
           用flatMap算子，遍历所有的<dateHour,(session aggrInfo)>格式的数据然后，会遍历每天每小时的session如果发现某个session恰巧在我们指定的这天这小时的随机抽取索引上
           那么抽取该session，直接写入MySQL的random_extract_session表,将抽取出来的session id返回回来，形成一个新的JavaRDD<String>最后一步，是用抽取出来的sessionid，去join它们的访问行为明细数据，写入session表
         */
        JavaPairRDD<String, String> extractSessionidsRDD = time2sessionsRDD.flatMapToPair(
                new PairFlatMapFunction<Tuple2<String, Iterable<String>>, String, String>() {

                    @Override
                    public Iterable<Tuple2<String, String>> call(
                            Tuple2<String, Iterable<String>> tuple)
                            throws Exception {
                        List<Tuple2<String, String>> extractSessionids =
                                new ArrayList<Tuple2<String, String>>();
                        String dateHour = tuple._1;
                        String date = dateHour.split("_")[0];
                        String hour = dateHour.split("_")[1];
                        Iterator<String> iterator = tuple._2.iterator();
                        /**
                         * 使用广播变量的时候
                         * 直接调用广播变量（Broadcast类型）的value() / getValue()
                         * 可以获取到之前封装的广播变量
                         */
                        Map<String, Map<String, IntList>> dateHourExtractMap =
                                dateHourExtractMapBroadcast.value();
                        List<Integer> extractIndexList = dateHourExtractMap.get(date).get(hour);
                        ISessionRandomExtractDAO sessionRandomExtractDAO =
                                DAOFactory.getSessionRandomExtractDAO();
                        int index = 0;
                        while (iterator.hasNext()) {
                            String sessionAggrInfo = iterator.next();
                            if (extractIndexList.contains(index)) {
                                String sessionid = StringUtils.getFieldFromConcatString(
                                        sessionAggrInfo, "\\|", Constants.FIELD_SESSION_ID);
                                SessionRandomExtract sessionRandomExtract = new SessionRandomExtract();                // 将数据写入MySQL
                                sessionRandomExtract.setTaskid(taskid);
                                sessionRandomExtract.setSessionid(sessionid);
                                sessionRandomExtract.setStartTime(StringUtils.getFieldFromConcatString(sessionAggrInfo, "\\|", Constants.FIELD_START_TIME));
                                sessionRandomExtract.setSearchKeywords(StringUtils.getFieldFromConcatString(sessionAggrInfo, "\\|", Constants.FIELD_SEARCH_KEYWORDS));
                                sessionRandomExtract.setClickCategoryIds(StringUtils.getFieldFromConcatString(sessionAggrInfo, "\\|", Constants.FIELD_CLICK_CATEGORY_IDS));
                                sessionRandomExtractDAO.insert(sessionRandomExtract);
                                extractSessionids.add(new Tuple2<String, String>(sessionid, sessionid));               // 将sessionid加入list
                            }
                            index++;
                        }
                        return extractSessionids;
                    }
                });

        JavaPairRDD<String, Tuple2<String, Row>> extractSessionDetailRDD = extractSessionidsRDD.join(sessionid2actionRDD);//第五步：获取抽取出来的session的明细数据
        extractSessionDetailRDD.foreachPartition(new VoidFunction<Iterator<Tuple2<String, Tuple2<String, Row>>>>() {       //调优(使用foreachPartition)
                    @Override
                    public void call(Iterator<Tuple2<String, Tuple2<String, Row>>> iterator) throws Exception {
                        List<SessionDetail> sessionDetails = new ArrayList<SessionDetail>();
                        while (iterator.hasNext()) {
                            Tuple2<String, Tuple2<String, Row>> tuple = iterator.next();
                            Row row = tuple._2._2;
                            SessionDetail sessionDetail = new SessionDetail();
                            sessionDetail.setTaskid(taskid);
                            sessionDetail.setUserid(row.getLong(1));
                            sessionDetail.setSessionid(row.getString(2));
                            sessionDetail.setPageid(row.getLong(3));
                            sessionDetail.setActionTime(row.getString(4));
                            sessionDetail.setSearchKeyword(row.getString(5));
                            sessionDetail.setClickCategoryId(row.getLong(6));
                            sessionDetail.setClickProductId(row.getLong(7));
                            sessionDetail.setOrderCategoryIds(row.getString(8));
                            sessionDetail.setOrderProductIds(row.getString(9));
                            sessionDetail.setPayCategoryIds(row.getString(10));
                            sessionDetail.setPayProductIds(row.getString(11));
                            sessionDetails.add(sessionDetail);
                        }
                        ISessionDetailDAO sessionDetailDAO = DAOFactory.getSessionDetailDAO();
                        sessionDetailDAO.insertBatch(sessionDetails);                                                  //把抽取出来的session对应的session明细数据插入到mysql当中去
                    }
                });
    }

    /**
     * @Description: 计算各session范围占比，并写入MySQL
     * @author: wangguoqing
     * @date: 2018/10/30 9:58
     */
    private static void calculateAndPersistAggrStat(String value, long taskid) {
        // 从Accumulator统计串中获取值
        long session_count = Long.valueOf(StringUtils.getFieldFromConcatString(value, "\\|", Constants.SESSION_COUNT));                             //从累加器中获取数据
        long visit_length_1s_3s = Long.valueOf(StringUtils.getFieldFromConcatString(value, "\\|", Constants.TIME_PERIOD_1s_3s));                    //从累加器中获取数据
        long visit_length_4s_6s = Long.valueOf(StringUtils.getFieldFromConcatString(value, "\\|", Constants.TIME_PERIOD_4s_6s));                    //从累加器中获取数据
        long visit_length_7s_9s = Long.valueOf(StringUtils.getFieldFromConcatString(value, "\\|", Constants.TIME_PERIOD_7s_9s));                    //从累加器中获取数据
        long visit_length_10s_30s = Long.valueOf(StringUtils.getFieldFromConcatString(value, "\\|", Constants.TIME_PERIOD_10s_30s));                //从累加器中获取数据
        long visit_length_30s_60s = Long.valueOf(StringUtils.getFieldFromConcatString(value, "\\|", Constants.TIME_PERIOD_30s_60s));                //从累加器中获取数据
        long visit_length_1m_3m = Long.valueOf(StringUtils.getFieldFromConcatString(value, "\\|", Constants.TIME_PERIOD_1m_3m));                    //从累加器中获取数据
        long visit_length_3m_10m = Long.valueOf(StringUtils.getFieldFromConcatString(value, "\\|", Constants.TIME_PERIOD_3m_10m));                  //从累加器中获取数据
        long visit_length_10m_30m = Long.valueOf(StringUtils.getFieldFromConcatString(value, "\\|", Constants.TIME_PERIOD_10m_30m));                //从累加器中获取数据
        long visit_length_30m = Long.valueOf(StringUtils.getFieldFromConcatString(value, "\\|", Constants.TIME_PERIOD_30m));                        //从累加器中获取数据

        long step_length_1_3 = Long.valueOf(StringUtils.getFieldFromConcatString(value, "\\|", Constants.STEP_PERIOD_1_3));                         //从累加器中获取数据
        long step_length_4_6 = Long.valueOf(StringUtils.getFieldFromConcatString(value, "\\|", Constants.STEP_PERIOD_4_6));                         //从累加器中获取数据
        long step_length_7_9 = Long.valueOf(StringUtils.getFieldFromConcatString(value, "\\|", Constants.STEP_PERIOD_7_9));                         //从累加器中获取数据
        long step_length_10_30 = Long.valueOf(StringUtils.getFieldFromConcatString(value, "\\|", Constants.STEP_PERIOD_10_30));                     //从累加器中获取数据
        long step_length_30_60 = Long.valueOf(StringUtils.getFieldFromConcatString(value, "\\|", Constants.STEP_PERIOD_30_60));                     //从累加器中获取数据
        long step_length_60 = Long.valueOf(StringUtils.getFieldFromConcatString(value, "\\|", Constants.STEP_PERIOD_60));                           //从累加器中获取数据

        // 计算各个访问时长和访问步长的范围
        double visit_length_1s_3s_ratio = NumberUtils.formatDouble((double) visit_length_1s_3s / (double) session_count, 2);                     // 计算访问时长的比例
        double visit_length_4s_6s_ratio = NumberUtils.formatDouble((double) visit_length_4s_6s / (double) session_count, 2);                     // 计算访问时长的比例
        double visit_length_7s_9s_ratio = NumberUtils.formatDouble((double) visit_length_7s_9s / (double) session_count, 2);                     // 计算访问时长的比例
        double visit_length_10s_30s_ratio = NumberUtils.formatDouble((double) visit_length_10s_30s / (double) session_count, 2);                 // 计算访问时长的比例
        double visit_length_30s_60s_ratio = NumberUtils.formatDouble((double) visit_length_30s_60s / (double) session_count, 2);                 // 计算访问时长的比例
        double visit_length_1m_3m_ratio = NumberUtils.formatDouble((double) visit_length_1m_3m / (double) session_count, 2);                     // 计算访问时长的比例
        double visit_length_3m_10m_ratio = NumberUtils.formatDouble((double) visit_length_3m_10m / (double) session_count, 2);                   // 计算访问时长的比例
        double visit_length_10m_30m_ratio = NumberUtils.formatDouble((double) visit_length_10m_30m / (double) session_count, 2);                 // 计算访问时长的比例
        double visit_length_30m_ratio = NumberUtils.formatDouble((double) visit_length_30m / (double) session_count, 2);                         // 计算访问时长的比例

        double step_length_1_3_ratio = NumberUtils.formatDouble((double) step_length_1_3 / (double) session_count, 2);                           // 计算访问步长的比例
        double step_length_4_6_ratio = NumberUtils.formatDouble((double) step_length_4_6 / (double) session_count, 2);                           // 计算访问步长的比例
        double step_length_7_9_ratio = NumberUtils.formatDouble((double) step_length_7_9 / (double) session_count, 2);                           // 计算访问步长的比例
        double step_length_10_30_ratio = NumberUtils.formatDouble((double) step_length_10_30 / (double) session_count, 2);                       // 计算访问步长的比例
        double step_length_30_60_ratio = NumberUtils.formatDouble((double) step_length_30_60 / (double) session_count, 2);                       // 计算访问步长的比例
        double step_length_60_ratio = NumberUtils.formatDouble((double) step_length_60 / (double) session_count, 2);                             // 计算访问步长的比例

        // 将统计结果封装为Domain对象
        SessionAggrStat sessionAggrStat = new SessionAggrStat();
        sessionAggrStat.setTaskid(taskid);
        sessionAggrStat.setSession_count(session_count);
        sessionAggrStat.setVisit_length_1s_3s_ratio(visit_length_1s_3s_ratio);
        sessionAggrStat.setVisit_length_4s_6s_ratio(visit_length_4s_6s_ratio);
        sessionAggrStat.setVisit_length_7s_9s_ratio(visit_length_7s_9s_ratio);
        sessionAggrStat.setVisit_length_10s_30s_ratio(visit_length_10s_30s_ratio);
        sessionAggrStat.setVisit_length_30s_60s_ratio(visit_length_30s_60s_ratio);
        sessionAggrStat.setVisit_length_1m_3m_ratio(visit_length_1m_3m_ratio);
        sessionAggrStat.setVisit_length_3m_10m_ratio(visit_length_3m_10m_ratio);
        sessionAggrStat.setVisit_length_10m_30m_ratio(visit_length_10m_30m_ratio);
        sessionAggrStat.setVisit_length_30m_ratio(visit_length_30m_ratio);
        sessionAggrStat.setStep_length_1_3_ratio(step_length_1_3_ratio);
        sessionAggrStat.setStep_length_4_6_ratio(step_length_4_6_ratio);
        sessionAggrStat.setStep_length_7_9_ratio(step_length_7_9_ratio);
        sessionAggrStat.setStep_length_10_30_ratio(step_length_10_30_ratio);
        sessionAggrStat.setStep_length_30_60_ratio(step_length_30_60_ratio);
        sessionAggrStat.setStep_length_60_ratio(step_length_60_ratio);

        // 调用对应的DAO插入统计结果
        ISessionAggrStatDAO sessionAggrStatDAO = DAOFactory.getSessionAggrStatDAO();
        sessionAggrStatDAO.insert(sessionAggrStat);
    }

    /**
     * @param filteredSessionid2AggrInfoRDD
     * @param sessionid2actionRDD
     * @Description: 获取top10热门品类
     * @author: wangguoqing
     * @date: 2018/10/30 14:30
     */
    private static List<Tuple2<CategorySortKey, String>> getTop10Category(
            long taskid,
            JavaPairRDD<String, Row> sessionid2detailRDD) {
        //第一步：获取符合条件的session访问过的所有品类
        JavaPairRDD<Long, Long> categoryidRDD = sessionid2detailRDD.flatMapToPair(                                     // 获取session访问过的所有品类id[访问过：指的是，点击过、下单过、支付过的品类]
                new PairFlatMapFunction<Tuple2<String, Row>, Long, Long>() {
                    @Override
                    public Iterable<Tuple2<Long, Long>> call(
                            Tuple2<String, Row> tuple) throws Exception {
                        Row row = tuple._2;
                        List<Tuple2<Long, Long>> list = new ArrayList<Tuple2<Long, Long>>();
                        Long clickCategoryId = row.getLong(6);                                                      //获取session中的点击过的品类ID
                        if (clickCategoryId != null) {
                            list.add(new Tuple2<Long, Long>(clickCategoryId, clickCategoryId));
                        }
                        String orderCategoryIds = row.getString(8);                                                 //获取session中的下单ID[可能会有多个]
                        if (orderCategoryIds != null) {
                            String[] orderCategoryIdsSplited = orderCategoryIds.split(",");
                            for (String orderCategoryId : orderCategoryIdsSplited) {
                                list.add(new Tuple2<Long, Long>(Long.valueOf(orderCategoryId),
                                        Long.valueOf(orderCategoryId)));
                            }
                        }
                        String payCategoryIds = row.getString(10);                                                  //获取session中的支付ID[可能会有多个]
                        if (payCategoryIds != null) {
                            String[] payCategoryIdsSplited = payCategoryIds.split(",");
                            for (String payCategoryId : payCategoryIdsSplited) {
                                list.add(new Tuple2<Long, Long>(Long.valueOf(payCategoryId),
                                        Long.valueOf(payCategoryId)));
                            }
                        }
                        return list;
                    }

                });

        categoryidRDD = categoryidRDD.distinct();                                                                      //去重
        //第二步：计算各品类的点击、下单和支付的次数
        JavaPairRDD<Long, Long> clickCategoryId2CountRDD = getClickCategoryId2CountRDD(sessionid2detailRDD);           // 计算各个品类的点击次数
        JavaPairRDD<Long, Long> orderCategoryId2CountRDD = getOrderCategoryId2CountRDD(sessionid2detailRDD);           // 计算各个品类的下单次数
        JavaPairRDD<Long, Long> payCategoryId2CountRDD = getPayCategoryId2CountRDD(sessionid2detailRDD);               // 计算各个品类的支付次数

        //第三步：join各品类与它的点击、下单和支付的次数
        JavaPairRDD<Long, String> categoryid2countRDD = joinCategoryAndData(categoryidRDD, clickCategoryId2CountRDD, orderCategoryId2CountRDD, payCategoryId2CountRDD);
        //第四步：自定义二次排序key   CategorySortKey
        //第五步：将数据映射成<CategorySortKey,info>格式的RDD，然后进行二次排序（降序）
        JavaPairRDD<CategorySortKey, String> sortKey2countRDD = categoryid2countRDD.mapToPair(
                new PairFunction<Tuple2<Long, String>, CategorySortKey, String>() {
                    @Override
                    public Tuple2<CategorySortKey, String> call(Tuple2<Long, String> tuple) throws Exception {
                        String countInfo = tuple._2;
                        long clickCount = Long.valueOf(StringUtils.getFieldFromConcatString(countInfo, "\\|", Constants.FIELD_CLICK_COUNT));
                        long orderCount = Long.valueOf(StringUtils.getFieldFromConcatString(countInfo, "\\|", Constants.FIELD_ORDER_COUNT));
                        long payCount = Long.valueOf(StringUtils.getFieldFromConcatString(countInfo, "\\|", Constants.FIELD_PAY_COUNT));
                        CategorySortKey sortKey = new CategorySortKey(clickCount, orderCount, payCount);
                        return new Tuple2<CategorySortKey, String>(sortKey, countInfo);
                    }
                });
        JavaPairRDD<CategorySortKey, String> sortedCategoryCountRDD = sortKey2countRDD.sortByKey(false);               //执行二次排序
        //第六步：用take(10)取出top10热门品类，并写入MySQL
        ITop10CategoryDAO top10CategoryDAO = DAOFactory.getTop10CategoryDAO();
        List<Tuple2<CategorySortKey, String>> top10CategoryList = sortedCategoryCountRDD.take(10);
        for (Tuple2<CategorySortKey, String> tuple : top10CategoryList) {
            String countInfo = tuple._2;
            long categoryid = Long.valueOf(StringUtils.getFieldFromConcatString(countInfo, "\\|", Constants.FIELD_CATEGORY_ID));
            long clickCount = Long.valueOf(StringUtils.getFieldFromConcatString(countInfo, "\\|", Constants.FIELD_CLICK_COUNT));
            long orderCount = Long.valueOf(StringUtils.getFieldFromConcatString(countInfo, "\\|", Constants.FIELD_ORDER_COUNT));
            long payCount = Long.valueOf(StringUtils.getFieldFromConcatString(countInfo, "\\|", Constants.FIELD_PAY_COUNT));

            Top10Category category = new Top10Category();
            category.setTaskid(taskid);
            category.setCategoryid(categoryid);
            category.setClickCount(clickCount);
            category.setOrderCount(orderCount);
            category.setPayCount(payCount);

            top10CategoryDAO.insert(category);
        }

        return top10CategoryList;
    }

    /**
     * @param sessionid2detailRDD
     * @Description: 获取各品类点击次数RDD
     * @author: wangguoqing
     * @date: 2018/10/30 14:47
     */
    private static JavaPairRDD<Long, Long> getClickCategoryId2CountRDD(
            JavaPairRDD<String, Row> sessionid2detailRDD) {
        JavaPairRDD<String, Row> clickActionRDD = sessionid2detailRDD.filter(
                new Function<Tuple2<String, Row>, Boolean>() {
                    @Override
                    public Boolean call(Tuple2<String, Row> tuple) throws Exception {
                        Row row = tuple._2;
                        return row.get(6) != null ? true : false;                                                      //如果为true说明有点击行为，反之则没有点击行为
                    }
                });
//				.coalesce(100);                                                                                        //用coalesce算子，在filter过后去减少partition的数量
        JavaPairRDD<Long, Long> clickCategoryIdRDD = clickActionRDD.mapToPair(                                         //RDD===之中的数据是====》<click_categoryId,1>
                new PairFunction<Tuple2<String, Row>, Long, Long>() {
                    @Override
                    public Tuple2<Long, Long> call(Tuple2<String, Row> tuple) throws Exception {
                        long clickCategoryId = tuple._2.getLong(6);
                        return new Tuple2<Long, Long>(clickCategoryId, 1L);
                    }
                });

        JavaPairRDD<Long, Long> clickCategoryId2CountRDD = clickCategoryIdRDD.reduceByKey(                             //计算各个品类的点击次数【如果某个品类点击了1000万次，其他品类都是10万次，那么也会数据倾斜】
                new Function2<Long, Long, Long>() {
                    @Override
                    public Long call(Long v1, Long v2) throws Exception {
                        return v1 + v2;
                    }
                });
        return clickCategoryId2CountRDD;
    }

    /**
     * @Description: 获取各品类的下单次数RDD
     * @author: wangguoqing
     * @date: 2018/10/30 14:58
     */
    private static JavaPairRDD<Long, Long> getOrderCategoryId2CountRDD(
            JavaPairRDD<String, Row> sessionid2detailRDD) {
        JavaPairRDD<String, Row> orderActionRDD = sessionid2detailRDD.filter(                                          //第一步：先过滤出有下单行为的session
                new Function<Tuple2<String, Row>, Boolean>() {
                    @Override
                    public Boolean call(Tuple2<String, Row> tuple) throws Exception {
                        Row row = tuple._2;
                        return row.getString(8) != null ? true : false;
                    }

                });
        JavaPairRDD<Long, Long> orderCategoryIdRDD = orderActionRDD.flatMapToPair(                                     //第二步：展开每个session的数据为<orderCategoryId,1L>
                new PairFlatMapFunction<Tuple2<String, Row>, Long, Long>() {
                    @Override
                    public Iterable<Tuple2<Long, Long>> call(
                            Tuple2<String, Row> tuple) throws Exception {
                        Row row = tuple._2;
                        String orderCategoryIds = row.getString(8);
                        String[] orderCategoryIdsSplited = orderCategoryIds.split(",");

                        List<Tuple2<Long, Long>> list = new ArrayList<Tuple2<Long, Long>>();

                        for (String orderCategoryId : orderCategoryIdsSplited) {
                            list.add(new Tuple2<Long, Long>(Long.valueOf(orderCategoryId), 1L));
                        }
                        return list;
                    }

                });

        JavaPairRDD<Long, Long> orderCategoryId2CountRDD = orderCategoryIdRDD.reduceByKey(                             //第三步：根据key进行聚合<orderCategoryId,1L>===><orderCategoryId,2L>
                new Function2<Long, Long, Long>() {

                    @Override
                    public Long call(Long v1, Long v2) throws Exception {
                        return v1 + v2;
                    }
                });

        return orderCategoryId2CountRDD;                                                                               //第四步：返回数据<orderCategoryId,（N）L>
    }

    /**
     * @Description: 获取各个品类的支付次数RDD
     * @author: wangguoqing
     * @date: 2018/10/30 15:03
     */
    private static JavaPairRDD<Long, Long> getPayCategoryId2CountRDD(
            JavaPairRDD<String, Row> sessionid2detailRDD) {
        JavaPairRDD<String, Row> payActionRDD = sessionid2detailRDD.filter(
                new Function<Tuple2<String, Row>, Boolean>() {
                    @Override
                    public Boolean call(Tuple2<String, Row> tuple) throws Exception {
                        Row row = tuple._2;
                        return row.getString(10) != null ? true : false;
                    }
                });
        JavaPairRDD<Long, Long> payCategoryIdRDD = payActionRDD.flatMapToPair(
                new PairFlatMapFunction<Tuple2<String, Row>, Long, Long>() {
                    @Override
                    public Iterable<Tuple2<Long, Long>> call(
                            Tuple2<String, Row> tuple) throws Exception {
                        Row row = tuple._2;
                        String payCategoryIds = row.getString(10);
                        String[] payCategoryIdsSplited = payCategoryIds.split(",");
                        List<Tuple2<Long, Long>> list = new ArrayList<Tuple2<Long, Long>>();
                        for (String payCategoryId : payCategoryIdsSplited) {
                            list.add(new Tuple2<Long, Long>(Long.valueOf(payCategoryId), 1L));
                        }

                        return list;
                    }
                });
        JavaPairRDD<Long, Long> payCategoryId2CountRDD = payCategoryIdRDD.reduceByKey(
                new Function2<Long, Long, Long>() {
                    @Override
                    public Long call(Long v1, Long v2) throws Exception {
                        return v1 + v2;
                    }
                });
        return payCategoryId2CountRDD;
    }

    /**
     * @param categoryidRDD
     * @param clickCategoryId2CountRDD
     * @param orderCategoryId2CountRDD
     * @param payCategoryId2CountRDD
     * @Description: 连接品类RDD与数据RDD
     * @author: wangguoqing
     * @date: 2018/10/30 15:04
     */
    private static JavaPairRDD<Long, String> joinCategoryAndData(
            JavaPairRDD<Long, Long> categoryidRDD,                                                                     //访问过得品类
            JavaPairRDD<Long, Long> clickCategoryId2CountRDD,                                                          //点击品类
            JavaPairRDD<Long, Long> orderCategoryId2CountRDD,                                                          //订单品类
            JavaPairRDD<Long, Long> payCategoryId2CountRDD) {                                                          //支付品类
        JavaPairRDD<Long, Tuple2<Long, Optional<Long>>> tmpJoinRDD =
                categoryidRDD.leftOuterJoin(clickCategoryId2CountRDD);                                                 //如果用leftOuterJoin，就可能出现，右边那个RDD中，join过来时没有值，所以Tuple中的第二个值用Optional<Long>类型，就代表，可能有值，可能没有值
        //1.产生了点击的品类
        JavaPairRDD<Long, String> tmpMapRDD = tmpJoinRDD.mapToPair(
                new PairFunction<Tuple2<Long, Tuple2<Long, Optional<Long>>>, Long, String>() {
                    @Override
                    public Tuple2<Long, String> call(
                            Tuple2<Long, Tuple2<Long, Optional<Long>>> tuple) throws Exception {
                        long categoryid = tuple._1;                                                                    //获取品类ID
                        Optional<Long> optional = tuple._2._2;                                                         //
                        long clickCount = 0L;
                        if (optional.isPresent()) {
                            clickCount = optional.get();                                                               //获取点击的次数
                        }
                        String value = Constants.FIELD_CATEGORY_ID + "=" + categoryid + "|" + Constants.FIELD_CLICK_COUNT + "=" + clickCount;
                        return new Tuple2<Long, String>(categoryid, value);
                    }

                });
        //2.产生了点击并且下单了的品类
        tmpMapRDD = tmpMapRDD.leftOuterJoin(orderCategoryId2CountRDD).mapToPair(
                new PairFunction<Tuple2<Long, Tuple2<String, Optional<Long>>>, Long, String>() {
                    @Override
                    public Tuple2<Long, String> call(
                            Tuple2<Long, Tuple2<String, Optional<Long>>> tuple)
                            throws Exception {
                        long categoryid = tuple._1;
                        String value = tuple._2._1;
                        Optional<Long> optional = tuple._2._2;
                        long orderCount = 0L;
                        if (optional.isPresent()) {
                            orderCount = optional.get();
                        }
                        value = value + "|" + Constants.FIELD_ORDER_COUNT + "=" + orderCount;
                        return new Tuple2<Long, String>(categoryid, value);
                    }

                });
        //3.产生了点击并且下单并且支付了的品类
        tmpMapRDD = tmpMapRDD.leftOuterJoin(payCategoryId2CountRDD).mapToPair(
                new PairFunction<Tuple2<Long, Tuple2<String, Optional<Long>>>, Long, String>() {
                    @Override
                    public Tuple2<Long, String> call(
                            Tuple2<Long, Tuple2<String, Optional<Long>>> tuple)
                            throws Exception {
                        long categoryid = tuple._1;
                        String value = tuple._2._1;
                        Optional<Long> optional = tuple._2._2;
                        long payCount = 0L;
                        if (optional.isPresent()) {
                            payCount = optional.get();
                        }
                        value = value + "|" + Constants.FIELD_PAY_COUNT + "=" + payCount;
                        return new Tuple2<Long, String>(categoryid, value);
                    }
                });
        return tmpMapRDD;
    }

    /**
     * @param taskid
     * @param sessionid2detailRDD
     * @Description: 获取top10活跃session
     * @author: wangguoqing
     * @date: 2018/10/30 15:57
     */
    private static void getTop10Session(
            JavaSparkContext sc,
            final long taskid,
            List<Tuple2<CategorySortKey, String>> top10CategoryList,
            JavaPairRDD<String, Row> sessionid2detailRDD) {
        //第一步：将top10热门品类的id，生成一份RDD
        List<Tuple2<Long, Long>> top10CategoryIdList = new ArrayList<Tuple2<Long, Long>>();
        for (Tuple2<CategorySortKey, String> category : top10CategoryList) {
            long categoryid = Long.valueOf(StringUtils.getFieldFromConcatString(category._2, "\\|", Constants.FIELD_CATEGORY_ID));
            top10CategoryIdList.add(new Tuple2<Long, Long>(categoryid, categoryid));
        }
        JavaPairRDD<Long, Long> top10CategoryIdRDD = sc.parallelizePairs(top10CategoryIdList);                         //用List集合生成JavaPairRDD
        //第二步：计算top10品类被各session点击的次数
        JavaPairRDD<String, Iterable<Row>> sessionid2detailsRDD = sessionid2detailRDD.groupByKey();
        JavaPairRDD<Long, String> categoryid2sessionCountRDD = sessionid2detailsRDD.flatMapToPair(                     //每个品类被点击的session的总数
                new PairFlatMapFunction<Tuple2<String, Iterable<Row>>, Long, String>() {
                    @Override
                    public Iterable<Tuple2<Long, String>> call(Tuple2<String, Iterable<Row>> tuple) throws Exception {
                        String sessionid = tuple._1;
                        Iterator<Row> iterator = tuple._2.iterator();
                        Map<Long, Long> categoryCountMap = new HashMap<Long, Long>();
                        // 计算出该session，对每个品类的点击次数
                        while (iterator.hasNext()) {
                            Row row = iterator.next();
                            if (row.get(6) != null) {
                                long categoryid = row.getLong(6);
                                Long count = categoryCountMap.get(categoryid);
                                if (count == null) {
                                    count = 0L;
                                }
                                count++;
                                categoryCountMap.put(categoryid, count);
                            }
                        }
                        // 返回结果，<categoryid,sessionid,count>格式
                        List<Tuple2<Long, String>> list = new ArrayList<Tuple2<Long, String>>();
                        for (Map.Entry<Long, Long> categoryCountEntry : categoryCountMap.entrySet()) {
                            long categoryid = categoryCountEntry.getKey();
                            long count = categoryCountEntry.getValue();
                            String value = sessionid + "," + count;
                            list.add(new Tuple2<Long, String>(categoryid, value));
                        }
                        return list;
                    }
                });
        // 获取到to10热门品类，被各个session点击的次数
        JavaPairRDD<Long, String> top10CategorySessionCountRDD = top10CategoryIdRDD.join(categoryid2sessionCountRDD)
                .mapToPair(new PairFunction<Tuple2<Long, Tuple2<Long, String>>, Long, String>() {
                    @Override
                    public Tuple2<Long, String> call(Tuple2<Long, Tuple2<Long, String>> tuple) throws Exception {
                        return new Tuple2<Long, String>(tuple._1, tuple._2._2);
                    }
                });
        //第三步：分组取TopN算法实现，获取每个品类的top10活跃用户
        JavaPairRDD<Long, Iterable<String>> top10CategorySessionCountsRDD = top10CategorySessionCountRDD.groupByKey();
        JavaPairRDD<String, String> top10SessionRDD = top10CategorySessionCountsRDD.flatMapToPair(
                new PairFlatMapFunction<Tuple2<Long, Iterable<String>>, String, String>() {
                    @Override
                    public Iterable<Tuple2<String, String>> call(
                            Tuple2<Long, Iterable<String>> tuple)
                            throws Exception {
                        long categoryid = tuple._1;
                        Iterator<String> iterator = tuple._2.iterator();
                        String[] top10Sessions = new String[10];                                                       // 定义取topn的排序数组
                        while (iterator.hasNext()) {
                            String sessionCount = iterator.next();
                            long count = Long.valueOf(sessionCount.split(",")[1]);
                            for (int i = 0; i < top10Sessions.length; i++) {                                           // 遍历排序数组
                                if (top10Sessions[i] == null) {                                                        //// 如果当前i位，没有数据，那么直接将i位数据赋值为当前sessionCount
                                    top10Sessions[i] = sessionCount;
                                    break;
                                } else {
                                    long _count = Long.valueOf(top10Sessions[i].split(",")[1]);
                                    if (count > _count) {                                                              // 如果sessionCount比i位的sessionCount要大
                                        for (int j = 9; j > i; j--) {                                                  // 从排序数组最后一位开始，到i位，所有数据往后挪一位
                                            top10Sessions[j] = top10Sessions[j - 1];
                                        }
                                        top10Sessions[i] = sessionCount;                                               // 将i位赋值为sessionCount
                                        break;
                                    }
                                    // 比较小，继续外层for循环
                                }
                            }
                        }
                        List<Tuple2<String, String>> list = new ArrayList<Tuple2<String, String>>();                                        // 将数据写入MySQL表
                        for (String sessionCount : top10Sessions) {
                            if (sessionCount != null) {
                                String sessionid = sessionCount.split(",")[0];
                                long count = Long.valueOf(sessionCount.split(",")[1]);
                                Top10Session top10Session = new Top10Session();
                                top10Session.setTaskid(taskid);                                                        // 封装成domain然后插入数据库
                                top10Session.setCategoryid(categoryid);
                                top10Session.setSessionid(sessionid);
                                top10Session.setClickCount(count);
                                ITop10SessionDAO top10SessionDAO = DAOFactory.getTop10SessionDAO();
                                top10SessionDAO.insert(top10Session);
                                list.add(new Tuple2<String, String>(sessionid, sessionid));                            // 放入list
                            }
                        }
                        return list;
                    }
                });

        //第四步：获取top10活跃session的明细数据，并写入MySQL
        JavaPairRDD<String, Tuple2<String, Row>> sessionDetailRDD = top10SessionRDD.join(sessionid2detailRDD);
        sessionDetailRDD.foreach(new VoidFunction<Tuple2<String, Tuple2<String, Row>>>() {
            @Override
            public void call(Tuple2<String, Tuple2<String, Row>> tuple) throws Exception {
                Row row = tuple._2._2;
                SessionDetail sessionDetail = new SessionDetail();
                sessionDetail.setTaskid(taskid);
                sessionDetail.setUserid(row.getLong(1));
                sessionDetail.setSessionid(row.getString(2));
                sessionDetail.setPageid(row.getLong(3));
                sessionDetail.setActionTime(row.getString(4));
                sessionDetail.setSearchKeyword(row.getString(5));
                sessionDetail.setClickCategoryId(row.getLong(6));
                sessionDetail.setClickProductId(row.getLong(7));
                sessionDetail.setOrderCategoryIds(row.getString(8));
                sessionDetail.setOrderProductIds(row.getString(9));
                sessionDetail.setPayCategoryIds(row.getString(10));
                sessionDetail.setPayProductIds(row.getString(11));
                ISessionDetailDAO sessionDetailDAO = DAOFactory.getSessionDetailDAO();
                sessionDetailDAO.insert(sessionDetail);
            }
        });
    }

}
