package com.gsau.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.gsau")   //替换 <context:component-scan base-package="com.gsau"></context:component-scan>
@Import(JdbcConfig.class)
public class SpringConfig {

}
