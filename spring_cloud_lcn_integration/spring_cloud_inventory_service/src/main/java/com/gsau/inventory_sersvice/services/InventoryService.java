package com.gsau.inventory_sersvice.services;

import com.gsau.inventory_sersvice.projo.Inventory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author WangGuoQing
 * @date 2019/5/8 13:22
 * @Desc 微服务接口
 */
public interface InventoryService {
    /**
     * @author WangGuoQing
     * @date 2019/5/8 13:22
     * @Desc 添加订单
     */
    @RequestMapping(value = "/updateInventory",method= RequestMethod.POST)
    public void updateInventory(@RequestBody Inventory inventory);

}
