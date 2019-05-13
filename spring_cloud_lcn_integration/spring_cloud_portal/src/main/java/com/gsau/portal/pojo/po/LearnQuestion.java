package com.gsau.portal.pojo.po;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author WangGuoQing
 * @date 2019/5/13 21:19
 * @Desc 
 */
@Entity
@Table(name="tb_learnquestion")
public class LearnQuestion {
    @Id
    public long pkid;
    public long userid;
    public String questionid;
    public long createtime;
    public String subjectid;
    public String ismistake;
    public String moniname;

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
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

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public String getIsmistake() {
        return ismistake;
    }

    public void setIsmistake(String ismistake) {
        this.ismistake = ismistake;
    }

    public long getPkid() {
        return pkid;
    }

    public void setPkid(long pkid) {
        this.pkid = pkid;
    }

    public String getMoniname() {
        return moniname;
    }

    public void setMoniname(String moniname) {
        this.moniname = moniname;
    }
}
