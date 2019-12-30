package com.gsau.utils;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import static java.lang.System.out;

@Component("txManager")
@Aspect
public class TxManager {
    @Pointcut("execution(* com.gsau.service.impl.*.*(..))")
    public void pointCut(){}
    @Before("pointCut()")
    public void beforeNotice() {
        out.println("TxManager.java--beforeNotice--14-->" + "前置通知");
    }
    public void afterNotice() {
        out.println("TxManager.java--afterNotice--17-->" + "后置通知");
    }
    public void exceptionNotice() {
        out.println("TxManager.java--exceptionNotice--20-->" + "异常通知");
    }
    public void finallyNotice() {
        out.println("TxManager.java--finallyNotice--23-->" + "最终通知");
    }
    public void aroundNotice() {
        out.println("TxManager.java--aroundNotice--26-->" + "环绕通知");
    }
}
