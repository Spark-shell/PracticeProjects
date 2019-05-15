package com.gsau.order.service.impl;

import com.codingapi.tx.annotation.TxTransaction;
import com.gsau.order.mapper.UserInfoMapper;
import com.gsau.order.service.UserInfoService;
import com.gsau.order_sersvice.projo.po.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author WangGuoQing
 * @date 2019/5/8 13:52
 * @Desc
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;


    @Override
    public UserInfo findByUserId(int id) {
        UserInfo info=userInfoMapper.findByUserId(id);
        return info;
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
    public UserInfo findByUserTel(String usertel) {
        UserInfo userInfo = userInfoMapper.findByUserTel(usertel);
        return userInfo;
    }

    @Override
    public UserInfo findByUserInfoBytokenid(long userid, String tokenid) {
        return null;
    }

    @Transactional
    @TxTransaction
    @Override
    public void insertUser(UserInfo userInfo) {
        userInfoMapper.insertUserInfo(userInfo);
    }

    @Override
    public void save(UserInfo userInfo) {
        userInfoMapper.insertUserInfo(userInfo);
    }

    @Override
    public List<UserInfo> findAll() {
        List<UserInfo> list=  userInfoMapper.findAll();
        return list;
    }

    @Override
    public void delete(UserInfo userInfo) {
        userInfoMapper.delete(userInfo);
    }
}
