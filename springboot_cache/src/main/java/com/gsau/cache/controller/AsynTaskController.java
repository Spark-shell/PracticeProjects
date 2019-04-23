package com.gsau.cache.controller;

import com.gsau.cache.service.AsynTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsynTaskController {
    @Autowired
    AsynTaskService asynTaskService;
    @GetMapping("/say")
    public String say() {
        asynTaskService.hello();
        return "success";
    }
}
