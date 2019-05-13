package com.gsau.order.controller;

import com.gsau.order.service.OrdersService;
import com.gsau.order_sersvice.projo.Orders;
import com.gsau.order_sersvice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController implements OrderService {
    @Autowired
    private OrdersService ordersService;

    /**
     * 添加一个Order
     *      @Transactional 开启事务
     * @param order
     */
    @Override
    @Transactional
    public void addOder(@RequestBody Orders order) {
        ordersService.addOrders(order);
    }
    @RequestMapping("/test")
    public String access() {
        return "Order访问成功";
    }
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
