package com.gsau.order.controller;


import com.gsau.order.service.impl.UserInfoServiceImpl;
import com.gsau.order_sersvice.projo.po.UserInfo;
import com.gsau.order_sersvice.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserInfoController implements UserInfoService {
    @Autowired
    UserInfoServiceImpl userInfoService;

    /**
     * 方式一
     * @param usertel
     * @return
     */
    // @Override
    // public UserInfo findByUserTel(@RequestParam("usertel")  String usertel) {
    //     UserInfo userInfo=userInfoService.findByUserTel(usertel);
    //     return userInfo;
    // }

    /**
     * 方式二
     *
     * @param usertel
     * @return
     */
    @Override
    public UserInfo findByUserTel(@PathVariable("usertel") String usertel) {
        UserInfo userInfo = userInfoService.findByUserTel(usertel);
        return userInfo;
    }
    @Override
    public UserInfo findByUserId(@PathVariable("userid") int userid) {
        UserInfo userInfo = userInfoService.findByUserId(userid);
        return userInfo;
    }

    @RequestMapping(value = "/save")
    @Override
    public void save(@RequestBody UserInfo userInfo) {
        userInfoService.save(userInfo);
    }

    @RequestMapping(value = "/findAll")
    @Override
    public List<UserInfo> findAll() {
        List<UserInfo> list = userInfoService.findAll();
        return list;
    }

    @RequestMapping(value = "/delete")
    @Override
    public void delete(@RequestBody UserInfo userInfo) {
        userInfoService.delete(userInfo);
    }

}
