package com.gsau.portal.controller.app;

import com.gsau.portal.pojo.MessageObject;
import com.gsau.portal.pojo.po.UserInfo;
import com.gsau.portal.repository.UserRepository;
import com.gsau.portal.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author WangGuoQing
 * @date 2019/5/13 21:14
 * @Desc
 */
@RestController
@RequestMapping("/app/userinfo")
public class UserInfoApp {
    @Autowired
    UserRepository userRepository;
    private MessageObject mo = new MessageObject(SystemConfig.mess_succ, "执行成功");

    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public MessageObject dologin(@RequestParam(required = false) String usertel,
                                 @RequestParam(required = false) String userpassword,
                                 HttpServletRequest request) {

        try {
            System.out.println("---usertel---" + usertel + "---" + userpassword);
            if (StringUtil.isEmpty(usertel) || StringUtil.isEmpty(userpassword)) {
                mo.setMdesc("手机号，用户密码不能为空！");
                mo.setCode(SystemConfig.mess_succ);
                return mo;
            }
            userpassword = MD5Util.getMD5Code(userpassword);
            UserInfo user = userRepository.findUser(usertel, userpassword);
            user.setTokenid(TokenManager.getInstance().createToken(user.getUsertel()));
            userRepository.save(user);
            if (user == null) {
                mo.setMdesc("手机号或者用户密码错误");
                mo.setCode(SystemConfig.mess_succ);
                return mo;
            }
            String content = GsonUtil.objTOjson(user);
            mo.setMcontent(content);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mo;
    }

    /**
     * 创建用户信息
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/doregister", method = RequestMethod.POST)
    @ResponseBody
    public MessageObject doregister(@RequestParam(required = false) String usertel,
                                    @RequestParam(required = false) String userpassword,
                                    @RequestParam(required = false) String username,
                                    Model model) {
        System.out.println("usertel" + usertel);
        UserInfo userInfo = userRepository.findByUsertel(usertel);
        MessageObject messageObject = new MessageObject(SystemConfig.mess_succ, "执行成功！");
        try {
            if (userInfo == null) {
                userInfo = new UserInfo();
                userpassword = MD5Util.getMD5Code(userpassword);
                userInfo.setUserpassword(userpassword);
                userInfo.setUsertel(usertel.trim());
                userInfo.setCreatetime(System.currentTimeMillis());
                userInfo.setMstatus(SystemConfig.mstatus_normal);
                userInfo.setUsername(username);
                userInfo.setMsex(SystemConfig.sex_male);
                userInfo.setTokenid(TokenManager.getInstance().createToken(usertel.trim()));
                userRepository.save(userInfo);

                userInfo = userRepository.findByUsertel(usertel.trim());

                String content = GsonUtil.objTOjson(userInfo);
                System.out.println("***=" + content);
                messageObject.setMcontent(content);

            } else {
                messageObject.setCode(SystemConfig.mess_failed);
                messageObject.setMdesc("您已经完成注册,请直接去登录！");
                return messageObject;
            }
            return messageObject;
        } catch (Exception e) {
            e.printStackTrace();
            messageObject.setCode(SystemConfig.mess_failed);
            messageObject.setMdesc("创建失败，执行异常！");
            return messageObject;
        }
    }

    /**
     * 用户退出
     *
     * @param userid
     * @param tokenid
     * @return
     */
    @RequestMapping(value = "/doout", method = RequestMethod.POST)
    @ResponseBody
    public MessageObject doout(@RequestParam(required = false) long userid,
                               @RequestParam(required = false) String tokenid) {
        System.out.println("tokenid" + tokenid);
        UserInfo userInfo = userRepository.findByUserInfoBytokenid(userid, tokenid);
        MessageObject messageObject = new MessageObject(SystemConfig.mess_succ, "执行成功！");
        try {
            if (userInfo != null) {
                userInfo.setTokenid("");
                userRepository.save(userInfo);
            }
            return messageObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageObject;
    }

}
