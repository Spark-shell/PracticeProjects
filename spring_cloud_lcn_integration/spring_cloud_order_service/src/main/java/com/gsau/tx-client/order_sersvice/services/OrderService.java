package com.gsau.order_sersvice.services;

import com.gsau.order_sersvice.projo.Orders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author WangGuoQing
 * @date 2019/5/8 13:22
 * @Desc 微服务接口
 */
public interface OrderService {
    /**
     * @author WangGuoQing
     * @date 2019/5/8 13:22
     * @Desc 添加订单
     */
    @RequestMapping(value = "/addOrder",method= RequestMethod.POST)
    public void addOder(@RequestBody Orders order);

}
