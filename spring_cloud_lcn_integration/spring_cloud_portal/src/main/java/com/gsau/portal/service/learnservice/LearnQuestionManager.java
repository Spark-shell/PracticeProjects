package com.gsau.portal.service.learnservice;

import com.gsau.portal.pojo.MessageObject;
import com.gsau.portal.pojo.po.LearnCurrent;
import com.gsau.portal.pojo.po.LearnQuestion;

/**
 * @author WangGuoQing
 * @date 2019/5/13 17:43
 * @Desc 
 */
public interface LearnQuestionManager {

    public MessageObject createLearnObj(LearnCurrent lc, LearnQuestion lq);

}
