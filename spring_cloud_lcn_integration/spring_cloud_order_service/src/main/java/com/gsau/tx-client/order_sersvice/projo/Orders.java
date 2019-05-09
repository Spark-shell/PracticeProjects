package com.gsau.order_sersvice.projo;

import java.io.Serializable;

/**
 * @author WangGuoQing
 * @date 2019/5/8 11:12
 * @Desc Order 实体
 */
public class Orders implements Serializable {
    private Integer orderid;
    private Integer itemid;
    private Integer price;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
