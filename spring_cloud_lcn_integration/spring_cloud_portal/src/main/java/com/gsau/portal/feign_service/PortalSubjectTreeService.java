package com.gsau.portal.feign_service;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("SPRING-CLOUD-INVENTORY-7002-DEV")
public interface PortalSubjectTreeService {
}
