package com.gsau.portal.portal.service;

import com.gsau.order_sersvice.projo.po.UserInfo;

import java.util.List;

/**
 * @author WangGuoQing
 * @date 2019/5/15 14:45
 * @Desc
 */
public interface UserInfoService {
    /**
     * @author WangGuoQing
     * @date 2019/5/15 14:45
     * @Desc 根据电话查询
     */
    UserInfo findUserByTel(String usertel);

    /**
     * 保存
     * @param userInfo
     */
    void save(UserInfo userInfo);

    /**
     * 根据ID查询
     * @param userid
     * @return
     */
    UserInfo findUserByUserId(int userid);

    /**
     * 获取所有
     * @return
     */
    List<UserInfo> findAll();

    /**
     * 删除
     * @param userInfo
     */
    void delete(UserInfo userInfo);

    /**
     * 用户名和密码查询用户
     * @param usertel
     * @param userpassword
     * @return
     */
    UserInfo findUserByTelAndPwd(String usertel, String userpassword);
}
