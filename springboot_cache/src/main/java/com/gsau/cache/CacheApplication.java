package com.gsau.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author WangGuoQing
 * @date 2019/4/19 15:34
 * @Desc spring boot缓存初试
 */
@MapperScan(value = "com.gsau.cache.mapper")
@SpringBootApplication
public class CacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }
}
