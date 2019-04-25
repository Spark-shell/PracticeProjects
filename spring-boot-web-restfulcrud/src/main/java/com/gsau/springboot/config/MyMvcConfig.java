package com.gsau.springboot.config;

import com.gsau.springboot.component.LoginHandlerInterceptor;
import com.gsau.springboot.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author WangGuoQing
 * @date 2019/4/17 17:43
 * @Desc 使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
 *       @EnableWebMvc 不要接管SpringMVC
 */
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
       // super.addViewControllers(registry);
        //浏览器发送 /gsau 请求来到 success
        registry.addViewController("/gsau").setViewName("success");
    }
    /**
     * @author WangGuoQing
     * @date 2019/4/17 17:44
     * @Desc 所有的WebMvcConfigurerAdapter组件都会一起起作用
     */
    @Bean //将组件注册在容器
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            /**
             * 添加视图映射器
             * @param registry
             */
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }
            /**
             * 添加各种拦截器到容器中
             * @param registry
             */
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //SpringBoot已经做好了静态资源映射静，所以可以不用特意设置对静态资源的拦截器处理，态资源；  *.css , *.js
                //添加注册拦截器
               registry.addInterceptor(new LoginHandlerInterceptor())
                       .addPathPatterns("/**")                                                //设置拦截的URL路径
                       .excludePathPatterns("/index.html","/","/user/login","/hello");        //去除对指定路径的拦截
            }
        };
        return adapter;
    }

    /**
     * 配置区域信息解析器
     * @return
     */
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
