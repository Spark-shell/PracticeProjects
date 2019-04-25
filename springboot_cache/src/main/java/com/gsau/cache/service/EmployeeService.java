package com.gsau.cache.service;

import com.gsau.cache.entity.Employee;
import com.gsau.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *  @CacheConfig()
 *                  用于抽取缓存中的公共配置
 * @author WangGuoQing
 * @date 2019/4/21 16:19
 * @Desc @EnableCaching开启基于注解的缓存
 */
@Service
@EnableCaching
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存，一会要相同的数据，直接从换从中去不用调用数据接口到数据库去查询
     * CacheManager管理多个Cache组件，对缓存的真正CRUD操作在Cache组件中，每一个缓存组件都有自己唯一一个名字
     *      几个属性：
     *              cacheNames/value:指定缓存组件的名称
     *              key:  缓存的 key，可以为空，如果指定要按照 SpEL 表达式编写，如果不指定，则缺省按照方法的所有参数进行组合 例如：@Cacheable(value=”testcache”,key=”#userName”)
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "emp", keyGenerator="myKeyGenerator")
    public Employee getEmpById(Integer id) {
        System.out.println("====查询一个员工====");
        Employee employee=employeeMapper.getEmpById(id);
        System.out.println(employee);
        return  employee;
    }

    /**
     * 更新数据并更新缓存
     * @return
     */
    @CachePut(value = {"emp"},keyGenerator = "myKeyGenerator")
    public Employee updateEmp(Employee emp){
            employeeMapper.updateEmp(emp);
            return  emp;
    }

    /**
     * 删除数据同时清除缓存中相关的记录
     *      清除缓存的时候需要指定，Cache名称和key的值
     *      key指定要清除的数据
     *      allEntries=true:指定清除整个缓存中的所有数据
     *      beforeInvocation=fase: 缓存的清除是否在方法之前执行，默认是在方法之后执行
     * @param id
     * @return
     */
    @CacheEvict(value="emp",keyGenerator="myKeyGenerator")
    public String deleteEmp(Integer  id){
       Employee employee= employeeMapper.getEmpById(id);
       employeeMapper.deleteEmp(id);
       return "SUCCESS";
    }

    /**
     * 插入
     * @param employee
     * @return
     */
    public Employee insertEmp(Employee employee){
        employeeMapper.insertEmp(employee);
        return employee;
    }

    /**
     * 根据用户名查询用户
     *    @Caching 定义复杂的缓存规则写法和其他的注解一致
     * @param lastName
     * @return
     */
    @Caching(cacheable = {@Cacheable(value = "emp",key = "#lastName")},
             put={@CachePut(value="emp"),
                  @CachePut(value="emp")
             }
    )
    public List getEmpByLastName(String lastName){
        List<Employee> emps=new ArrayList<Employee>();
        System.out.println(employeeMapper.getEmpByLastName(lastName));
        emps= (List<Employee>) employeeMapper.getEmpByLastName(lastName);
        return emps;
    }
}
