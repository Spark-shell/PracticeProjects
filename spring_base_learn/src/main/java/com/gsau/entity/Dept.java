package com.gsau.entity;

 /**
  * @ Description:  用于测试bean创建的三种方式: 使用实例工厂的方法创建对象
  * @ Date: 2019/12/27 14:13
  * @ Author: wgq
  * @ Version: 1.0
  */
public class Dept {
    public String name="部门1";
    public Dept(){
        System.out.println("Dept-Dept-6->"+System.currentTimeMillis());
    }
}
