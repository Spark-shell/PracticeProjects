package com.gsau.provider_dept_8082.controller;

import com.gsau.api_8081.entities.Department;
import com.gsau.provider_dept_8082.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangGuoQing
 * @date 2019/4/28 12:15
 * @Desc 
 */
@RestController
public class TestRestController {
    @Autowired
    DeptService service;

    @GetMapping("/rest/{id}")
    public Department findById(@PathVariable("id") int id) {
       return service.findById(id);
    }
}
