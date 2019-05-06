package com.gsau.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author WangGuoQing
 * @date 2019/5/5 16:32
 * @Desc spring cloud 分布式配置中心服务端
 */
@EnableConfigServer
@SpringBootApplication
public class Config3344Application {
    public static void main(String[] args) {
        SpringApplication.run(Config3344Application.class, args);
    }
}
