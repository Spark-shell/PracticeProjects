package com.us.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
/**
 * @author WangGuoQing
 * @date 2019/4/9 16:50
 * @Desc   配置缓存管理工具
 *        @EnableCaching 注解自动化配置合适的缓存管理器（CacheManager），Spring Boot根据下面的顺序去侦测缓存提供
 *        Generic，JCache (JSR-107)，EhCache 2.x，Hazelcast，Infinispan，Redis，Guava，Simple
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{
    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Autowired
    private Environment env;

    /**
     * 连接redis服务器
     * @return  Redis连接的线程安全工厂
     */
    @Bean
    public JedisConnectionFactory redisConnectionFactory() {

        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
        redisConnectionFactory.setHostName(env.getProperty("redis.hostname"));
        redisConnectionFactory.setPort(Integer.parseInt(env.getProperty("redis.port")));
        return redisConnectionFactory;
    }

    /**
     *  spring 封装了RedisTemplate对象来进行对Redis的各种操作，它支持所有的Redis原生的api。RedisTemplate位于spring-data-redis包下
     * @param cf
     * @return redisTemplate
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(cf);
        return redisTemplate;
    }

    /**
     *  配置spring的缓存管理器
     * @param redisTemplate
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setDefaultExpiration(600);     //设置默认过期时间
        return cacheManager;
    }

    /**
     * 缓存错误处理句柄
     * @return
     */
    public CacheErrorHandler errorHandler() {
        return new CacheErrorHandler(){

            @Override
            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
                logger.warn("handleCacheGetError in redis: {}", exception.getMessage());
            }

            @Override
            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
                logger.warn("handleCachePutError in redis: {}", exception.getMessage());
            }

            @Override
            public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
                logger.warn("handleCacheEvictError in redis: {}", exception.getMessage());
            }

            @Override
            public void handleCacheClearError(RuntimeException exception, Cache cache) {
                logger.warn("handleCacheClearError in redis: {}", exception.getMessage());
            }};
    }
}
