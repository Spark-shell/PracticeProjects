package com.gsau.project01.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ Author: WangGQ
 * @ Date: 2019/12/21 20:06
 * @ Version: 1.0
 * @ Description: 除了使用 ConfigurationProperties从配置文件中读取实体值的方式之外，还有一种就是@value()(可以作用在属性或者方法。。。)
 */
@Component
public class File {
    @Value("${file.path}")
    public String path;
    @Value("${file.prefix}")
    public String prefix;
    @Value("${file.fileType}")
    public String fileType;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "File{" +
                "path='" + path + '\'' +
                ", prefix='" + prefix + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}
