package com.gsau.portal.controller.web;

import com.gsau.portal.pojo.MessageObject;
import com.gsau.portal.pojo.po.UserInfo;
import com.gsau.portal.repository.UserRepository;
import com.gsau.portal.util.MD5Util;
import com.gsau.portal.util.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author WangGuoQing
 * @date 2019/5/13 21:15
 * @Desc 
 */
@Controller
@RequestMapping("/web/userinfo")
public class UserInfoController {
    @Autowired
    UserRepository userRepository;


    /**
     * 开始页面
     * @return
     */
    @RequestMapping(value="/startpage" , method = RequestMethod.GET)
    public String startpage(){
        return "userinfo/userinfolist";
    }


    /**
     * 新增
     * @return
     */
    @RequestMapping(value="/add" , method = RequestMethod.GET)
    public String add(){
        return "userinfo/userino-add";
    }

    /**
     * 查询用户信息
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value="/finduserinfo" , method = RequestMethod.GET)
    @ResponseBody
    public UserInfo findUserInfo(Model model, HttpSession session){
        UserInfo userInfo =null;
        try {
            if(null != session.getAttribute(SystemConfig.Session_key))
            {
                userInfo = (UserInfo)session.getAttribute(SystemConfig.Session_key);
            }
            return userInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return userInfo;
        }
    }

    /**
     * 创建用户信息
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value="/createuserinfo", method = RequestMethod.POST)
    @ResponseBody
    public MessageObject createUserinfo(@RequestParam(required = false) String usertel ,
                                        @RequestParam(required = false) String username ,
                                        @RequestParam(required = false) String userpassword ,
                                        @RequestParam(required = false) String msex ,
                                        @RequestParam(required = false) String mstatus ,
                                        Model model, HttpSession session){
        UserInfo userInfo =  userRepository.findByUsertel(usertel);
        MessageObject messageObject = new MessageObject(SystemConfig.mess_succ,"执行成功！");
        try {
            if(userInfo == null){
                userInfo = new UserInfo();
                userpassword = MD5Util.getMD5Code(userpassword);
                userInfo.setUserpassword( userpassword );
                userInfo.setUsertel(usertel);
                userInfo.setCreatetime(System.currentTimeMillis());
                userInfo.setMstatus(mstatus);
                userInfo.setUsername(username);
                userInfo.setMsex(msex);
                userRepository.save(userInfo);
            }else{
                messageObject.setCode(SystemConfig.mess_failed);
                messageObject.setMdesc("创建失败，存在相同的对象！");
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
     * 查询用户列表
     * @param model
     * @return
     */
    @RequestMapping(value="/userinfolist" , method = RequestMethod.GET)
    @ResponseBody
    public List<UserInfo> userlist( Model model){
        List<UserInfo> list = (List<UserInfo>) userRepository.findAll();
        if(list != null){
            model.addAttribute("userlist",list);
        }
        return list;
    }

    @RequestMapping(method=RequestMethod.GET , value="/del/{usertel}")
    @ResponseBody
    public void del(@PathVariable String usertel ){
        UserInfo userInfo = userRepository.findByUsertel(usertel);
        if(userInfo != null){
            userRepository.delete(userInfo);
        }
    }


}
