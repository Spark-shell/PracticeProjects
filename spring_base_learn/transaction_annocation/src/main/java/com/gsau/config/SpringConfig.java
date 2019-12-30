package com.gsau.config;

import org.springframework.context.annotation.*;
import org.springframework.test.context.ContextConfiguration;

@Configuration
// @ContextConfiguration(locations = "classpath:SpringBeans.xml")
@ComponentScan("com.gsau")   //替换 <context:component-scan base-package="com.gsau"></context:component-scan>
@Import(JdbcConfig.class)
@EnableAspectJAutoProxy
public class SpringConfig {

}
