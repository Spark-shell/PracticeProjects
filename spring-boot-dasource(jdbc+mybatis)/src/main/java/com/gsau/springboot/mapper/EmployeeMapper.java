package com.gsau.springboot.mapper;

import com.gsau.springboot.bean.Employee;

//@Mapper或者@MapperScan将接口扫描装配到容器中
/**
 * @author WangGuoQing
 * @date 2019/4/19 12:07
 * @Desc 配置文件的方式访问员工表
 */
public interface EmployeeMapper {
    public Employee getEmpById(Integer id);
    public void insertEmp(Employee employee);
}
