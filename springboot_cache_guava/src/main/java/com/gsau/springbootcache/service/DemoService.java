package com.gsau.springbootcache.service;

import com.gsau.springbootcache.bean.Person;
import com.gsau.springbootcache.dao.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author WangGuoQing
 * @date 2019/4/9 13:45
 * @Desc  Service   DemoService 实例注入
 */
@Service
public class DemoService {
    @Autowired              //自动装配personRepository
    private PersonRepository personRepository;
    public List<Person> findAll() {
        return personRepository.findAll();
    }
}