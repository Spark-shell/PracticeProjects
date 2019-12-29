package com.gsau.dao.impl;

import com.gsau.dao.IAccountDao;
import com.gsau.domain.Account;
import com.gsau.utils.AccountMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static java.lang.System.out;

public class AccountDaoImpl implements IAccountDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Account> findAccountsByName(String name) {
        out.println("AccountDaoImpl.java--findAccountsByName--14-->"+ jdbcTemplate.query("select * from account",new AccountMapper()));
        return null;
    }
}
