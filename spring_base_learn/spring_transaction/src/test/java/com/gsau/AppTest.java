package com.gsau;

import com.gsau.config.SpringConfig;
import com.gsau.dao.IAccountDao;
import com.gsau.domain.Account;
import com.gsau.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(classes = SpringConfig.class)
@ContextConfiguration(value = "classpath:SpringBeans.xml")
public class AppTest {
    @Autowired
    private IAccountService service;

    @Test
    public  void testTransfer(){
        service.findById(1);
    }

}
