package com.gsau.springboot.controller;

import com.gsau.springboot.dao.DepartmentDao;
import com.gsau.springboot.dao.EmployeeDao;
import com.gsau.springboot.entities.Department;
import com.gsau.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
/**
 * @author WangGuoQing
 * @date 2019/4/18 11:17
 * @Desc restful风格的CRUD
 *       需求：1.满足rest风格，用请求方式区分请求操作的类型
 *             2.GET 查询
 *             3.POST 添加
 *             4.DELETE 删除
 *             5.PUT 更新
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;
    /**
     * 处理GET方式提交的请求，查询所有员工返回列表页面
     * @param model
     * @return
     */
    @GetMapping("/emps")
    public String  list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        //放在请求域中
        model.addAttribute("emps",employees);
        //thymeleaf默认就会拼串，拼接到 classpath:/templates/xxxx.html 路径下
        return "emp/list";
    }
    /**
     * 处理GET方式提交的请求，跳转到员工添加页面
     * @param model
     * @return
     */
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //查出所有的部门，在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    /**
     * 处理POST方式提交的请求，添加员工信息；
     * SpringMVC自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
     * @param employee
     * @return
     */
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println("保存的员工信息："+employee);
        employeeDao.save(employee);                                 //保存员工
                                                                    // redirect: 表示重定向到一个地址  /代表当前项目路径
        return "redirect:/emps";                                    // forward: 表示转发到一个地址

    }
    /**
     * 处理GET方式提交的请求,URL : ../emp/3  note:查出当前员工，在页面回显
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);

        //页面要显示所有的部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //回到修改页面(add是一个修改添加二合一的页面);
        return "emp/add";
    }
    /**
     * 员工修改；需要提交员工id；
     * @param employee
     * @return
     */
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        System.out.println("修改的员工数据："+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    /**
     * 员工删除
     * @param id
     * @return
     */
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
