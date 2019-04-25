package com.us.example.dao;

import com.us.example.bean.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author WangGuoQing
 * @date 2019/4/9 22:54
 * @Desc 
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

}
