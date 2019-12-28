package com.gsau.service.impl;

import com.gsau.config.Config;
import com.gsau.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static java.lang.System.out;

@Service
public class AccountServiceImpl  implements AccountService {
    @Autowired
    public Config config;
    @Override
    public void setAccount() {
            out.println("AccountServiceImpl.java--setAccount--10-->"+ config.getPort());
    }
    @PostConstruct
    public void create(){
        out.println("AccountServiceImpl.java--create--22-->"+ "Service创建了");
    }
    @PreDestroy
    public void desDestroy(){
        out.println("AccountServiceImpl.java--desDestroy--23-->"+ "Service销毁了");
    }
}
