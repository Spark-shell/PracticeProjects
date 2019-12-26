package com.gsau.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @ Author: WangGQ
 * @ Date: 2019/12/26 20:37
 * @ Version: 1.0
 * @ Description: 创建对象工厂
 */
public class BeanFactory {
    public static Properties pros;
    public static Map<String,Object> beans;
    static {
        try {
            pros = new Properties();
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("beans.properties");
            pros.load(in);
            //----改造----start---
            Enumeration enums=pros.keys();
            beans=new HashMap<String,Object>();         //实例化容器
            while (enums.hasMoreElements()){
                try {
                    String key    = (String) enums.nextElement();
                    String keyPath=pros.getProperty(key);
                    Object obj=Class.forName(keyPath).newInstance();
                    beans.put(key,obj);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            //----改造----end---
        } catch (IOException e) {
            throw new ExceptionInInitializerError("读取配置文件失败");
        }
    }
     /**
      * @ Author: WangGQ
      * @ Date: 2019/12/26 21:04
      * @ Version: 1.0
      * @ Description: 获取bean,Class.forName(beanPath).newInstance()调用的是类的默认的构造方法，所以创建的bean是多例的
      */
    public static Object getBean(String beanName){
        Object obj = null;
            try{
                String beanPath=pros.getProperty(beanName);
                obj=Class.forName(beanPath).newInstance();
            }catch (Exception e){
                    e.printStackTrace();
            }
        return obj;
    }
    public static Object createBean(String beanName){
        return beans.get(beanName);
    }
}
