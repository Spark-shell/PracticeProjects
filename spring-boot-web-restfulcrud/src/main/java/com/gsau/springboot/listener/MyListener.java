package com.gsau.springboot.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * @author WangGuoQing
 * @date 2019/4/18 17:06
 * @Desc 自定义Servlet的监听器
 */
public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized...web应用启动");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed...当前web项目销毁");
    }
}
