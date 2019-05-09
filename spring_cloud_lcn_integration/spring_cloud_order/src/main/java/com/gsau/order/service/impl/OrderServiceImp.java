package com.gsau.order.service.impl;

import com.gsau.order.mapper.OrdersMapper;
import com.gsau.order.service.OrdersService;
import com.gsau.order_sersvice.projo.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WangGuoQing
 * @date 2019/5/8 13:48
 * @Desc 添加
 */
@Service
public class OrderServiceImp implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public void addOrders(Orders orders) {
        ordersMapper.insertOrders(orders);
    }
}
