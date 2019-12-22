package com.gsau.project01;

import org.hibernate.validator.internal.constraintvalidators.bv.number.bound.decimal.DecimalMinValidatorForLong;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 *
 * @author WangGuoQing
 * @date 2019/4/19 10:56
 * @Desc @MapperScan(value = "com.gsau.springboot.mapper") 指定mapper包下的所有类都是Mybatis映射器，就不用给每个类上都注解@Mapper
 *  @ImportResource(locations = {"classpath:beans.xml"}) 引入spring的配置文件
 */
//@ImportResource(locations = {"classpath:beans.xml"})  //可用@Configuration代替
@MapperScan(value = "com.gsau.project01.mapper")
@SpringBootApplication
public class SuperMallApplication {
    public static void main(String[] args) {
        SpringApplication.run(SuperMallApplication.class, args);
    }
}
