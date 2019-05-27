package com.gsau.weichat.weichat_server.domain;

import lombok.Data;

import java.util.Date;

/**
* @Description: 教室信息
* @Date: 2019/5/22 15:09
* @author: wgq
* @version: 1.0
*/
@Data
public class ClassRoom {
    private int Id;                        //教室id
    private String className;              //教室名称（代号）

    private Date dateCreated;               //创建时间
    private Date dateUpdated;               //更新时间
    private int delFlag;
}
