package com.gsau.portal.repository;


import com.gsau.portal.pojo.po.Subject;

import java.util.List;

/**
 * Created by liang on 2017/7/11.
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
