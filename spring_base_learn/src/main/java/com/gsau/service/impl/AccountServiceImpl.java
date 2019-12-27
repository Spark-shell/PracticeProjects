package com.gsau.service.impl;

import com.gsau.dao.AccountDao;
import com.gsau.dao.impl.AccountDaoImpl;
import com.gsau.factory.BeanFactory;
import com.gsau.service.AccountService;

import java.util.Date;

import static java.lang.System.out;


public class AccountServiceImpl implements AccountService {
    private AccountDaoImpl dao= (AccountDaoImpl) BeanFactory.getBean("accountDaoImpl");
    public AccountServiceImpl() {
        out.println("AccountServiceImpl-AccountServiceImpl-对象创建->"+System.currentTimeMillis());
    }

    @Override
    public void saveAccount() {
        dao.saveAcount();
        out.println("AccountServiceImpl-AccountServiceImpl-16->"+System.currentTimeMillis());
    }
}
