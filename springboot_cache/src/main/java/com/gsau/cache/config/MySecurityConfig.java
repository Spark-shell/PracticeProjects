package com.gsau.cache.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author WangGuoQing
 * @date 2019/4/23 16:59
 * @Desc Security配置类
 */
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //定制授权的规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()         //antMatchers()方法所设定的路径支持Ant风格的通配符 //permitAll无条件进行访问
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP2");
        //开启自动配置的登录功能
        http.formLogin();
        //开启自动配置的注销功能
        http.logout();

    }

    /**
     * 定义认证规则
     *          Spring security 5.0中新增了多种加密方式，也改变了密码的格式。在inMemoryAuthentication()后面多了"
     *         .passwordEncoder(new BCryptPasswordEncoder())",这相当于登陆时用BCrypt加密方式对用户密码进行处理。
     *         以前的".password("123456")" 变成了 ".password(new BCryptPasswordEncoder().encode("123456"))" ，
     *         这相当于对内存中的密码进行Bcrypt编码加密。比对时一致，说明密码正确，允许登陆。
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //springboot 1.x的写法
        // auth.inMemoryAuthentication().withUser("user1").password("123456").roles("USER");
        //springboot 2.x的写法
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("VIP1","VIP2","VIP3")
                .and()
                .withUser("dd").password(new BCryptPasswordEncoder().encode("123")).roles("VIP1","VIP2","VIP3");
    }
}
