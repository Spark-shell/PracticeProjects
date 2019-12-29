package com.gsau.cglib;
import static java.lang.System.out;
/**
  * @ Author: WangGQ
  * @ Date: 2019/12/29 15:33
  * @ Version: 1.0
  * @ Description: 被代理的子类
  */
public class Producer {
    public void method1(String name){
        out.println("Producer.java--method1--13-->"+ name);
    }
    public void method2(String name,int id){
        out.println("Producer.java--method1--13-->"+ name+"--"+id);
    }
}
