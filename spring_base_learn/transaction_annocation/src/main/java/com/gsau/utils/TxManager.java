package com.gsau.utils;

import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.lang.System.out;

 /**
  * @ Description:  和事务管理相关的工具类，它包含了，开启事务，提交事务，回滚事务和释放连接
  * @ Date: 2019/12/30 14:14
  * @ Author: wgq
  * @ Version: 1.0
  */
@Component("txManager")
@Aspect
public class TxManager {
    @Autowired
     private ConnectionUtils connectionUtils;
    @Pointcut("execution(* com.gsau.service.impl.*.*(..))")
    public void pointCut(){}
     /**
      * @ Description: 开启事务   
      * @ Date: 2019/12/30 14:14
      * @ Author: wgq
      * @ Version: 1.0
      */
     @Before("pointCut()")
    public  void beginTransaction(){
        try {
            out.println("TxManager-TxManager-33->"+"开启事务");
            connectionUtils.getThreadConnection().setAutoCommit(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

     /**
      * @ Description:  提交事务
      * @ Date: 2019/12/30 14:15
      * @ Author: wgq
      * @ Version: 1.0
      */
     @AfterReturning("pointCut()")
    public  void commit(){
        try {
            out.println("TxManager-TxManager-50->"+"提交事务");
            connectionUtils.getThreadConnection().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

     /**
      * @ Description:  回滚事务
      * @ Date: 2019/12/30 14:15
      * @ Author: wgq
      * @ Version: 1.0
      */
     @AfterThrowing("pointCut()")
    public  void rollback(){
        try {
            out.println("TxManager-TxManager-65->"+"回滚事务");
            connectionUtils.getThreadConnection().rollback();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

     /**
      * @ Description:  释放连接
      * @ Date: 2019/12/30 14:15
      * @ Author: wgq
      * @ Version: 1.0
      */
    public  void release(){
        try {
            connectionUtils.getThreadConnection().close();//还回连接池中
            connectionUtils.removeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
     @Around("pt1()")
     public Object aroundAdvice(ProceedingJoinPoint pjp){
         Object rtValue = null;
         try {
             //1.获取参数
             Object[] args = pjp.getArgs();
             //2.开启事务
             this.beginTransaction();
             //3.执行方法
             rtValue = pjp.proceed(args);
             //4.提交事务
             this.commit();

             //返回结果
             return  rtValue;

         }catch (Throwable e){
             //5.回滚事务
             this.rollback();
             throw new RuntimeException(e);
         }finally {
             //6.释放资源
             this.release();
         }
     }
}

