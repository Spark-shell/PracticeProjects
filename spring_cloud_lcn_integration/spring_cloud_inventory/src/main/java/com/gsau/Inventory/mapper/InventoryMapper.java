package com.gsau.Inventory.mapper;

import com.gsau.inventory_sersvice.projo.Inventory;

/**
 * @author WangGuoQing
 * @date 2019/5/8 14:50
 * @Desc mybatis映射器
 */
public interface InventoryMapper {
    /**
     * 更新
     * @param inventory
     */
    void updateInventory(Inventory inventory);
}
