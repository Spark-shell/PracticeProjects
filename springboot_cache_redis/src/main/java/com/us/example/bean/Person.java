package com.us.example.bean;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author WangGuoQing
 * @date 2019/4/9 15:18
 * @Desc  @Entity   标注此类是JPA实体类
 *        @Table(name = "Person")   @Table - 映射表名
 *        @GeneratedValue(strategy=GenerationType.IDENTITY) - 自动递增生成
 *        @Column(name = "dict_name",columnDefinition="varchar(100) COMMENT '字典名'") - 字段名、类型、注释
 *        @UpdateTimestamp - 更新时自动更新时间
 *        @CreationTimestamp - 创建时自动更新时间
 *        @Version - 版本号，更新时自动加1
 */
@Entity
@Table(name = "Person")
public class Person implements Serializable {
    private static final long serialVersionUID = 133938246231808718L;
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer age;
    private String address;

    public Person() {
        super();
    }
    public Person(Long id, String name, Integer age, String address) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }


}

