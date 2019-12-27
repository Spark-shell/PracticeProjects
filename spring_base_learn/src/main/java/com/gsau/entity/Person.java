package com.gsau.entity;

/**
 * @ Description:  用于测试bean创建的三种方式:默认情况下
 * @ Date: 2019/12/27 11:25
 * @ Author: wgq
 * @ Version: 1.0
 */
public class Person {
    public Person(){
        System.out.println("Person-Person-11->"+System.currentTimeMillis());
    }
    private String id;
    private String name;
    private String addr;
    public String getId() {
        return (id==null ? "没有实际值": id ) ;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return (name==null ? "没有实际值": name ) ;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddr() {
        return (addr==null ? "没有实际值": addr ) ;
    }
    public void setAddr(String addr) {
        this.addr = addr;
    }
    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }
}
