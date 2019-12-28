package com.gsau.controller.impl;

import com.gsau.controller.AccountController;
import com.gsau.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class AccountConrollerImpl implements AccountController {
    public String name = "我是Nam";
    @Autowired
    AccountServiceImpl service;
    public void saveAccount() {
            service.setAccount();
    }
}
