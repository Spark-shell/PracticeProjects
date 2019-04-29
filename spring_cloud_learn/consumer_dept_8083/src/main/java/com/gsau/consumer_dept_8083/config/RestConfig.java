package com.gsau.consumer_dept_8083.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


/**
 * @author WangGuoQing
 * @date 2019/4/28 13:49
 * @Desc spring框架提供的RestTemplate类可用于在应用中调用rest服务，它简化了与http服务的通信方式，统一了RESTful的标准，封装了http链接，
 * 我们只需要传入url及返回值类型即可。相较于之前常用的HttpClient，RestTemplate是一种更优雅的调用RESTful服务的方式
 *       RestTemplate默认依赖JDK提供http连接的能力（HttpURLConnection），如果有需要的话也可以通过setRequestFactory方法替换为例如
 *              Apache HttpComponents、Netty或OkHttp等其它HTTP library。
 * 引用：https://blog.csdn.net/qq_31491785/article/details/80249917
 */
@Configuration
public class RestConfig {
    @Bean
    @LoadBalanced
    public RestTemplate getTemplate() {
        return new RestTemplate();
    }

    /**
     * 修改Ribbon默认的负载均衡策略
     * @return
     */
    @Bean
    public IRule  myRule(){
      return new RandomRule();
    }
}
