package com.gsau.springBootTemplate.service;

import com.gsau.springBootTemplate.bean.UserVO;

import java.util.List;

/**
 * 缓存用户信息
 * @author Wgq
 */
public interface IUserCacheService {

    /**
     * 查询缓存用户数据
     */
    List<UserVO> listUser();

    /**
     * 添加一个缓存用户
     */
    List<UserVO> addUser();

    /**
     * 删除一个缓存用户
     */
    boolean deleteUser();
}
