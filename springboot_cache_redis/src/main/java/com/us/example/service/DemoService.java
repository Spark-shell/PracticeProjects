package com.us.example.service;

import com.us.example.bean.Person;

/**
 * @author WangGuoQing
 * @date 2019/4/9 22:52
 * @Desc 数据访问层接口
 */
public  interface DemoService {
    //插入
    public Person save(Person person);
    //删除
    public void remove(Long id);
    //根据ID进行查找
    public Person findOne(Person person);

}
