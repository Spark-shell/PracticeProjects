package com.gsau.order.mapper;

import com.gsau.order_sersvice.projo.po.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 获取全部
     * @return
     */
    List<UserInfo> findAll();

    /**
     * 删除
     * @param userInfo
     */
    void delete(UserInfo userInfo);

    /**
     * 用户名和密码查询
     * @param usertel
     * @param userpassword
     * @return
     */
    UserInfo findUserByTelAndPwd(@Param("usertel") String usertel, @Param("userpassword")String userpassword);
}
