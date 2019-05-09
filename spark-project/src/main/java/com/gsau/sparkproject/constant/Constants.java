package com.gsau.sparkproject.constant;

 /**
   * @Description: 常量接口
   * @author: wangguoqing
   * @date: 2018/10/29 9:37
  */
public interface Constants {

	 /**
	   * @Description: 项目配置相关的常量
	   * @author: wangguoqing
	   * @date: 2018/10/29 9:38
	  */
	String JDBC_DRIVER = "jdbc.driver";
	String JDBC_DATASOURCE_SIZE = "jdbc.datasource.size";
	String JDBC_URL = "jdbc.url";
	String JDBC_USER = "jdbc.user";
	String JDBC_PASSWORD = "jdbc.password";
	String JDBC_URL_PROD = "jdbc.url.prod";
	String JDBC_USER_PROD = "jdbc.user.prod";
	String JDBC_PASSWORD_PROD = "jdbc.password.prod";
	String SPARK_LOCAL = "spark.local";
	String SPARK_LOCAL_TASKID_SESSION = "spark.local.taskid.session";
	String  SPARK_LOCAL_TASKID_PAGE = "spark.local.taskid.page";
	String SPARK_LOCAL_TASKID_PRODUCT = "spark.local.taskid.product";
	String KAFKA_METADATA_BROKER_LIST = "kafka.metadata.broker.list";
	String KAFKA_TOPICS = "kafka.topics";
	
	 /**
	   * @Description: Spark作业相关的常量
	   * @author: wangguoqing
	   * @date: 2018/10/29 9:38
	  */
	String SPARK_APP_NAME_SESSION = "UserVisitSessionAnalyzeSpark";                                                //
	String SPARK_APP_NAME_PAGE = "PageOneStepConvertRateSpark";                                                    //
	String FIELD_SESSION_ID = "sessionid";                                                                         //访问的sessionID
	String FIELD_SEARCH_KEYWORDS = "searchKeywords";                                                               //搜索过得关键词
	String FIELD_CLICK_CATEGORY_IDS = "clickCategoryIds";                                                          //点击的品类
	String FIELD_AGE = "age";                                                                                      //年龄
	String FIELD_PROFESSIONAL = "professional";                                                                    //专业
	String FIELD_CITY = "city";                                                                                    //城市
	String FIELD_SEX = "sex";                                                                                      //性别
	String FIELD_VISIT_LENGTH = "visitLength";                                                                     //访问深度
	String FIELD_STEP_LENGTH = "stepLength";                                                                       //步长
	String FIELD_START_TIME = "startTime";                                                                         //开始时间
	String FIELD_CLICK_COUNT = "clickCount";                                                                       //点击总数
	String FIELD_ORDER_COUNT = "orderCount";                                                                       //订单总数
	String FIELD_PAY_COUNT = "payCount";                                                                           //支付总数
	String FIELD_CATEGORY_ID = "categoryid";                                                                       //品类
	                                                                                                               //
	String SESSION_COUNT = "session_count";                                                                        //session总数
	
	String TIME_PERIOD_1s_3s = "1s_3s";                                                                             //时间长度区间：
	String TIME_PERIOD_4s_6s = "4s_6s";                                                                             //时间长度区间：
	String TIME_PERIOD_7s_9s = "7s_9s";                                                                             //时间长度区间：
	String TIME_PERIOD_10s_30s = "10s_30s";                                                                         //时间长度区间：
	String TIME_PERIOD_30s_60s = "30s_60s";                                                                         //时间长度区间：
	String TIME_PERIOD_1m_3m = "1m_3m";                                                                             //时间长度区间：
	String TIME_PERIOD_3m_10m = "3m_10m";                                                                           //时间长度区间：
	String TIME_PERIOD_10m_30m = "10m_30m";                                                                         //时间长度区间：
	String TIME_PERIOD_30m = "30m";                                                                                 //时间长度区间：
	
	String STEP_PERIOD_1_3 = "1_3";                                                                                 //访问步长区间：
	String STEP_PERIOD_4_6 = "4_6";                                                                                 //访问步长区间：
	String STEP_PERIOD_7_9 = "7_9";                                                                                 //访问步长区间：
	String STEP_PERIOD_10_30 = "10_30";                                                                             //访问步长区间：
	String STEP_PERIOD_30_60 = "30_60";                                                                             //访问步长区间：
	String STEP_PERIOD_60 = "60";                                                                                   //访问步长区间：
	
	 /**
	   * @Description: 任务相关的常量
	   * @author: wangguoqing
	   * @date: 2018/10/29 9:37
	  */
	String PARAM_START_DATE = "startDate";                                                                            //开始日期
	String PARAM_END_DATE = "endDate";                                                                                //结束日期
	String PARAM_START_AGE = "startAge";                                                                              //开始年龄
	String PARAM_END_AGE = "endAge";                                                                                  //结束年龄
	String PARAM_PROFESSIONALS = "professionals";                                                                     //专业
	String PARAM_CITIES = "cities";                                                                                   //城市
	String PARAM_SEX = "sex";                                                                                         //性别
	String PARAM_KEYWORDS = "keywords";                                                                               //关键词
	String PARAM_CATEGORY_IDS = "categoryIds";                                                                        //品类
	String PARAM_TARGET_PAGE_FLOW = "targetPageFlow";                                                                 //用户指定的页面流
	
}
