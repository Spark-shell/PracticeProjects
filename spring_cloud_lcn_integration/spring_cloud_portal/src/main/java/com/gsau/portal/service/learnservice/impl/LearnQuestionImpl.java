package com.gsau.portal.service.learnservice.impl;


import com.gsau.portal.pojo.MessageObject;
import com.gsau.portal.pojo.po.LearnCurrent;
import com.gsau.portal.pojo.po.LearnQuestion;
import com.gsau.portal.repository.LearnCurrentRepository;
import com.gsau.portal.repository.LearnQuestionRepository;
import com.gsau.portal.service.learnservice.LearnQuestionManager;
import com.gsau.portal.util.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author WangGuoQing
 * @date 2019/5/13 17:40
 * @Desc 
 */
@Service
public class LearnQuestionImpl implements LearnQuestionManager {

    @Autowired
    LearnQuestionRepository learnQuestionRepository;
    @Autowired
    LearnCurrentRepository learnCurrentRepository;

    MessageObject mo;
    @Transactional
    public MessageObject createLearnObj(LearnCurrent lc , LearnQuestion lq){

        mo = new MessageObject();
        try{
            learnQuestionRepository.save(lq);
            learnCurrentRepository.save(lc);
        }catch (Exception e){
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("保存失败！");
            e.printStackTrace();
        }
        return mo;

    }

}
