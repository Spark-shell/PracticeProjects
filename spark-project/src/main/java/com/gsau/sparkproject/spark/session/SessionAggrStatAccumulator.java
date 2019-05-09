package com.gsau.sparkproject.spark.session;

import com.gsau.sparkproject.util.StringUtils;
import org.apache.spark.AccumulatorParam;

import com.gsau.sparkproject.constant.Constants;

/**
 * session聚合统计Accumulator（累加器）
 * 
 * 大家可以看到
 * 其实使用自己定义的一些数据格式，比如String，甚至说，我们可以自己定义model，自己定义的类（必须可序列化）
 * 然后呢，可以基于这种特殊的数据格式，可以实现自己复杂的分布式的计算逻辑
 * 各个task，分布式在运行，可以根据你的需求，task给Accumulator传入不同的值
 * 根据不同的值，去做复杂的逻辑
 * 
 * Spark Core里面很实用的高端技术
 *
 * @author Administrator
 *
 */
public class SessionAggrStatAccumulator implements AccumulatorParam<String> {

	private static final long serialVersionUID = 6311074555136039130L;
	
	 /**
	   * @Description:
	   *     zero方法，其实主要用于数据的初始化
	   * @author: wangguoqing
	   * @date: 2018/10/29 15:36
	  */
	@Override
	public String zero(String v) {
		return Constants.SESSION_COUNT + "=0|"
				+ Constants.TIME_PERIOD_1s_3s + "=0|"
				+ Constants.TIME_PERIOD_4s_6s + "=0|"
				+ Constants.TIME_PERIOD_7s_9s + "=0|"
				+ Constants.TIME_PERIOD_10s_30s + "=0|"
				+ Constants.TIME_PERIOD_30s_60s + "=0|"
				+ Constants.TIME_PERIOD_1m_3m + "=0|"
				+ Constants.TIME_PERIOD_3m_10m + "=0|"
				+ Constants.TIME_PERIOD_10m_30m + "=0|"
				+ Constants.TIME_PERIOD_30m + "=0|"
				+ Constants.STEP_PERIOD_1_3 + "=0|"
				+ Constants.STEP_PERIOD_4_6 + "=0|"
				+ Constants.STEP_PERIOD_7_9 + "=0|"
				+ Constants.STEP_PERIOD_10_30 + "=0|"
				+ Constants.STEP_PERIOD_30_60 + "=0|"
				+ Constants.STEP_PERIOD_60 + "=0";
	}
	 /**
	   * @Description:
	   * @author: wangguoqing
	   * @date: 2018/10/29 17:29
	  */
	@Override
	public String addInPlace(String v1, String v2) {
		return add(v1, v2);
	}
	
	@Override
	public String addAccumulator(String v1, String v2) {
		return add(v1, v2);
	}  
	
	/**
	 * session 统计计算逻辑
	 * @param v1 连接串
	 * @param v2 范围区间
	 * @return 更新以后的连接串
	 */
	private String add(String v1, String v2) {
		if(StringUtils.isEmpty(v1)) {                                                                     // 校验：v1为空的话，直接返回v2
			return v2;
		}
		String oldValue = StringUtils.getFieldFromConcatString(v1, "|", v2);                    // 使用StringUtils工具类，从v1中，提取v2对应的值，并累加1
		if(oldValue != null) {
			int newValue = Integer.valueOf(oldValue) + 1;                                                 // 将范围区间原有的值，累加1
			return StringUtils.setFieldInConcatString(v1, "|", v2, String.valueOf(newValue));   // 使用StringUtils工具类，将v1中，v2对应的值，设置成新的累加后的值
		}
		return v1;
	}
	
}
