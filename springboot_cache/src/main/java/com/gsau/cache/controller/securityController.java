package com.gsau.cache.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class securityController {
    private final String prefix = "pages/";

    /**
     * index
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "success";
    }

    @GetMapping("/userlogin")
    public String login() {
        return prefix + "login";
    }

    @GetMapping("/level1/{path}")
    public String level1() {
        return prefix + "login";
    }

    @GetMapping("/level2/{path}")
    public String level2() {
        return "success";
    }

    @GetMapping("/level3/{path}")
    public String level3() {
        return "success";
    }

    @GetMapping("/level4/{path}")
    public String level4() {
        return "success";
    }
}
