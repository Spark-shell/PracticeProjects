package com.gsau.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableZuulProxy
public class PortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class);
    }
}
