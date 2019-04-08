package com.gsau.springBootTemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

/**
 * SpringBoot启动类
 * @SpringBootApplication :包含了@ComponentScan：让spring Boot扫描到Configuration类并把它加入到程序上下文
 * 								@Configuration
 * 								@EnableAutoConfiguration。
 * @author Wgq
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableCaching
public class SpringBootTemplateApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootTemplateApplication.class, args);
	}
}
