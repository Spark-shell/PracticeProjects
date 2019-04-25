package com.gsau.springboot.controller;

import com.gsau.springboot.bean.Department;
import com.gsau.springboot.bean.Employee;
import com.gsau.springboot.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangGuoQing
 * @date 2019/4/19 9:53
 * @Desc 测试MyBatis注解方式访问数据库
 */
@RestController
public class AnnotationController {
    @Autowired
    DepartmentMapper departmentMapper;

    /**
     * 查询
     * @param id
     * @return
     */
    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable("id") Integer id){
        return departmentMapper.getDeptById(id);
    }
    /**
     * 添加
     * @param department
     * @return
     */
    @GetMapping("/dept")
    public Department insertDept(Department department){
        departmentMapper.insertDept(department);
        return department;
    }

}
