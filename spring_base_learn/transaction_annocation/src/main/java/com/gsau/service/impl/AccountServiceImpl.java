package com.gsau.service.impl;

import com.gsau.dao.IAccountDao;
import com.gsau.domain.Account;
import com.gsau.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    IAccountDao dao;

    @Override
    public Account findById(int id) {
        Account account=dao.findById(id);
        return account;
    }

    @Override
    public List<Account> findByName(String name) {
        dao.findAccountsByName(name);
        return null;
    }

    @Override
    public void insert(Account account) {
        dao.insert(account);
    }
    public void findAll(){
        dao.findAll();
    }

    @Override
    public void update(Account account) {
        dao.udpate(account);
        int i=1/0;
    }
}
