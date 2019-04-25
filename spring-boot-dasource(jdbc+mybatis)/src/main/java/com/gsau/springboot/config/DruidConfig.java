package com.gsau.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/**
 * @author WangGuoQing
 * @date 2019/4/18 22:13
 * @Desc 如果不自配数据源，Springboot  JDBC使用默认的Tomcat数据源，这里自定义一个druid配置类
 *      用户设置druid和设置druid数据源的属性
 */
@Configuration
public class DruidConfig {
    /**
     * 绑定application文件中存在前缀是spring.datasource项的属性
     * @return
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
       return  new DruidDataSource();
    }

    //配置druid的监控：
    /**
     * 配置一个管理后台的Servlet
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,String> initParams = new HashMap<>();

        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","admin");
        initParams.put("allow","");                 //默认就是允许所有访问
        initParams.put("deny","192.168.199.1");     //配置拒绝访问

        bean.setInitParameters(initParams);
        return bean;
    }
    /**
     * 配置一个web监控的filter
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");    //设置不拦截的路径
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));               //设置过滤的URL
        return  bean;
    }
}
