package com.gsau.client;

import com.gsau.factory.BeanFactory;
import com.gsau.service.impl.AccountServiceImpl;

public class test1 {
    public static void main(String[] args) {
//        AccountServiceImpl service= (AccountServiceImpl) BeanFactory.getBean("accountServiceImpl");
        AccountServiceImpl service= (AccountServiceImpl) BeanFactory.createBean("accountServiceImpl");
        service.saveAccount();
    }
}
