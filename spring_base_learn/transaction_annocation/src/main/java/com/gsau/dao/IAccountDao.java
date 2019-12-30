package com.gsau.dao;

import com.gsau.domain.Account;

import java.util.List;

/**
  * @ Author: WangGQ
  * @ Date: 2019/12/29 20:28
  * @ Version: 1.0
  * @ Description:
  */
public interface IAccountDao {
    List<Account> findAccountsByName(String name);
    Account findById(int id);
    int insert(Account account);
    List<Account> findAll();
}
