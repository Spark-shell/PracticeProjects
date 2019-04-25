package com.gsau.springboot.controller;

import com.gsau.springboot.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
/**
 * @author WangGuoQing
 * @date 2019/4/18 16:21
 * @Desc 自定义异常处理器
 */
@ControllerAdvice
public class MyExceptionHandler {

    //1、浏览器客户端返回的都是json
//    @ResponseBody
//    @ExceptionHandler(UserNotExistException.class)
//    public Map<String,Object> handleException(Exception e){
//        Map<String,Object> map = new HashMap<>();
//        map.put("code","user.notexist");
//        map.put("message",e.getMessage());
//        return map;
//    }
    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        //传入自己的错误状态码  4xx 5xx
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user1000");
        map.put("message","用户出错啦");

        request.setAttribute("ext",map);
        return "forward:/error";                        //转发到/error
    }
}
