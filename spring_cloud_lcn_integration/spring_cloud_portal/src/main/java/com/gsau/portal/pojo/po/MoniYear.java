package com.gsau.portal.pojo.po;


/**
 * @author WangGuoQing
 * @date 2019/5/13 21:17
 * @Desc 模拟题的年份
 */
@Entity
@Table(name="tb_moniyear")
public class MoniYear {
    @Id
    public long moniid;
    public String moniname;
    public String mstatus;
    public int mindex;

    public long getMoniid() {
        return moniid;
    }

    public void setMoniid(long moniid) {
        this.moniid = moniid;
    }

    public String getMoniname() {
        return moniname;
    }

    public void setMoniname(String moniname) {
        this.moniname = moniname;
    }

    public String getMstatus() {
        return mstatus;
    }

    public void setMstatus(String mstatus) {
        this.mstatus = mstatus;
    }

    public int getMindex() {
        return mindex;
    }

    public void setMindex(int mindex) {
        this.mindex = mindex;
    }

}
