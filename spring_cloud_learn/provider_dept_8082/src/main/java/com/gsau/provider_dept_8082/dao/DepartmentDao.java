package com.gsau.provider_dept_8082.dao;

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
     * @param deptEntity
     *
     * @return
     */
    boolean addDept(Department deptEntity);

    /**
     * 根据id查找
     * @param deptNo
     * @return
     */
    Department findById(Long deptNo);

    /**
     * 查询全部
     * @return
     */
    List<Department> findAll();
}
