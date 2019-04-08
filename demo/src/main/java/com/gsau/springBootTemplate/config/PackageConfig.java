package com.gsau.springBootTemplate.config;

import com.gsau.springBootTemplate.SpringBootTemplateApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 打war包所需要使用的工具类
 * @author Wgq
 */
@ConditionalOnProperty(name = "package.type", havingValue = "war")
public class PackageConfig extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
        return builder.sources(SpringBootTemplateApplication.class);
    }
}
