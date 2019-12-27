package com.gsau.client;

import com.gsau.entity.Person;
import com.gsau.factory.BeanFactory;
import com.gsau.factory.SpringBeanFactory;
import com.gsau.service.impl.AccountServiceImpl;

public class test1 {
    public static void main(String[] args) {
//        AccountServiceImpl service= (AccountServiceImpl) BeanFactory.getBean("accountServiceImpl");
        AccountServiceImpl service= SpringBeanFactory.getBean("accountServiceImpl",AccountServiceImpl.class);
        Person person=SpringBeanFactory.getBean("person", Person.class);
        System.out.println("test1-test1-13->"+person.toString());
        service.saveAccount();
    }
}
