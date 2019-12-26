package com.gsau.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ Author: WangGQ
 * @ Date: 2019/12/26 20:37
 * @ Version: 1.0
 * @ Description: 创建对象工厂
 */
public class BeanFactory {
    public static Properties pros;
    static {
        try {
            pros = new Properties();
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("beans.properties");
            pros.load(in);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("读取配置文件失败");
        }
    }
     /**
      * @ Author: WangGQ
      * @ Date: 2019/12/26 21:04
      * @ Version: 1.0
      * @ Description: 获取bean
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
}
