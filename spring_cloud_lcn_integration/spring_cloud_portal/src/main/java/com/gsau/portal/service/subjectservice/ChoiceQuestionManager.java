package com.gsau.portal.service.subjectservice;


import com.gsau.portal.pojo.MessageObject;
import com.gsau.portal.pojo.po.Choicequestion;
import com.gsau.portal.pojo.po.ChoicequestionExplain;

/**
 * @author WangGuoQing
 * @date 2019/5/13 17:41
 * @Desc 
 */
public interface ChoiceQuestionManager {

    /**
     * 创建对象
     * @param cq
     * @param ce
     * @return
     */
    public MessageObject docreate(Choicequestion cq, ChoicequestionExplain ce);

    /**
     * 删除对象
     * @param cq
     * @param ce
     * @return
     */
    public MessageObject dodel(Choicequestion cq, ChoicequestionExplain ce);




}
