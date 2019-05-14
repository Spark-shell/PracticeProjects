package com.gsau.order.service.impl;

import com.gsau.order.service.UserInfoService;
import com.gsau.order_sersvice.projo.po.UserInfo;

import java.util.List;

/**
 * @author WangGuoQing
 * @date 2019/5/8 13:52
 * @Desc 
 */
public class UserInfoServiceImpl  implements UserInfoService {

    @Override
    public UserInfo findByUsertel(String usertel) {
        return null;
    }

    @Override
    public UserInfo findByUserId(int id) {
        return null;
    }

    @Override
    public UserInfo findUser(String usertel, String userPassword) {
        return null;
    }

    @Override
    public List<UserInfo> queryList(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public UserInfo findByUserid(long userid) {
        return null;
    }

    @Override
    public UserInfo findByUserInfoBytokenid(long userid, String tokenid) {
        return null;
    }
}
