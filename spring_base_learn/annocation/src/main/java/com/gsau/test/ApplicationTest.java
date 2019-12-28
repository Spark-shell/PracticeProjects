package com.gsau.test;

import com.gsau.config.Config;
import com.gsau.controller.impl.AccountConrollerImpl;
import com.gsau.factory.BeanFactory;
import org.springframework.context.ApplicationContext;

import static java.lang.System.out;

/**
  * @ Author: WangGQ
  * @ Date: 2019/12/28 16:48
  * @ Version: 1.0
  * @ Description: 测试主类
  */
public class ApplicationTest {
     public static void main(String[] args) {
          AccountConrollerImpl conroller= BeanFactory.getBean("accountConrollerImpl",AccountConrollerImpl.class);
          AccountConrollerImpl accountConrollerImpl= BeanFactory.getBean("accountConrollerImpl", AccountConrollerImpl.class);
          accountConrollerImpl.saveAccount();
     }
}
