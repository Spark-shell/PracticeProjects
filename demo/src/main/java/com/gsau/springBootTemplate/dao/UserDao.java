package com.gsau.springBootTemplate.dao;

import com.gsau.springBootTemplate.bean.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 数据访问层
 * @author Wgq
 */
public interface UserDao {

    /**
     * 查询数据
     * @return 用户列表
     */
    List<UserEntity> listUser();

    /**
     * 添加数据
     */
    boolean addUser();
}
