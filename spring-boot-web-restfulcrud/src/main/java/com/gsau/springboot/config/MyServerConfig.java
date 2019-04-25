package com.gsau.springboot.config;

import com.gsau.springboot.filter.MyFilter;
import com.gsau.springboot.listener.MyListener;
import com.gsau.springboot.servlet.MyServlet;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
/**
 * @author WangGuoQing
 * @date 2019/4/18 16:50
 * @Desc
 *          1.配置嵌入式的Servlet容器,和在application.properties文件中配置原理是一样的
 *          2.注册servlet的三大组件
 */
@Configuration
public class MyServerConfig {
    /**
     * 注册servlet
     * @return
     */
    @Bean
    public ServletRegistrationBean myServlet(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new MyServlet(),"/myServlet");
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

    /**
     * 注册Filter
     * @return
     */
    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
        return registrationBean;
    }

    /**
     * 注册listener
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean<MyListener> registrationBean = new ServletListenerRegistrationBean<>(new MyListener());
        return registrationBean;
    }
    /**
     * 配置嵌入式的Servlet容器
     * @return
     */
    @Bean
    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
        return new EmbeddedServletContainerCustomizer() {

            //定制嵌入式的Servlet容器相关的规则
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.setPort(8083);            //设置容器的端口号
            }
        };
    }

}
