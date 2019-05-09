package com.gsau.sparkproject.spark.product;

import org.apache.spark.sql.api.java.UDF2;

import com.alibaba.fastjson.JSONObject;

/**
  * @Description: 技术点：自定义UDF函数  get_json_object()
  * @author: wangguoqing
  * @date: 2018/11/13 14:39
  * @Version：1.0.0
 */
public class GetJsonObjectUDF implements UDF2<String, String, String> {
	private static final long serialVersionUID = 1L;
	@Override
	public String call(String json, String field) throws Exception {
		try {
			JSONObject jsonObject = JSONObject.parseObject(json);
			return jsonObject.getString(field);
		} catch (Exception e) {
			e.printStackTrace();  
		}
		return null;
	}
}
