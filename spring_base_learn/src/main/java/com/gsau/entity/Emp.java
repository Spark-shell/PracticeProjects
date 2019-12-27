package com.gsau.entity;
 /**
  * @ Description:  用于测试bean创建的三种方式:使用自定义工厂类
  * @ Date: 2019/12/27 13:37
  * @ Author: wgq
  * @ Version: 1.0
  */
public class Emp {
    public Emp(){
        System.out.println("Emp-Emp-6->"+System.currentTimeMillis());
    }
    public String id="123";
}
