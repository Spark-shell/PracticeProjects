package com.gsau.dao.impl;

import com.gsau.dao.IAccountDao;
import com.gsau.domain.Account;
import com.gsau.utils.AccountMapper;
import com.gsau.utils.JdbcDaoSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.System.out;

@Service("accountDao")
@Scope("singleton")
public class AccountDaoImpl extends JdbcDaoSupport implements IAccountDao {
    @Override
    public List<Account> findAccountsByName(String name) {
        out.println("AccountDaoImpl.java--findAccountsByName--14-->"+ super.getJdbcTemplate().query("select * from account where name=?",new AccountMapper(),name));
        return null;
    }

    @Override
    public Account findById(int id) {
        out.println("AccountDaoImpl-AccountDaoImpl-27->"+super.getJdbcTemplate().query("select * from account where id=? ",new AccountMapper(),id));
        return null;
    }

    @Override
    public int insert(Account account) {
        return super.getJdbcTemplate().update("insert into account (name,price) values (?,?)",account.getName(),account.getPrice());
    }
    @Override
    public List<Account> findAll(){
        out.println("AccountDaoImpl-AccountDaoImpl-36->"+super.getJdbcTemplate().query("select * from account",new BeanPropertyRowMapper<Account>(Account.class)));
        return null;
    }
}
