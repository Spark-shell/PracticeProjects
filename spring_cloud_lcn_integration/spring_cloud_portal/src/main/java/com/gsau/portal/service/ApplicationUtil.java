package com.gsau.portal.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
/**
 * @author WangGuoQing
 * @date 2019/5/13 21:12
 * @Desc 
 */
@Service("applicationUtil")
public class ApplicationUtil implements ApplicationContextAware{
	
	public static ApplicationContext applicationContext;  
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	 ApplicationUtil.applicationContext = applicationContext;
	}
	public static Object getBean(String name){
	       return applicationContext.getBean(name);
	}
	 
	public static Object getBean(Class<?> cla){
	       return applicationContext.getBean(cla);
	}

}