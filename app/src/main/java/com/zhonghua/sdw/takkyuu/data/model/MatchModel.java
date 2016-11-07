package com.zhonghua.sdw.takkyuu.data.model;

import com.drcosu.ndileber.mvp.data.model.SModel;

import java.util.Date;

public class MatchModel extends SModel{

    private Integer _id;
    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }
    private Integer matchid;

    private String matchname;

    private Date matchtime;

    private Date matchcreatetime;

    private Integer matchfromuser;

    private Integer matchtouser;

    private Integer matchstate;

    private Integer matchplace;

    private Integer matchfenshu;

    private Integer matchkachiid;

    public Integer getMatchfenshu() {
        return matchfenshu;
    }

    public void setMatchfenshu(Integer matchfenshu) {
        this.matchfenshu = matchfenshu;
    }

    public Integer getMatchkachiid() {
        return matchkachiid;
    }

    public void setMatchkachiid(Integer matchkachiid) {
        this.matchkachiid = matchkachiid;
    }


    public Integer getMatchid() {
        return matchid;
    }

    public void setMatchid(Integer matchid) {
        this.matchid = matchid;
    }

    public String getMatchname() {
        return matchname;
    }

    public void setMatchname(String matchname) {
        this.matchname = matchname == null ? null : matchname.trim();
    }

    public Date getMatchtime() {
        return matchtime;
    }

    public void setMatchtime(Date matchtime) {
        this.matchtime = matchtime;
    }

    public Date getMatchcreatetime() {
        return matchcreatetime;
    }

    public void setMatchcreatetime(Date matchcreatetime) {
        this.matchcreatetime = matchcreatetime;
    }

    public Integer getMatchfromuser() {
        return matchfromuser;
    }

    public void setMatchfromuser(Integer matchfromuser) {
        this.matchfromuser = matchfromuser;
    }

    public Integer getMatchtouser() {
        return matchtouser;
    }

    public void setMatchtouser(Integer matchtouser) {
        this.matchtouser = matchtouser;
    }

    public Integer getMatchstate() {
        return matchstate;
    }

    public void setMatchstate(Integer matchstate) {
        this.matchstate = matchstate;
    }

    public Integer getMatchplace() {
        return matchplace;
    }

    public void setMatchplace(Integer matchplace) {
        this.matchplace = matchplace;
    }
}