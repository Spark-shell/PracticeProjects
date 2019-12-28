package com.gsau.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
  * @ Author: WangGQ
  * @ Date: 2019/12/28 17:06
  * @ Version: 1.0
  * @ Description: 测试 @Component
  */
 @Component
public class Config {
     @Value(value = "192.168.199.00")
     private String Ip;
    @Value(value = "8080")
     private Integer port;
    public String getIp() {
        return Ip;
    }
    public void setIp(String ip) {
        Ip = ip;
    }
    public Integer getPort() {
        return port;
    }
    public void setPort(Integer port) {
        this.port = port;
    }
}
