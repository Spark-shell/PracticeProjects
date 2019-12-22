package com.gsau.project01.testItems;

import com.gsau.project01.entity.File;
import com.gsau.project01.entity.Person;
import com.gsau.project01.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static java.lang.System.out;

/**
 * @ Author: WangGQ
 * @ Date: 2019/12/21 18:27
 * @ Version: 1.0
 * @ Description: springboot的单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestConfig {
    @Autowired
    Person person;
    @Autowired
    File file;
    @Autowired
    User user;
    @Autowired
    ServerProperties serverProperties;
    @Autowired
    ApplicationContext ioc;         //调用spring容器

    @Test
    public void test1() {
        //ioc.containsBean("person")  判断容器中是否包含person
        out.println(ioc.containsBean("company"));
    }
}
