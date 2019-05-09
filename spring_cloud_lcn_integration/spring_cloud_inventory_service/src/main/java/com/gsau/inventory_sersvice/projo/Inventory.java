package com.gsau.inventory_sersvice.projo;

import java.io.Serializable;

/**
 * @author WangGuoQing
 * @date 2019/5/8 14:35
 * @Desc 库存实体封装类
 */
public class Inventory implements Serializable {
    private  Integer inventoryId;
    private  Integer itemid;

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public Integer getItemnum() {
        return itemnum;
    }

    public void setItemnum(Integer itemnum) {
        this.itemnum = itemnum;
    }

    private  Integer itemnum;
}
