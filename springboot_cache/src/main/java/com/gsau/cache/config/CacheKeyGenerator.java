package com.gsau.cache.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author WangGuoQing
 * @date 2019/4/21 16:54
 * @Desc 自定义Key的生成策略
 */
@Configuration
public class CacheKeyGenerator {
    @Bean("myKeyGenerator")
    public KeyGenerator getKeyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                System.out.println("===当前缓存ID===" +"MY_CACHE->["+ Arrays.asList(params).toString()+"]");
                return "MY_CACHE->["+ Arrays.asList(params).toString()+"]";
            }
        };
    }
}
