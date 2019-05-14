package com.gsau.portal.controller.web;

import com.gsau.portal.pojo.po.UserInfo;
import com.gsau.portal.repository.UserRepository;
import com.gsau.portal.util.MD5Util;
import com.gsau.portal.util.StringUtil;
import com.gsau.portal.util.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author WangGuoQing
 * @date 2019/5/13 21:15
 * @Desc 
 */
@Controller
@RequestMapping(value="/sys")
public class SystemController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value="/dologin")
    public ModelAndView dologin(@RequestParam(required = false) String usertel ,
                                @RequestParam(required = false) String userpassword ,
                                HttpServletRequest request){
        System.out.println("--"+usertel+"--"+userpassword);
        ModelAndView modelAndView = new ModelAndView();

        try {
            HttpSession session = request.getSession();
            if(session.getAttribute(SystemConfig.Session_key) != null){
                modelAndView.setViewName("sys/main");
                return modelAndView;
            }
            if(StringUtil.isEmpty(usertel) || StringUtil.isEmpty(userpassword))
            {
                modelAndView.setViewName("index");
                return modelAndView;
            }
            UserInfo user =  userRepository.findByUsertel(usertel);
            if(null == user)
            {
                modelAndView.setViewName("index");
                return modelAndView;
            }

            //变成md5 在查询
            userpassword = MD5Util.getMD5Code(userpassword);

            user = userRepository.findUser(usertel,userpassword);
            if(user==null){
                modelAndView.setViewName("index");
                return modelAndView;
            }

            session.setAttribute(SystemConfig.Session_key, user);
            modelAndView.setViewName("sys/main");
            return modelAndView;

        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.setViewName("index");
            return modelAndView;
        }
    }
}