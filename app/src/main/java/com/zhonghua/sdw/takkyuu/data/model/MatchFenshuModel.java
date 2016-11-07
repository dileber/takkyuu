package com.zhonghua.sdw.takkyuu.data.model;

import com.drcosu.ndileber.mvp.data.model.SModel;

public class MatchFenshuModel extends SModel{
    private Integer _id;
    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }
    private Integer matchfenshuid;

    private Integer mid;

    private Integer matchfenshuju;

    private Integer matchselectoneid;

    private Integer matchselecttwoid;

    private Integer matchselectonecount;

    private Integer matchselecttwocount;

    public Integer getMatchfenshuid() {
        return matchfenshuid;
    }

    public void setMatchfenshuid(Integer matchfenshuid) {
        this.matchfenshuid = matchfenshuid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getMatchfenshuju() {
        return matchfenshuju;
    }

    public void setMatchfenshuju(Integer matchfenshuju) {
        this.matchfenshuju = matchfenshuju;
    }

    public Integer getMatchselectoneid() {
        return matchselectoneid;
    }

    public void setMatchselectoneid(Integer matchselectoneid) {
        this.matchselectoneid = matchselectoneid;
    }

    public Integer getMatchselecttwoid() {
        return matchselecttwoid;
    }

    public void setMatchselecttwoid(Integer matchselecttwoid) {
        this.matchselecttwoid = matchselecttwoid;
    }

    public Integer getMatchselectonecount() {
        return matchselectonecount;
    }

    public void setMatchselectonecount(Integer matchselectonecount) {
        this.matchselectonecount = matchselectonecount;
    }

    public Integer getMatchselecttwocount() {
        return matchselecttwocount;
    }

    public void setMatchselecttwocount(Integer matchselecttwocount) {
        this.matchselecttwocount = matchselecttwocount;
    }
}