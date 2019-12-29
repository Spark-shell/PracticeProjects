package com.gsau.factory;

import com.gsau.config.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnocationBeanFactory {
    // private static ApplicationContext ioc=null;
    private static AnnotationConfigApplicationContext ioc=null;
    static {
        ioc = new AnnotationConfigApplicationContext(SpringConfig.class);
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
