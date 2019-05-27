package com.gsau.weichat.weichat_server.domain;

import lombok.Data;

/**
* @Description: 建议信息
* @Date: 2019/5/22 14:38
* @author: wgq
* @version: 1.0
*/
@Data
public class Suggest {
     private int id;                //id
     private int userId;            //建议用户
     private String comment;        //建议内容
}
