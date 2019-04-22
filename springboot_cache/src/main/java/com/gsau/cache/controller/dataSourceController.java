package com.gsau.cache.controller;

import com.gsau.cache.entity.Employee;
import com.gsau.cache.mapper.EmployeeMapper;
import com.gsau.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author WangGuoQing
 * @date 2019/4/19 16:15
 * @Desc 测试MyBatis数据源配置
 */
@RestController
public class dataSourceController {
    @Autowired
    EmployeeService service;

    @GetMapping("/emp/{id}")
    public Employee get(@PathVariable("id") Integer id) {
        Employee employee = service.getEmpById(id);
        return employee;
    }

    /**
     * 更新数据库更新缓存
     *
     * @param emp
     * @return
     */
    @GetMapping("/emp")
    public Employee updateEmp(Employee emp) {
        service.updateEmp(emp);
        return emp;
    }

    /**
     * 插入
     * @return
     */
    @GetMapping("/emp/insert")
    public  Employee insertEmp(Employee employee){
        service.insertEmp(employee);
        return employee;
    }
    /**
     * 删除数据
     * @param id
     * @return
     */
    @GetMapping("/emp/del/{id}")
    public String deleteEmp(@PathVariable("id") Integer id) {
        return service.deleteEmp(id);
    }
    /**
     * 根据用户名称查询
     * @param employee
     * @return
     */
    @GetMapping("/emp/list")
    public List<Employee> getEmps( Employee employee) {
        return service.getEmpByLastName(employee.getLastName());
    }
}
