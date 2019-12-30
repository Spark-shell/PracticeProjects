package com.gsau.dao.impl;

import com.gsau.dao.IAccountDao;
import com.gsau.domain.Account;
import com.gsau.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.System.out;

@Service("accountDao")
@Scope("singleton")
public class AccountDaoImpl implements IAccountDao {
    @Autowired
    private ConnectionUtils connectionUtils;
    @Autowired
    private QueryRunner runner;
    @Override
    public List<Account> findAccountsByName(String name) {
        try{
            return runner.query(connectionUtils.getThreadConnection(),"select * from account where name=?",new BeanListHandler<Account>(Account.class),name);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findById(int id) {
        try{
            return runner.query(connectionUtils.getThreadConnection(),"select * from account where id=?",new BeanListHandler<Account>(Account.class),id).get(0);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(Account account) {
        try{
            return runner.update(connectionUtils.getThreadConnection(),"insert into account (name,price) values (?,?)",account.getName(),account.getPrice());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Account> findAll(){
        try{
            return runner.query(connectionUtils.getThreadConnection(),"select * from account",new BeanListHandler<Account>(Account.class));
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void udpate(Account account) {
        int id=account.getId();
        Account ac= this.findById(id);
        if(ac!=null){
            try{
               runner.update(connectionUtils.getThreadConnection(),"update account set name=?,price=? where id=?",account.getName(),account.getPrice(),account.getId());
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else{
            out.println("AccountDaoImpl-AccountDaoImpl-46->"+"更新的账户不存在");
        }

    }
}
