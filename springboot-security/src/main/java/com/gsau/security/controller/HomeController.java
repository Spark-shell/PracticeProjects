package com.gsau.security.controller;

import com.gsau.security.domain.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author WangGuoQing
 * @date 2019/4/10 16:12
 * @Desc
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(Model model){
        Msg msg =  new Msg("测试标题","测试内容","额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "home";
    }
}
