package com.gsau.utils;

import static java.lang.System.out;

/**
 * @ Author: WangGQ
 * @ Date: 2019/12/29 21:12
 * @ Version: 1.0
 * @ Description: aop切面通知
 */
public class Logger {
    public void beforeNotice() {
        out.println("Logger.java--beforeNotice--14-->" + "前置通知");
    }
    public void afterNotice() {
        out.println("Logger.java--afterNotice--17-->" + "后置通知");
    }
    public void exceptionNotice() {
        out.println("Logger.java--exceptionNotice--20-->" + "异常通知");
    }
    public void finallyNotice() {
        out.println("Logger.java--finallyNotice--23-->" + "最终通知");
    }
    public void aroundNotice() {
        out.println("Logger.java--aroundNotice--26-->" + "环绕通知");
    }
}
