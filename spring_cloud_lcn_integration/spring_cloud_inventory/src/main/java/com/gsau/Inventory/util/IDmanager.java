package com.gsau.Inventory.util;

import java.util.UUID;

/**
 * @author WangGuoQing
 * @date 2019/5/13 17:38
 * @Desc ID
 */
public class IDmanager {
	
	public static String  createID(){
		String uuID = UUID.randomUUID().toString().replaceAll("-", "");
		return uuID;
	}
	
	
}
