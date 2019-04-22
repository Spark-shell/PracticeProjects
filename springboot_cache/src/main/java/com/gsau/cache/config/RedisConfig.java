package com.gsau.cache.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsau.cache.entity.Employee;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author WangGuoQing
 * @date 2019/4/22 14:46
 * @Desc Redis缓存配置，缓存对象存储配置成 JSON 方式存储
 */
@Configuration
public class RedisConfig {
    // /**
    //  * 定制RedisTemplate
    //  * @return
    //  */
    // @Bean
    // public RedisTemplate<Object, Employee> configRedisTemplate(RedisConnectionFactory factory) throws UnknownHostException {
    //     RedisTemplate<Object, Employee> template = new RedisTemplate<>();
    //     // 配置连接工厂
    //     template.setConnectionFactory(factory);
    //     Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);   //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
    //     // ObjectMapper om = new ObjectMapper();
    //     // om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);                      // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
    //     // om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);                               // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
    //     // jacksonSeial.setObjectMapper(om);
    //     // template.setValueSerializer(jacksonSeial);                                                  // 值采用json序列化
    //     // template.setKeySerializer(new StringRedisSerializer());                                     //使用StringRedisSerializer来序列化和反序列化redis的key值
    //     //
    //     //
    //     // // 设置hash key 和value序列化模式
    //     // template.setHashKeySerializer(new StringRedisSerializer());
    //     // template.setHashValueSerializer(jacksonSeial);
    //     // template.afterPropertiesSet();
    //     template.setDefaultSerializer(jacksonSeial);
    //
    //     return template;
    // }

    /**
     * 在自义定cacheManager方面，Spring Boot2.X 与Spring Boot1.X 有很大不同，
     * 在1.x版本中，我们配置redis的cacheManager是这种方式：
     *     //缓存管理器
     *     @Bean
     *     public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
     *         RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
     *         //设置缓存过期时间
     *         cacheManager.setDefaultExpiration(10000);
     *         return cacheManager;
     *     }    //缓存管理
     *
     *      然而在2.x版本中，这个代码直接报错，原因是RedisCacheManager取消了1.0版本中的public RedisCacheManager(RedisOperations redisOperations)的这个构造方法，
     * 所以我们无法再用RedisTemplate作为参数来自定义CacheManager。
     * @param factory
     * @return
     */
    @Bean
    public RedisCacheManager confiCacheManager(RedisConnectionFactory factory) {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        //解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        // 配置序列化（解决乱码的问题）      --->RedisCacheConfiguration属性配置
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                .disableCachingNullValues();

        RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
                .cacheDefaults(config)
                .build();
        return cacheManager;
    }
}
