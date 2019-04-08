package com.gsau.springBootTemplate.aop;
import com.gsau.springBootTemplate.util.LogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * aop拦截controller
 * @author Wgq
 */
@Component
@Aspect
public class DemoAop {
    @Pointcut("execution(* com.gsau.springBootTemplate.controller.*.*(..))")
    private void logPointcut(){}
    @Before("logPointcut()")
    public void before(JoinPoint joinPoint){
        LogUtil.printLog("AOP的before方法执行");
    }
}
