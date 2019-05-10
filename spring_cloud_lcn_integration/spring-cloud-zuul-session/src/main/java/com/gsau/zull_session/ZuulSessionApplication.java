package com.gsau.zull_session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Description: 服务启动入口
 * @Date: 2019/5/10 14:14
 * @author: wgq
 * @version: 1.0
 */
@EnableRedisHttpSession
@EnableEurekaClient
@SpringBootApplication
@EnableZuulProxy
public class ZuulSessionApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulSessionApplication.class);
    }
}
