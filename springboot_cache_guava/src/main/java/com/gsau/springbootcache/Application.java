package com.gsau.springbootcache;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

import static org.springframework.boot.SpringApplication.*;

/**
 * @author WangGuoQing
 * @date 2019/4/9 15:10
 * @Desc 
 */
@SpringBootApplication
@EnableCaching
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = run(Application.class, args);
    }

}
