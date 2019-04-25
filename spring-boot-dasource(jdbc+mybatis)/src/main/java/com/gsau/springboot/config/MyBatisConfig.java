package com.gsau.springboot.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
/**
 * @author WangGuoQing
 * @date 2019/4/19 10:25
 * @Desc 定制MyBatis配置类
 */
@org.springframework.context.annotation.Configuration
public class MyBatisConfig {
    /**
     * 注入一个配置定制器
     * @return
     */
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(Configuration configuration) {
                //开启驼峰命名法规则，将下划线映射成驼峰
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
