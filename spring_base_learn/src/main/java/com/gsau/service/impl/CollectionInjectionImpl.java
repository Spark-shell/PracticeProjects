package com.gsau.service.impl;

import com.gsau.entity.Dept;
import com.gsau.service.CollectionInjection;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ Description:  依赖注入：集合的注入
 * @ Date: 2019/12/27 17:23
 * @ Author: wgq
 * @ Version: 1.0
 */
public class CollectionInjectionImpl implements CollectionInjection {
    private String name;
    private Map<String,Object> injections;
    private List<String> depts;
    private Set<Integer> nums;

    public void setName(String name) {
        this.name = name;
    }

    public void setInjections(Map<String, Object> injections) {
        this.injections = injections;
    }

    public void setDepts(List<String> depts) {
        this.depts = depts;
    }

    public void setNums(Set<Integer> nums) {
        this.nums = nums;
    }

    @Override
    public void println() {
        System.out.println("CollectionInjectionImpl-CollectionInjectionImpl-21->"+depts+"---"+injections);
    }
}
