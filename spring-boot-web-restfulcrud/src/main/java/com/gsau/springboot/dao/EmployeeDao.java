package com.gsau.springboot.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.gsau.springboot.entities.Department;
import com.gsau.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/**
 * @author WangGuoQing
 * @date 2019/4/18 11:22
 * @Desc 表示带@Repository注释的类是“存储库”，@Repository的作用为给bean在容器中命名
 */
@Repository
public class EmployeeDao {
	private static Map<Integer, Employee> employees = null;
	@Autowired
	private DepartmentDao departmentDao;
	//模拟静态数据
	static{
		employees = new HashMap<Integer, Employee>();
		employees.put(1001, new Employee(1001, "E-AA", "aa@163.com", 1, new Department(101, "D-AA")));
		employees.put(1002, new Employee(1002, "E-BB", "bb@163.com", 1, new Department(102, "D-BB")));
		employees.put(1003, new Employee(1003, "E-CC", "cc@163.com", 0, new Department(103, "D-CC")));
		employees.put(1004, new Employee(1004, "E-DD", "dd@163.com", 0, new Department(104, "D-DD")));
		employees.put(1005, new Employee(1005, "E-EE", "ee@163.com", 1, new Department(105, "D-EE")));
	}
	
	private static Integer initId = 1006;

	/**
	 * 保存
	 * @param employee
	 */
	public void save(Employee employee){
		if(employee.getId() == null){
			employee.setId(initId++);
		}
		
		employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
		employees.put(employee.getId(), employee);
	}
	/**
	 * 查询所有员工
	 * @return
	 */
	public Collection<Employee> getAll(){
		return employees.values();
	}

	/**
	 * 根据ID进行查询
	 * @param id
	 * @return
	 */
	public Employee get(Integer id){
		return employees.get(id);
	}

	/**
	 * 根据ＩＤ进行删除
	 * @param id
	 */
	public void delete(Integer id){
		employees.remove(id);
	}
}
