package com.gsau.cache.mapper;

import com.gsau.cache.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author WangGuoQing
 * @date 2019/4/19 15:51
 * @Desc mybatis Employee实体映射器
 */
// @Mapper
public interface EmployeeMapper {
    /**
     * 根据 ID获取
     * @param id
     * @return
     */
    public Employee getEmpById(Integer id);
    public List<Employee> getEmpByLastName(String lastName);
    /**
     * 插入
     * @param employee
     */
    public void insertEmp(Employee employee);
    /**
     * 更新
     * @param emp
     */
   public  void updateEmp(Employee emp);

    /**
     * 更新
     * @param id
     */
   public void deleteEmp(int id);
}
