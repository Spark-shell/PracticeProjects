package com.gsau.order.controller;

import com.gsau.order.service.UserInfoService;
import com.gsau.order_sersvice.projo.Orders;
import com.gsau.order_sersvice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController implements OrderService {
    @Autowired
    private UserInfoService userInfoService;

    @Override
    @Transactional
    public void addOder(@RequestBody Orders order) {
        userInfoService.findByUserId(1);
    }

}
