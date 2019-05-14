package com.gsau.order_sersvice.services;

import com.gsau.order_sersvice.projo.Orders;
import com.gsau.order_sersvice.projo.po.UserInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author WangGuoQing
 * @date 2019/5/8 13:22
 * @Desc 微服务接口
 */
public interface UserInfoService {
    /**
     * @author WangGuoQing
     * @date 2019/5/8 13:22
     * @Desc 添加订单
     */
    @RequestMapping(value = "/addOrder",method= RequestMethod.POST)
    void addOder(@RequestBody Orders order);

    UserInfo findByUsertel(String usertel);
    UserInfo findByUserId(int id);
    UserInfo findUser(String usertel, String userPassword);
    List<UserInfo> queryList(int pageNumber, int pageSize);
    UserInfo findByUserid(long userid);
    UserInfo findByUserInfoBytokenid(long userid, String tokenid);
}
