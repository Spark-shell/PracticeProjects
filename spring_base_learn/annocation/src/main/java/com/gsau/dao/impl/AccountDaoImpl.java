package com.gsau.dao.impl;

import com.gsau.dao.AccountDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import static java.lang.System.out;

/**
  * @ Author: WangGQ
  * @ Date: 2019/12/28 18:26
  * @ Version: 1.0
  * @ Description: 持久层注解
  */
@Scope("singleton")
@Repository
public class AccountDaoImpl implements AccountDao {
    @Override
    public void saveAccount() {
            out.println("AccountDaoImpl.java--saveAccount--18-->"+"持久层保存成功");
    }
}