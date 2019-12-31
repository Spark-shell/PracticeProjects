package com.gsau.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/")
public class AccountController {
    @RequestMapping("hlo")
    public String hello(){
        System.out.println("AccountController-AccountController-5->"+"");
        return "index";
    }
}
