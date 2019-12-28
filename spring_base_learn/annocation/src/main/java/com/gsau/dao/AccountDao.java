package com.gsau.dao;

import com.gsau.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AccountDao {
    void saveAccount();
    Account updateAccount(Account account);
    List<Account> findByName(String name);
}
