package com.gsau.springboot.mapper;

import com.gsau.springboot.bean.Department;
import org.apache.ibatis.annotations.*;


/**
 * @author WangGuoQing
 * @date 2019/4/19 9:49
 * @Desc 指定这是一个操作数据库的mapper（映射器）
 */
// @Mapper    //启动类上添加了MapperScan所以可以不用使用@mapper注解了
public interface DepartmentMapper {

    @Select("select * from department where id=#{id}")
    public Department getDeptById(Integer id);

    @Delete("delete from department where id=#{id}")
    public int deleteDeptById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")                            //插入的时候使用自增的 ID
    @Insert("insert into department(departmentName) values(#{departmentName})")
    public int insertDept(Department department);

    @Update("update department set departmentName=#{departmentName} where id=#{id}")
    public int updateDept(Department department);
}
