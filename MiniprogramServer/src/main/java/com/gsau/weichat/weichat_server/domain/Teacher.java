package com.gsau.weichat.weichat_server.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 教师信息
 * @Date: 2019/5/22 14:25
 * @author: wgq
 * @version: 1.0
 */
@Data
public class Teacher {
    private int tId;                        //ID
    private String tName;                   //姓名
    private String tClass;                  //班级
    private int tAge;                       //年龄
    private int tGender;                    //性别
    private int tSubjectType;               //教学类别
    private String tPhone;                  //电话
    private Date dateCreated;               //创建时间
    private Date dateUpdated;               //更新时间
    private int delFlag;                    //0 :未删除   1：已删除
}
