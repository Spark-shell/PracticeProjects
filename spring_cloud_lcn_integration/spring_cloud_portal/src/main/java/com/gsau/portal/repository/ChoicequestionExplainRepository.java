package com.gsau.portal.repository;

import com.liang.pojo.po.Choicequestion;
import com.liang.pojo.po.ChoicequestionExplain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @author WangGuoQing
 * @date 2019/5/13 21:13
 * @Desc 
 */
public interface ChoicequestionExplainRepository extends CrudRepository<ChoicequestionExplain, Integer> {

    @Query(value = "select * from tb_choicequestion_explain where questionid = ?1" ,nativeQuery=true)
    ChoicequestionExplain findChoiceQuestionExplain(String questionid);

}
