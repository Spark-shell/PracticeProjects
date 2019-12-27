package com.gsau.service.impl;

import com.gsau.service.ConstructInjection;

import java.util.Date;

import static java.lang.System.out;

/**
 * @ Description:  依赖注入：使用构造函数注入
 * @ Date: 2019/12/27 15:32
 * @ Author: wgq
 * @ Version: 1.0
 */
public class ConstructInjectionImpl implements ConstructInjection {
    private String field1;
    private int field2;
    private Date field3;
    /**
     * @ Description:  使用构造函数注入 ,如果是经常变化的数据不适用构造函数注入的方式
     * @ Date: 2019/12/27 15:28
     * @ Author: wgq
     * @ Version: 1.0
     */
    public ConstructInjectionImpl(String field1, Integer field2, Date field3){
        this.field1=field1;
        this.field2=field2;
        this.field3=field3;
    }
    @Override
    public void println() {
        out.println("AccountServiceImpl-AccountServiceImpl-41->"+this.field1+"---"+this.field2+"---"+this.field3);
    }
}
