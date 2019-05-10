package com.gsau.zull_session.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author WangGuoQing
 * @date 2019/5/7 13:50
 * @Desc redis sesion共享
 *       @RefreshScope 动态刷新
 */
@RestController
@RefreshScope
public class UserManagementController {
    @GetMapping("/getUser")
    public String getUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println("当前微服务的sessionID是： "+session.getId());
        System.out.println("路由器带过来的sessionID是： "+request.getHeader("Cookie"));

        String username = (String) session.getAttribute("username");
        if (StringUtils.isEmpty(username)) {
            username = "testSessionRedis|" + System.currentTimeMillis();
            session.setAttribute("username", username);
        }
        System.out.println("访问端口：" + request.getServerPort());
        return username;
    }
}