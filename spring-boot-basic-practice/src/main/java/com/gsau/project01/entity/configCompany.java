package com.gsau.project01.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

 /**
  * @ Author: WangGQ
  * @ Date: 2019/12/21 20:53
  * @ Version: 1.0
  * @ Description: 知名当前类是一个配置类，这类的作用就和声明spring配置文件beans的作用一样，对spring的容器进行一系列的配置
  */
@Configuration
public class configCompany {
  @Bean
  public Company company(){
   return new Company();
  }
}
