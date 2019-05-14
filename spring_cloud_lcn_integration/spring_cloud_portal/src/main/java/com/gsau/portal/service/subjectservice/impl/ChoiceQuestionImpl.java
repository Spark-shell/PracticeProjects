package com.gsau.portal.service.subjectservice.impl;

import com.gsau.portal.pojo.MessageObject;
import com.gsau.portal.pojo.po.Choicequestion;
import com.gsau.portal.pojo.po.ChoicequestionExplain;
import com.gsau.portal.repository.ChoicequestionExplainRepository;
import com.gsau.portal.repository.ChoicequestionRepository;
import com.gsau.portal.service.subjectservice.ChoiceQuestionManager;
import com.gsau.portal.util.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author WangGuoQing
 * @date 2019/5/13 17:41
 * @Desc 
 */
@Service
public class ChoiceQuestionImpl implements ChoiceQuestionManager {

    @Autowired
    ChoicequestionRepository choicequestionRepository;

    @Autowired
    ChoicequestionExplainRepository choicequestionExplainRepository;

    MessageObject mo;

    /**
     * 启动事物
     * @param cq
     * @param ce
     */
    @Transactional
    @Override
    public MessageObject docreate(Choicequestion cq, ChoicequestionExplain ce) {
        mo = new MessageObject();
        try{
            choicequestionRepository.save(cq);

            choicequestionExplainRepository.save(ce);
        }catch (Exception e){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("保存失败！");
            e.printStackTrace();
        }
        return mo;

    }

    @Transactional
    @Override
    public MessageObject dodel(Choicequestion cq, ChoicequestionExplain ce) {
        mo = new MessageObject();
        try{
            choicequestionRepository.delete(cq);

            choicequestionExplainRepository.delete(ce);
        }catch (Exception e){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("删除失败！");
            e.printStackTrace();
        }
        return mo;

    }
}
