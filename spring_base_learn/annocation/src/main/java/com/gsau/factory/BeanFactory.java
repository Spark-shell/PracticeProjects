package com.gsau.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
  * @ Author: WangGQ
  * @ Date: 2019/12/28 16:51
  * @ Version: 1.0
  * @ Description: 使用spring容器获取bean实例
  */
public class BeanFactory {
    // private static ApplicationContext ioc=null;
    private static ClassPathXmlApplicationContext ioc=null;
    static {
        ioc=new ClassPathXmlApplicationContext("SpringBeans.xml");
    }
     /**
      * @ Author: WangGQ
      * @ Date: 2019/12/28 16:57
      * @ Version: 1.0
      * @ Description: 获取bean实例
      */
    public static <T> T getBean(String beanName,Class<T> cls){
            return ioc.getBean(beanName,cls);
    }
     /**
      * @ Author: WangGQ
      * @ Date: 2019/12/28 18:51
      * @ Version: 1.0
      * @ Description: 关闭容器 
      */
    public static void close(){
        ioc.close();
    }
}
