package com.gsau.project01.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

 /**
  * @ Author: WangGQ
  * @ Date: 2019/12/3 21:31
  * @ Version: 1.0
  * @ Description: 配置Spring Boot的Thymeleaf多个模板位置
  */
@Configuration
public class ResourceConfiguration {
     /**
      * @ Author: WangGQ
      * @ Date: 2019/12/3 21:32
      * @ Version: 1.0
      * @ Description: 模板位置1
      */
    @Bean
    public SpringResourceTemplateResolver fileTemplateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setCacheable(false);
        resolver.setOrder(0);
        resolver.setCharacterEncoding("UTF-8");

        resolver.setCheckExistence(true);
        return resolver;
    }
}