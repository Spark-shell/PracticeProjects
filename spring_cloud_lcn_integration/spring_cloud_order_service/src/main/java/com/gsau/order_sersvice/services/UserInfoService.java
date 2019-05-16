package com.gsau.order_sersvice.services;

import com.gsau.order_sersvice.projo.po.UserInfo;
import org.springframework.web.bind.annotation.PathVariable;
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
    // /**
    //  * 方式一
    //  * 电话查询
    //  * @param usertel
    //  * @return
    //  */
    // @RequestMapping(value = "/findByUserTel/{usertel}" ,method = RequestMethod.GET)
    // UserInfo findByUserTel(@RequestParam("usertel") String usertel);
    /**
     * 方式二
     * 电话查询
     * @param usertel
     * @return
     */
    @RequestMapping(value = "/findByUserTel/{usertel}" ,method = RequestMethod.GET)
    UserInfo findByUserTel(@PathVariable("usertel") String usertel);

    /**
     * ID查询
     * @param userid
     * @return
     */
    @RequestMapping(value = "/findByUserId/{userid}" ,method = RequestMethod.GET)
    UserInfo findByUserId(@PathVariable("userid") int userid);

    /**
     *  保存用户
     * @param userInfo
     */
    @RequestMapping(value = "/save" ,method = RequestMethod.POST)
    void save(@RequestBody UserInfo userInfo);

    /**
     * 获取所有用户
     * @return
     */
    @RequestMapping(value = "/findAll")
    List<UserInfo> findAll();

    /**
     * 删除用户
     * @param userInfo
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    void delete(@RequestBody UserInfo userInfo);

    /**
     * 根据用户名和密码查询
     * @param usertel
     * @param userpassword    MD5加密过的
     * @return
     */
    @RequestMapping(value = "/findUserByTelAndPwd/{usertel}/{userpassword}",method = RequestMethod.GET)
    UserInfo findUserByTelAndPwd(@PathVariable("usertel") String usertel, @PathVariable("userpassword")String userpassword);

    /**
     *
     * @param userid
     * @param tokenid
     * @return
     */
    @RequestMapping(value = "/findByUserInfoByTokenidAndUserid/{userid}/{tokenid}",method = RequestMethod.GET)
    UserInfo findByUserInfoByTokenidAndUserid(@PathVariable("userid")int userid,@PathVariable("tokenid") String tokenid);
}
