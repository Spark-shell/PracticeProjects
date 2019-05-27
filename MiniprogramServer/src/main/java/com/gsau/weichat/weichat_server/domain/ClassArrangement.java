package com.gsau.weichat.weichat_server.domain;

import lombok.Data;

import java.util.Date;

/**
* @Description: 课程安排表
* @Date: 2019/5/22 15:17
* @author: wgq
* @version: 1.0
*/
@Data
public class ClassArrangement {
    private  int id;               //课程安排ID
    private  int clsRommId;        //教室
    private int tId;               //老师
    private int sbtId;             //课程
    private int startTime;         //每节课开始时间
    private int endTime;           //每节课时间
    private int lessonNum;         //每天节数

    private Date dateCreated;               //创建时间
    private Date dateUpdated;               //更新时间
    private int delFlag;
}
