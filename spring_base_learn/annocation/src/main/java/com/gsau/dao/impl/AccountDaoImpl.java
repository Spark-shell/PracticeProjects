package com.gsau.dao.impl;

import com.gsau.dao.AccountDao;
import com.gsau.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void saveAccount() {
            out.println("AccountDaoImpl.java--saveAccount--18-->"+"持久层保存成功");

    }
    @Override
    public Account updateAccount(Account account) {
        List<Account> accounts = jdbcTemplate.query("select * from account where name = ?",new BeanPropertyRowMapper<Account>(Account.class),account.getName());
        if(accounts.isEmpty()){
            return null;
        }
        if(accounts.size()>1){
            throw new RuntimeException("结果集不唯一");
        }
        return accounts.get(0);
    }
    @Override
    public List<Account> findByName(String name) {
        List<Account> accounts = jdbcTemplate.query("select * from account where name = ?",new BeanPropertyRowMapper<Account>(Account.class),name);
        return accounts.isEmpty()?null:accounts;
    }
}