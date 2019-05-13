package com.gsau.portal.feign_service;

import com.gsau.order_sersvice.services.OrderService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("SPRING-CLOUD-ORDER-7003-DEV")
public interface PortalOrderService extends OrderService {

}
