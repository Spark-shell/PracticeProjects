package com.gsau.factory;

import com.gsau.entity.Emp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ Description:  使用spring容器获取bean实例
 * @ Date: 2019/12/27 9:57
 * @ Author: wgq
 * @ Version: 1.0
 */
public class SpringBeanFactory {
    public static ApplicationContext ioc=null;
     /**
      * @ Description:  初始初始化Spring容器
      * @ Date: 2019/12/27 10:22
      * @ Author: wgq
      * @ Version: 1.0
      */
    static {
        ioc= new ClassPathXmlApplicationContext("SpringBeans.xml");
    }
     /**
      * @ Description:  取容器中组件 
      * @ Date: 2019/12/27 10:22
      * @ Author: wgq
      * @ Version: 1.0
      */
    public static Object getBean(String beanName){
       return  ioc.getBean(beanName);
    }
    public static  < obj > obj getBean(String beanName,Class<obj> obj){
        return  ioc.getBean(beanName,obj);
    }
     /**
      * @ Description:   用于测试bean创建的三种方式:使用自定义工厂类 
      * @ Date: 2019/12/27 13:43
      * @ Author: wgq
      * @ Version: 1.0
      */
    public static Object instanceBean(){
        return new Emp();
    }
}
