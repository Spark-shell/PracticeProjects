package com.gsau.portal.pojo.po;

/**
 * @author WangGuoQing
 * @date 2019/5/13 21:19
 * @Desc 
 */
public class Missquestion {
    public long pkid;
    public String userid;
    public String questionid;
    public long createtime;

    public long getPkid() {
        return pkid;
    }

    public void setPkid(long pkid) {
        this.pkid = pkid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }
}
