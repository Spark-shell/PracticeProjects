package com.gsau.weichat.weichat_server.domain;

import lombok.Data;

import java.util.Date;

/**
* @Description: 考勤记录
* @Date: 2019/5/22 14:22
* @author: wgq
* @version: 1.0
*/
@Data
public class AttendanceRecord {
    private int Id;                         //ID
    private int sbtId;                      //课程ID
    private int tId;                        //老师ID
    private int stuNumber;                  //备注
    private int attendanceNum;              //上课人数
    private int absenteeNum;                //缺勤人数


    private Date dateCreated;               //创建时间
    private Date dateUpdated;               //更新时间
    private int delFlag;
}
