package com.gsau.service.impl;

import com.gsau.dao.AccountDao;
import com.gsau.dao.impl.AccountDaoImpl;
import com.gsau.factory.BeanFactory;
import com.gsau.service.AccountService;

import static java.lang.System.out;

public class AccountServiceImpl implements AccountService {
    private AccountDaoImpl dao= (AccountDaoImpl) BeanFactory.getBean("accountDaoImpl");
    @Override
    public void saveAccount() {
        dao.saveAcount();
        out.printf("AccountServiceImpl.java--saveAccount--10--> ","保存成功");
    }
}
