package com.gsau.eureka_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author WangGuoQing
 * @date 2019/4/28 12:15
 * @Desc  @EnableEurekaServer表示这是一个Eureka Server服务,其他服务可以注册进来
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaSersverApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaSersverApplication.class, args);
    }
}
