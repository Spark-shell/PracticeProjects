package com.gsau.Inventory.service;


/**
 * @author WangGuoQing
 * @date 2019/5/8 13:52
 * @Desc 用户登录验证信息
 */
public interface UserInfoService {
    void findUserById(int id);
    void findUserByTel(int tel);
}
