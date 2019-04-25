package com.gsau.springboot.controller;

import com.gsau.springboot.bean.Department;
import com.gsau.springboot.bean.Employee;
import com.gsau.springboot.mapper.DepartmentMapper;
import com.gsau.springboot.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangGuoQing
 * @date 2019/4/19 9:53
 * @Desc 测试MyBatis注解方式访问数据库
 */
@RestController
public class xmlController {
    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 查询
     * @param id
     * @return
     */
    @GetMapping("/emp/{id}")
    public Employee getEmpById(@PathVariable("id") Integer id){
        Employee obj=employeeMapper.getEmpById(id);
        return obj;
    }
    /**
     * 添加
     * @param employee
     * @return
     */
    @GetMapping("/emp")
    public Employee insertDept(Employee employee){
        employeeMapper.insertEmp(employee);
        return employee;
    }

}
