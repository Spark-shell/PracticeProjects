package com.gsau.Inventory.controller;

import com.gsau.inventory_sersvice.projo.Inventory;
import com.gsau.inventory_sersvice.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangGuoQing
 * @date 2019/5/8 15:00
 * @Desc
 */
@RestController
public class InventoryController  implements InventoryService {
    @Autowired
    private com.gsau.Inventory.service.InventoryService inventoryService;
    @Override
    public void updateInventory(@RequestBody Inventory inventory) {
        inventoryService.updateInventory(inventory);
    }
}
