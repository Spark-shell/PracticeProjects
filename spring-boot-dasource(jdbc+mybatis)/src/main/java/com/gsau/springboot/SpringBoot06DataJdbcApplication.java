package com.gsau.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author WangGuoQing
 * @date 2019/4/19 10:56
 * @Desc @MapperScan(value = "com.gsau.springboot.mapper") 指定mapper包下的所有类都是Mybatis映射器，就不用给每个类上都注解@Mapper
 */
@MapperScan(value = "com.gsau.springboot.mapper")
@SpringBootApplication
public class SpringBoot06DataJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot06DataJdbcApplication.class, args);
	}
}
