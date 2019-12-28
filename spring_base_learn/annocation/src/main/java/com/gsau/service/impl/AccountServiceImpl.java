package com.gsau.service.impl;

import com.gsau.config.Config;
import com.gsau.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.System.out;

@Service
public class AccountServiceImpl  implements AccountService {
    @Autowired
    public Config config;
    @Override
    public void setAccount() {
            out.println("AccountServiceImpl.java--setAccount--10-->"+ config.getPort());
    }
}
