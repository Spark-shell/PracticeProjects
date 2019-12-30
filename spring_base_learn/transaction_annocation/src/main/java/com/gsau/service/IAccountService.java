package com.gsau.service;

import com.gsau.domain.Account;

import java.util.List;

/**
  * @ Description:  业务层接口
  * @ Date: 2019/12/30 11:28
  * @ Author: wgq
  * @ Version: 1.0
  */
public interface IAccountService {
    Account findById(int id);
    void update(Account account);
    List<Account> findByName(String name);
    void insert(Account account);
    void findAll();
}
