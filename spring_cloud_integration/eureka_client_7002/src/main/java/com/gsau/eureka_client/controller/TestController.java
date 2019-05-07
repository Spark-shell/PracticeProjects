package com.gsau.eureka_client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author WangGuoQing
 * @date 2019/5/6 20:49
 * @Desc
 */
@RestController
public class TestController {
    @RequestMapping(value = "/test",method = GET)
    public String getInfo(){
        return "访问成功1111111";
    }
}
