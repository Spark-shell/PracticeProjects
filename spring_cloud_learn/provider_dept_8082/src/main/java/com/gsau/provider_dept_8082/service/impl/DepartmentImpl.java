package com.gsau.provider_dept_8082.service.impl;

import com.gsau.api_8081.entities.Department;
import com.gsau.provider_dept_8082.dao.DepartmentDao;
import com.gsau.provider_dept_8082.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WangGuoQing
 * @date 2019/4/28 11:44
 * @Desc 
 */
@Service
public class DepartmentImpl implements DeptService {

    @Autowired
    DepartmentDao departmentDao;

    @Override
    public boolean addDept(Department Department) {
        return departmentDao.addDept(Department);
    }

    @Override
    public Department findById(int id) {
        return departmentDao.findById(id);
    }

    @Override
    public List<Department> findAll() {
        return departmentDao.findAll();
    }
}
