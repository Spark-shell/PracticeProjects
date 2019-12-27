package com.gsau.service.impl;

import com.gsau.service.SetMethodInjection;

import java.util.Date;

/**
  * @ Description: 依赖注入：set 方法注入,顾名思义就是在类中提供需要注入成员的 set 方法。
  * @ Date: 2019/12/27 16:34
  * @ Author: wgq
  * @ Version: 1.0
  */
public class SetMethodInjectionImpl implements SetMethodInjection {
    private String name;
    private int age;
    private Date birth;
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public void println() {
        System.out.println("SetMethodInjectionImpl-SetMethodInjectionImpl-20->"+this.name+"---"+this.age+"---"+this.birth);
    }
}
