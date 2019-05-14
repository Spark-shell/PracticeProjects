package com.gsau.portal.repository;


import com.gsau.portal.pojo.po.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author WangGuoQing
 * @date 2019/5/13 21:20
 * @Desc 
 */
public interface SubjectRepository extends CrudRepository<Subject, Integer> {

    @Query(value = "select * from tb_subject" ,nativeQuery=true)
    List<Subject> queryList();

    @Query(value = "select * from tb_subject where subjectid = ?1" ,nativeQuery=true)
    Subject findSubject(String subjectid);

    @Query(value = "select * from tb_subject where mlevel = ?1" ,nativeQuery=true)
    List<Subject> findSubjectByLevel(int mlevel);

    @Query(value = "select * from tb_subject where parentid = ?1" ,nativeQuery=true)
    List<Subject> findSubjectBypid(String parentid);

}
