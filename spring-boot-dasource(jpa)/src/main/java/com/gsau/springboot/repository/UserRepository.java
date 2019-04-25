package com.gsau.springboot.repository;

import com.gsau.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author WangGuoQing
 * @date 2019/4/19 13:59
 * @Desc 继承JpaRepository来完成对数据库的操作
 */
public interface UserRepository extends JpaRepository<User,Integer> {
}
