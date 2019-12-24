package com.gsau.project01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * @ Author: WangGQ
 * @ Date: 2019/12/4 19:49
 * @ Version: 1.0
 * @ Description: 扩展springMVC,添加默认欢迎页面.
 * 扩展SpringMVC
 *  SpringBoot2使用的Spring5，因此将WebMvcConfigurerAdapter改为WebMvcConfigurer
 *  使用WebMvcConfigurer扩展SpringMVC好处既保留了SpringBoot的自动配置，又能用到我们自己的配置
 */
@Configuration
//public class MvcConfiguration extends WebMvcConfigurerAdapter { //spring 5.0以前的实现方式
public class MvcConfiguration implements WebMvcConfigurer {        //spring 5.0以后的实现方式
     /**
      * @ Author: WangGQ
      * @ Date: 2019/12/24 19:47
      * @ Version: 1.0
      * @ Description:  配置跨域，解决跨域问题
      */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/addView").setViewName("imgs/1-1");
    }
}