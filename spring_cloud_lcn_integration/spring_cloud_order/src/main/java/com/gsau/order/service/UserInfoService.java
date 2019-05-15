package com.gsau.order.service;

import com.gsau.order_sersvice.projo.po.UserInfo;

import java.util.List;

/**
 * @author WangGuoQing
 * @date 2019/5/8 13:52
 * @Desc 用户登录验证信息
 */
public interface UserInfoService {

    UserInfo findByUserId(int id);

    UserInfo findUser(String usertel, String userPassword);

    List<UserInfo> queryList(int pageNumber, int pageSize);

    UserInfo findByUserTel(String usertel);

    UserInfo findByUserInfoBytokenid(long userid, String tokenid);

    void insertUser(UserInfo userInfo);
}
