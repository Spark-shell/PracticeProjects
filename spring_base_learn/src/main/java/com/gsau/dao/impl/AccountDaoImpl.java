package com.gsau.dao.impl;

import com.gsau.dao.AccountDao;

import static java.lang.System.out;

public class AccountDaoImpl implements AccountDao {
    @Override
    public void saveAcount() {
        out.println("AccountDaoImpl.java--saveAcount--12-->"+ "请求成功");
    }
}
