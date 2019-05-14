package com.gsau.zull_session.config;

import jdk.nashorn.internal.runtime.GlobalConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author WangGuoQing
 * @date 2019/5/7 15:20
 * @Desc 通过EnableRedisHttpSession注解支持基于Redis存储session，全局共享session对象。
 *     maxInactiveIntervalInSeconds：session过期时间配置。
 *     redisFlushMode：RedisFlushMode.IMMEDIATE
 *                     session刷新模式。配置为RedisFlushMode.IMMEDIATE，
 *      可以确保zuul存储到redis的session对象在请求到micro service中能立即被获取。
 *      在实际开发过程中出现由于没有这个配置值，有时候zuul将session对象存储到了redis，
 *      但是micro service无法立即获取。
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = Integer.MAX_VALUE,redisFlushMode= RedisFlushMode.IMMEDIATE)
public class SessionRedisConfiguration {

}
