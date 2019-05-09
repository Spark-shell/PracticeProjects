package com.gsau.Inventory.service.impl;

import com.gsau.Inventory.mapper.InventoryMapper;
import com.gsau.Inventory.service.InventoryService;
import com.gsau.inventory_sersvice.projo.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author WangGuoQing
 * @date 2019/5/8 14:58
 * @Desc 库存服务层
 */
@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryMapper inventoryMapper;

    /**
     * 需要受事务控制
     * @param inventory
     */
    @Override
    @Transactional
    public void updateInventory(Inventory inventory) {
        inventoryMapper.updateInventory(inventory);
    }
}
