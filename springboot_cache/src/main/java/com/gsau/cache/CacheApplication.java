package com.gsau.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author WangGuoQing
 * @date 2019/4/19 15:34
 * @Desc spring boot缓存初试
 *          @EnableAsync  :开启异步注解功能
 *          @@EnableScheduling  :开启定时任务注解功能
 */
@EnableAsync
@EnableScheduling
@MapperScan(value = "com.gsau.cache.mapper")
@SpringBootApplication
public class CacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }
}
