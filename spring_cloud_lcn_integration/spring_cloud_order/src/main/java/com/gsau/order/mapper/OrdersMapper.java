package com.gsau.order.mapper;

import com.gsau.order_sersvice.projo.Orders;

/**
 * @author WangGuoQing
 * @date 2019/5/8 13:28
 * @Desc Orders实体Mybatis映射接口
 */
public interface OrdersMapper {
    /**
     * 插入
     * @param orders
     */
    public void insertOrders(Orders orders);
}
