package com.gsau.springbootcache.config;

import com.gsau.springbootcache.bean.Person;
import com.gsau.springbootcache.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WangGuoQing
 * @date 2019/4/9 13:50
 * @Desc Configuration: 标注当前类是配置类，并会将当前类内声明的一个或多个以@Bean注解标记的方法的实例纳入到spring容器中，
 * 并且实例名就是方法名。
 */
@Configuration
public class CacheConfig {

    @Autowired
    private DemoService demoService;

    @Bean
    public CacheManager getCacheManager() {
        List<Person> personList = demoService.findAll();                            //所有缓存的名字
        List<String> cacheNames = new ArrayList();
        GuavaCacheManager cacheManager = new GuavaCacheManager();                   //GuavaCacheManager 的数据结构类似  Map<String,Map<Object,Object>>  map =new HashMap<>();
        personList.stream().forEach(person -> {                                     //将数据放入缓存
        String cacheName=person.getId().toString();                                 //用person 的id cacheName
            if(cacheManager.getCache(cacheName)==null){
                cacheNames.add(cacheName);                                          //为每一个person 如果不存在，创建一个新的缓存对象
                cacheManager.setCacheNames(cacheNames);
            }
            Cache cache = cacheManager.getCache(cacheName);                          //缓存对象用person的id当作缓存的key 用person 当作缓存的value
            cache.put(person.getId(),person);
            System.out.println("为 ID 为"+cacheName+ "的person 数据做了缓存");
        });
        return cacheManager;
    }
}
