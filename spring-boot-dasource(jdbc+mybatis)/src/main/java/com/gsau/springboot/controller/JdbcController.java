package com.gsau.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
/**
 * @author WangGuoQing
 * @date 2019/4/18 22:11
 * @Desc 测试使用使用原生数据源访问数据库
 */
@Controller
public class JdbcController {
    //Springboot JDBC 启动的时候在IOC容器中自动注入了jdbcTemplate,用于操作数据库
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 操作数据源，查询数据列表
     * @return
     */
    @ResponseBody
    @GetMapping("/query")
    public Map<String,Object> map(){
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * FROM department");
        return list.get(0);
    }
}
