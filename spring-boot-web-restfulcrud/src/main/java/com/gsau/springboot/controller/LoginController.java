package com.gsau.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;
/**
 * @author WangGuoQing
 * @date 2019/4/17 17:52
 * @Desc 登录
 */
@Controller
public class LoginController {
   // @DeleteMapping          //映射请求方式
   // @PutMapping             //映射请求方式
   // @GetMapping             //映射请求方式

    //@RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @PostMapping(value = "/user/login")         //rest方式的mapping和 @RequestMapping(value = "/user/login",method = RequestMethod.POST)相同
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){
        if(!StringUtils.isEmpty(username) && "admin".equals(password)){
            session.setAttribute("loginUser",username);                //session中保存用户信息
            return "redirect:/main.html";                                  //登陆成功，防止表单重复提交，可以重定向到主页
        }else{
            map.put("msg","用户名密码错误");                               //登录消息
            return  "login";
        }

    }
}




