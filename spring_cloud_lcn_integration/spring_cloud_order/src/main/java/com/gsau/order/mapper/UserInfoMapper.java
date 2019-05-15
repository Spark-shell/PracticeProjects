package com.gsau.order.mapper;

import com.gsau.order_sersvice.projo.po.UserInfo;

/**
 * @author WangGuoQing
 * @date 2019/5/15 9:22
 * @Desc 用户信息
 */
public interface UserInfoMapper {
    /**
     * 插入
     * @param userInfo
     */
    void insertUserInfo(UserInfo userInfo);

    /**
     *  根据useridc查询
     * @param userid
     */
    UserInfo findByUserId(int userid);

    /**
     * 电话号查询
     * @param usertel
     */
    UserInfo  findByUserTel(String usertel);
}
