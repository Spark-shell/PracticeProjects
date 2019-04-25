package com.gsau.springbootcache.dao;

import com.gsau.springbootcache.bean.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author WangGuoQing
 * @date 2019/4/9 14:10
 * @Desc 
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

}
