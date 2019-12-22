package com.gsau.project01.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

 /**
  * @ Author: WangGQ
  * @ Date: 2019/12/21 20:27
  * @ Version: 1.0
  * @ Description:  ConfigurationProperties：默认加载的是application.yaml中的内容，可以通过@PropertySource指定加载的文件是那个
  */
@PropertySource(value = "classpath:user.properties",encoding = "utf-8")
@Component
@ConfigurationProperties(prefix = "user")
public class User {
    public String userName;
    public String password;
    public String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
