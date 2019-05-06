package com.gsau.eureka_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author WangGuoQing
 * @date 2019/4/28 12:15
 * @Desc @EnableEurekaClient 帮助服务启动都自动注册近Eureka Server中
 */
@EnableEurekaClient
@SpringBootApplication
public class ConfigProviderDept8021Application {
    public static void main(String[] args) {
        SpringApplication.run(ConfigProviderDept8021Application.class, args);
    }
}
