package com.gsau.weichat.weichat_server.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 学生信息
 * @Author wgq
 * @Date 2019/5/22 14:25
 */
@Data
public class Student {
    private int id;                         //ID
    private String stuName;                 //姓名
    private String stuClass;                //班级
    private int stuAge;                     //年龄
    private int stuGender;                  //性别
    private String stuPhone;                //学生电话
    private String stuGuardian;             //监护人
    private String guardianContactType;     //监护人联系方式   1:微信  2:手机短信
    private String guardianContactNumber;   //监护人联系号码
    private Date dateCreated;               //创建时间
    private Date dateUpdated;               //更新时间
    private int delFlag;                    //0 :未删除   1：已删除
}
