package com.gsau.springbootcache.service;

import com.gsau.springbootcache.bean.Person;
import com.gsau.springbootcache.dao.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
/**
 * @author WangGuoQing
 * @date 2019/4/9 14:47
 * @Desc    personService
 */
@Service
public class PersonService {
    @Autowired
    CacheManager cacheManager;
    @Autowired
    private PersonRepository personRepository;
    /**
     * 读取一个条数据
     * @param id
     * @return
     */
    public Person findOne(Long id) {
        Person person = getCache(id, cacheManager);                 //第一步：到缓存中读取数据
        if (person != null) {
            System.out.println("从缓存中取出:" + person.toString());
        } else {                                                    //第二步：缓存中没有的时候从数据库里面读取
            person = personRepository.findOne(id);
            System.out.println("从数据库中取出:" + person.toString());
        }
        return person;
    }

    /**
     * 保存数据
     * @param person
     * @return
     */
    public Person save(Person person){
        Person p = personRepository.save(person);
        return p;
    }

    /**
     * 从cache中取数据
     * @param id
     * @param cacheManager
     * @return
     */
    public Person getCache(Long id, CacheManager cacheManager) {
        Cache cache = cacheManager.getCache(id.toString());         //返回与给定名称关联的缓存。
        Cache.ValueWrapper valueWrapper = cache.get(id);            //返回一个表示缓存值的（包装器）对象
        Person person = (Person) valueWrapper.get();                //返回缓存中的实际值
        return person;
    }
}
