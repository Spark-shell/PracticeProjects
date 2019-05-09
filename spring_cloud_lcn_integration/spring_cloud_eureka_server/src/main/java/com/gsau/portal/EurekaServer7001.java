package com.gsau.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author WangGuoQing
 * @date 2019/5/8 10:52
 * @Desc 服务注册中心
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServer7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer7001.class);
    }
}
