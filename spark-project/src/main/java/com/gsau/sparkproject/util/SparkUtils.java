package com.gsau.sparkproject.util;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.hive.HiveContext;

import com.alibaba.fastjson.JSONObject;
import com.gsau.sparkproject.conf.ConfigurationManager;
import com.gsau.sparkproject.constant.Constants;
import com.gsau.sparkproject.test.MockData;

/**
 * Spark工具类
 *
 * @author Administrator
 */
public class SparkUtils {

    /**
     * 根据当前是否本地测试的配置
     * 决定，如何设置SparkConf的master
     */
    public static void setMaster(SparkConf conf) {
        boolean local = ConfigurationManager.getBoolean(Constants.SPARK_LOCAL);
        if (local) {
            conf.setMaster("local");
        }
    }

    /**
     * 获取SQLContext
     * 如果spark.local设置为true，那么就创建SQLContext；否则，创建HiveContext
     *
     * @param sc
     * @return
     */
    public static SQLContext getSQLContext(SparkContext sc) {
        boolean local = ConfigurationManager.getBoolean(Constants.SPARK_LOCAL);
        if (local) {
            return new SQLContext(sc);
        } else {
            return new HiveContext(sc);
        }
    }

     /**
       * @Description: 生成模拟数据
       * @author: wangguoqing
       * @date: 2018/10/29 14:00
      */
    public static void mockData(JavaSparkContext sc, SQLContext sqlContext) {
        boolean local = ConfigurationManager.getBoolean(Constants.SPARK_LOCAL);
        if (local) {
            MockData.mock(sc, sqlContext);
        }
    }

    /**
     * @Description: 获取指定日期范围内的用户行为数据RDD
     *      sqlContext总的一个过程如下图所示：
     *                1.SQL语句经过SqlParse解析成UnresolvedLogicalPlan；
     *                2.使用analyzer结合数据数据字典（catalog）进行绑定，生成resolvedLogicalPlan；
     *                3.使用optimizer对resolvedLogicalPlan进行优化，生成optimizedLogicalPlan；
     *                4.使用SparkPlan将LogicalPlan转换成PhysicalPlan；
     *                5.使用prepareForExecution()将PhysicalPlan转换成可执行物理计划；
     *                6.使用execute()执行可执行物理计划；
     *                7.生成SchemaRDD。
     *      hiveContext总的一个过程如下图所示：
     *                1.SQL语句经过HiveQl.parseSql解析成Unresolved LogicalPlan，在这个解析过程中对hiveql语句使用getAst()获取AST树，然后再进行解析；
     *                2.使用analyzer结合数据hive源数据Metastore（新的catalog）进行绑定，生成resolved LogicalPlan；
     *                3.使用optimizer对resolved LogicalPlan进行优化，生成optimized LogicalPlan，优化前使用了ExtractPythonUdfs(catalog.PreInsertionCasts(catalog.CreateTables(analyzed)))进行预处理；
     *                4.使用hivePlanner将LogicalPlan转换成PhysicalPlan；
     *                5.使用prepareForExecution()将PhysicalPlan转换成可执行物理计划；
     *                6.使用execute()执行可执行物理计划；
     *                7.执行后，使用map(_.copy)将结果导入SchemaRDD
     *      DataFrame是什么？
     *          是spark在内存中存数据的一个机制（数据结构），是来存储sparkSQL把数据读入到内存中，数据在内存中进行存储的基本数据结构。它采用的存储是类似于数据库的表的形式进行存储的。
     *     我们想一想，一个数据表有几部分组成：
     *              1、数据，这个数据是一行一行进行存储的，一条记录就是一行，
     *              2、数据表的数据字典，包括表的名称，表的字段和字段的类型等元数据信息。
     *              那么DataFrame也是按照行进行存储的，这个类是Row，一行一行的进行数据存储。
     *              一般情况下处理粒度是行粒度的，不需要对其行内数据进行操作，如果想单独操作行内数据也是可以的，
     *              只是在处理的时候要小心，因为处理行内的数据容易出错，比如选错数据，数组越界等。数据的存储的形式有了，
     *              数据表的字段和字段的类型都存放在哪里呢，就是schema中。我们可以调用schema来看其存储的是什么。
     * @author: wangguoqing
     * @date: 2018/10/29 13:40
     */
    public static JavaRDD<Row> getActionRDDByDateRange(
            SQLContext sqlContext, JSONObject taskParam) {
        String startDate = ParamUtils.getParam(taskParam, Constants.PARAM_START_DATE);
        String endDate = ParamUtils.getParam(taskParam, Constants.PARAM_END_DATE);
        String sql = "select * from user_visit_action where date>='" + startDate + "' and date<='" + endDate + "'";
        DataFrame actionDF = sqlContext.sql(sql);                                      //执行sql，从sqlContext中查取，指定日期范围内的用户行为数据
//		return actionDF.javaRDD().repartition(1000);

        return actionDF.javaRDD();
    }

}
