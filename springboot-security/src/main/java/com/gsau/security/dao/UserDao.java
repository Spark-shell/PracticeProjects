package com.gsau.security.dao;

import com.gsau.security.domain.SysUser;

/**
 * @author WangGuoQing
 * @date 2019/4/10 16:12
 * @Desc 数据查询接口
 */
public interface UserDao {
    public SysUser findByUserName(String username);
}
