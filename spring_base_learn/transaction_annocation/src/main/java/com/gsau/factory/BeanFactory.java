package com.gsau.factory;

import com.gsau.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
  * @ Author: WangGQ
  * @ Date: 2019/12/29 20:09
  * @ Version: 1.0
  * @ Description: 自定义容器出口
  */
public class BeanFactory {
    // private static ClassPathXmlApplicationContext ioc =null;
    private static AnnotationConfigApplicationContext ioc =null;

    static {
        ioc=new AnnotationConfigApplicationContext(SpringConfig.class);
    }
     /**
      * @ Author: WangGQ
      * @ Date: 2019/12/29 20:14
      * @ Version: 1.0
      * @ Description: 获取容器中的bean
      */
    public static  <T> T getBean(String beanName,Class<T> cls){
           return ioc.getBean(beanName,cls);
    }
}
