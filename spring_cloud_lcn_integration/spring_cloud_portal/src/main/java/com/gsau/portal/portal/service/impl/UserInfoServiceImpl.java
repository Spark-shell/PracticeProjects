package com.gsau.portal.portal.service.impl;

import com.gsau.order_sersvice.projo.po.UserInfo;
import com.gsau.portal.feign_service.PortalUserInfoService;
import com.gsau.portal.portal.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WangGuoQing
 * @date 2019/5/15 14:45
 * @Desc 调用远程微服务
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    //fegin远程微服务接口
    @Autowired
    PortalUserInfoService userInfoService;

    @Override
    public UserInfo findUserByTel(String usertel) {
        UserInfo userInfo = userInfoService.findByUserTel(usertel);
        return userInfo;
    }

    @Override
    public void save(UserInfo userInfo) {
        userInfoService.save(userInfo);
    }

    @Override
    public UserInfo findUserByUserId(int userid) {
        UserInfo userInfo=userInfoService.findByUserId(userid);
        return userInfo;
    }

    @Override
    public List<UserInfo> findAll() {
        List<UserInfo> list=userInfoService.findAll();
        return list;
    }

    @Override
    public void delete(UserInfo userInfo) {
       userInfoService.delete(userInfo);
    }
}
