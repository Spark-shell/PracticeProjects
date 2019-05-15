package com.gsau.portal.feign_service;

import com.gsau.order_sersvice.services.UserInfoService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("SPRING-CLOUD-ORDER-7003-DEV")
public interface PortalUserInfoService extends UserInfoService {
}
