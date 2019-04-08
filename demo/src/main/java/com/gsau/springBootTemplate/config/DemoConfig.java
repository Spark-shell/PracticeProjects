package com.gsau.springBootTemplate.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取配置文件中前缀是demo的信息,并将读取的数据信息封装成一个实体
 * @author Wgq
 */
@Component
@ConfigurationProperties( prefix = "demo")
public class DemoConfig {
    private int id;
    private String val;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getVal() {
        return val;
    }
    public void setVal(String val) {
        this.val = val;
    }
}
