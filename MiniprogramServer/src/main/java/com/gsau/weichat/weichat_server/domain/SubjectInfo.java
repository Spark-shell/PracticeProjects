package com.gsau.weichat.weichat_server.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 课程信息
 * @Date: 2019/5/22 14:24
 * @author: wgq
 * @version: 1.0
 */
@Data
public class SubjectInfo {
    private int sbtId;                      //ID
    private String sbtName;                 //姓名
    private Date sbtStartDate;              //课程开始时间
    private Date sbtEndDate;                //课程结束时间
    private String sbtAddr;                 //上课地点
    private int sbtCurriculum;              //总课程数目


    private Date dateCreated;               //创建时间
    private Date dateUpdated;               //更新时间
    private int delFlag;                    //0 :未删除   1：已删除
}
