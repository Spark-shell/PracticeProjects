package com.gsau.test;

import com.gsau.config.Config;
import com.gsau.controller.impl.AccountConrollerImpl;
import com.gsau.dao.AccountDao;
import com.gsau.dao.impl.AccountDaoImpl;
import com.gsau.dao.impl.AccountDaoImpl1;
import com.gsau.factory.BeanFactory;
import com.gsau.service.impl.AccountServiceImpl;
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
          // AccountConrollerImpl conroller= BeanFactory.getBean("accountConrollerImpl",AccountConrollerImpl.class);
          // AccountConrollerImpl accountConrollerImpl= BeanFactory.getBean("accountConrollerImpl", AccountConrollerImpl.class);
          // AccountDao dao=BeanFactory.getBean("accountDaoImpl", AccountDao.class);
          // dao.saveAccount();
          // accountConrollerImpl.saveAccount();
          // AccountDao dao0=BeanFactory.getBean("accountDaoImpl", AccountDao.class);
          // AccountDao dao1=BeanFactory.getBean("accountDaoImpl", AccountDao.class);
          // AccountDao dao3=BeanFactory.getBean("accountDaoImpl1", AccountDao.class);
          // AccountDao dao4=BeanFactory.getBean("accountDaoImpl1", AccountDao.class);
          // out.println("ApplicationTest.java--main--28-->"+ "@Scope测试"+(dao1==dao0));
          // out.println("ApplicationTest.java--main--29-->"+ "@Scope测试"+(dao3==dao4));
          AccountServiceImpl accountService=BeanFactory.getBean("accountServiceImpl",AccountServiceImpl.class);
          out.println("ApplicationTest.java--main--34-->"+ accountService.findByName("aaa") );
          BeanFactory.close();
     }
}
