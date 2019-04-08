package com.gsau.springBootTemplate.service;

import com.gsau.springBootTemplate.bean.UserEntity;

import java.util.List;

/**
 * 服务层接口
 * @author Wgq
 */
public interface IUserService {

    /**
     * 查询数据
     */
    List<UserEntity> listUser();

    /**
     * 添加数据
     */
    boolean addUser();
}
