package com.gsau.weichat.weichat_server.domain;

import lombok.Data;

import java.util.Date;

/**
* @Description: 缺勤信息
* @Date: 2019/5/22 14:28
* @author: wgq
* @version: 1.0
*/
@Data
public class Absentee {
    private int Id;                        //ID
    private int attId;                      //考勤ID
    private int sbtId;                      //课程ID
    private int tId;                        //老师ID
    private int stuId;                      //学生ID

    private Date dateCreated;               //创建时间
    private Date dateUpdated;               //更新时间
    private int delFlag;
}
