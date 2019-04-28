package com.gsau.provider_dept_8082.service;

import com.gsau.api_8081.entities.Department;
import com.gsau.provider_dept_8082.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WangGuoQing
 * @date 2019/4/28 11:46
 * @Desc 
 */
@Service
public interface DeptService {
    /**
     * 插入
     * @param Department
     * @return
     */
    boolean addDept(Department Department);

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