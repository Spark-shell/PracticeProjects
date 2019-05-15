package com.gsau.portal.portal.service.impl;

import com.codingapi.tx.annotation.TxTransaction;
import com.gsau.inventory_sersvice.projo.Inventory;
import com.gsau.order_sersvice.projo.Orders;
import com.gsau.portal.feign_service.PortalInventoryService;
import com.gsau.portal.feign_service.PortalOrderService;
import com.gsau.portal.feign_service.PortalUserInfoService;
import com.gsau.portal.portal.service.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortalServiceImpl implements PortalService {
    @Autowired
    private PortalInventoryService inventoryService;

    @Autowired
    private PortalOrderService orderService;
    @Autowired
    private PortalUserInfoService userInfoService;


    @TxTransaction(isStart = true)
    @Override
    public void addOrder() {
        Inventory inventory = new Inventory();
        inventory.setItemid(100);
        inventory.setItemnum(21);
        inventoryService.updateInventory(inventory);
        Orders orders = new Orders();
        orders.setItemid(100);
        orders.setPrice(10);
        orderService.addOder(orders);
    }

}
