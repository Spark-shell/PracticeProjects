package com.gsau.portal.pojo.po;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author WangGuoQing
 * @date 2019/5/13 21:17
 * @Desc 题目的类型
 * 章节类型
 * 随机类型
 * 历年真题
 */

@Entity
@Table(name="tb_question_type")
public class QuestionType {
    @Id
    public long questiontypeid;
    public String questiontypename;

    public long getQuestiontypeid() {
        return questiontypeid;
    }

    public void setQuestiontypeid(long questiontypeid) {
        this.questiontypeid = questiontypeid;
    }

    public String getQuestiontypename() {
        return questiontypename;
    }

    public void setQuestiontypename(String questiontypename) {
        this.questiontypename = questiontypename;
    }
}
