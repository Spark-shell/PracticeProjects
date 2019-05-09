package com.gsau.portal.zuul.config;

import com.gsau.portal.zuul.zuul_filters.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
* @Description: 网关过滤器配置进容器中
* @Date: 2019/5/9 17:20
* @author: wgq
* @version: 1.0
*/
@Configuration
public class FilterConfig {
    @Bean
    public ErrorFilter getErrorFilter(){
        return new ErrorFilter();
    }
}
