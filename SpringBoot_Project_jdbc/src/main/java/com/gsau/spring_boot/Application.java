package com.gsau.spring_boot;

import com.gsau.spring_boot.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Application implements CommandLineRunner {       //

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }
    //自动装配jdbcTemplate
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception {
        log.info("Creating tables");
        //如果存在表customers那么删除它
        jdbcTemplate.execute("drop table  if exists customers");
        //创建customers表
        jdbcTemplate.execute("CREATE TABLE customers(id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

        // 将整个名称数组拆分为一个名/姓数组
        List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long")
                .stream()                                   //返回一个序列流
                .map(name -> name.split(" "))        //对流中的元素进行分割，然后返回一个个的数组
                .collect(Collectors.toList());

        // 使用Java 8流打印列表中的每个元组
        splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

        // 使用JDBCTemplate的批更新操作批量加载数据
        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);
        log.info("查询first_name是Josh的记录");
        jdbcTemplate.query("SELECT id, first_name, last_name FROM customers WHERE first_name = ?",
                new Object[] { "Josh" },
                (rs, rowNum) ->new Customer(rs.getLong("id"),
                                            rs.getString("first_name"),
                                            rs.getString("last_name"))
        ).forEach(customer -> log.info(customer.toString()));
    }
}