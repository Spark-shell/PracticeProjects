package com.gsau.security.config;

import com.gsau.security.util.MD5Util;
import com.gsau.security.security.CustomUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @author WangGuoQing
 * @date 2019/4/10 15:12
 * @Desc WebSecurityConfigurerAdapter：
 *              可以通过继承重写其方法,简单快速的创建一个WebSecurityConfigurer实例。
 *       @EnableWebSecurity注解以及WebSecurityConfigurerAdapter一起配合提供基于web的security。
 *       继承了WebSecurityConfigurerAdapter之后，再加上几行代码，我们就能实现以下的功能：
 *                  要求用户在进入你的应用的任何URL之前都进行验证
 *                  创建一个用户名是“user”，密码是“password”，角色是“ROLE_USER”的用户
 *                  启用HTTP Basic和基于表单的验证
 *                  Spring Security将会自动生成一个登陆页面和登出成功页面
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    UserDetailsService customUserService(){         //注册UserDetailsService 的bean
        return new CustomUserService();
    }

    /**
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
             //构建认证管理器
             auth.userDetailsService(customUserService())       //设置用户服务
                 .passwordEncoder(new PasswordEncoder(){        //设置密码加密器
                                            @Override
                                            public String encode(CharSequence rawPassword) {
                                                return MD5Util.encode((String)rawPassword);
                                            }

                                             /**
                                              *
                                              * @param rawPassword       前台传入
                                              * @param encodedPassword   数据库存储的MD5加密串
                                              * @return
                                              */
                                             @Override
                                            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                                                 boolean flag=MD5Util.encode((String)rawPassword).equals(encodedPassword);
                                                 boolean flag=encodedPassword.equals(MD5Util.encode((String)rawPassword));
                                                 return flag;
                                            }}); //user Details Service验证
    }

    /**
     *  安全校验规则
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()               //任何请求,登录后可以访问
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .permitAll()                            //登录页面用户任意访问
                .and()
                .logout().permitAll();                   //注销行为任意访问


    }



}

