package com.gsau.service;

import com.gsau.entity.Account;

import java.util.List;

public interface AccountService {
    public void setAccount();
    List<Account> findByName(String name);
}
