package com.gsau.eureka_server.dao;

import com.gsau.api_8081.entities.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author WangGuoQing
 * @date 2019/4/28 11:24
 * @Desc 部门实体映射器
 */
@Mapper
public interface DepartmentDao {
    /**
     * 插入
     * @param department
     *
     * @return
     */
    boolean addDept(Department department);

    /**
     * 根据id查找
     * @param deptNo
     * @return
     */
    Department findById(int deptNo);

    /**
     * 查询全部
     * @return
     */
    List<Department> findAll();
}
