package com.gsau.portal.feign_service;

import com.gsau.inventory_sersvice.services.InventoryService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("SPRING-CLOUD-INVENTORY-7002")
public interface PortalInventoryService extends InventoryService {
}