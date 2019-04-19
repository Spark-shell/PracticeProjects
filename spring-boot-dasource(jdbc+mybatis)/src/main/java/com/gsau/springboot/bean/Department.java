package com.gsau.springboot.bean;
/**
 * @author WangGuoQing
 * @date 2019/4/19 9:33
 * @Desc 部门实体
 */
public class Department {
    private Integer id;
    private String departmentName;

    public void setId(Integer id) {
        this.id = id;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public Integer getId() {
        return id;
    }
    public String getDepartmentName() {
        return departmentName;
    }
}
