package com.gsau.project01;

import com.gsau.project01.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static java.lang.System.out;

@SpringBootTest
class Project01ApplicationTests {
    @Autowired
    Person person;
    @Autowired
    ServerProperties serverProperties;
    @Test
    void contextLoads() {
        out.println(person.toString());
    }

}
