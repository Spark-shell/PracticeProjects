package com.gsau.portal.repository;


import com.gsau.portal.pojo.po.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author WangGuoQing
 * @date 2019/5/13 21:20
 * @Desc 
 */
@Transactional
public interface UserRepository extends CrudRepository<UserInfo, Integer> {

    @Query(value="select * from tb_userinfo where usertel = ?1",nativeQuery=true)
    UserInfo findByUsertel(String usertel);

    @Query(value="select * from tb_userinfo where usertel = ?1 and userpassword = ?2",nativeQuery=true)
    UserInfo findUser(String usertel, String userPassword);

    @Query(value = "select * from tb_userinfo limit ?1,?2" ,nativeQuery=true)
    List<UserInfo> queryList(int pageNumber, int pageSize);

    @Query(value="select * from tb_userinfo where userid = ?1",nativeQuery=true)
    UserInfo findByUserid(long userid);

    @Query(value="select * from tb_userinfo where userid = ?1 and tokenid=?2",nativeQuery=true)
    UserInfo findByUserInfoBytokenid(long userid, String tokenid);

}
