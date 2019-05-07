package com.gsau.eureka_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author WangGuoQing
 * @date 2019/5/6 16:22
 * @Desc 
 */
@EnableEurekaClient
@SpringBootApplication
public class EurekaClientAppliction {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientAppliction.class);
    }
}
