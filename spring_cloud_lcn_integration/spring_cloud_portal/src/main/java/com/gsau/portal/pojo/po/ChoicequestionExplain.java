package com.gsau.portal.pojo.po;


/**
 * @author WangGuoQing
 * @date 2019/5/13 21:16
 * @Desc 选择题的解析
 */
@Entity
@Table(name="tb_choicequestion_explain")
public class ChoicequestionExplain {
    @Id
    public String questionid;
    public String mexplain;

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public String getMexplain() {
        return mexplain;
    }

    public void setMexplain(String mexplain) {
        this.mexplain = mexplain;
    }
}
