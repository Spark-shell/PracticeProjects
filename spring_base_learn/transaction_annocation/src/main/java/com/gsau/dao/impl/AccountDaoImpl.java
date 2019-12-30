package com.gsau.dao.impl;

import com.gsau.dao.IAccountDao;
import com.gsau.domain.Account;
import com.gsau.utils.AccountMapper;
import com.gsau.utils.JdbcDaoSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.System.out;

@Service("accountDao")
@Scope("singleton")
public class AccountDaoImpl extends JdbcDaoSupport implements IAccountDao {
    @Override
    public List<Account> findAccountsByName(String name) {
        out.println("AccountDaoImpl.java--findAccountsByName--14-->"+ super.getJdbcTemplate().query("select * from account",new AccountMapper()));
        return null;
    }
    public List<Account> findAll(){
        return super.getJdbcTemplate().query("select * from account",new BeanPropertyRowMapper<Account>(Account.class));
    }
}
