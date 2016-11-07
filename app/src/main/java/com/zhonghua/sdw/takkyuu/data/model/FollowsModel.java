package com.zhonghua.sdw.takkyuu.data.model;

import com.drcosu.ndileber.mvp.data.model.SModel;

public class FollowsModel  extends SModel {
    private Integer _id;
    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    private Integer followsid;

    private Integer followstouserid;

    private Integer followsfromuserid;

    private Integer followsstate;

    public Integer getFollowsid() {
        return followsid;
    }

    public void setFollowsid(Integer followsid) {
        this.followsid = followsid;
    }

    public Integer getFollowstouserid() {
        return followstouserid;
    }

    public void setFollowstouserid(Integer followstouserid) {
        this.followstouserid = followstouserid;
    }

    public Integer getFollowsfromuserid() {
        return followsfromuserid;
    }

    public void setFollowsfromuserid(Integer followsfromuserid) {
        this.followsfromuserid = followsfromuserid;
    }

    public Integer getFollowsstate() {
        return followsstate;
    }

    public void setFollowsstate(Integer followsstate) {
        this.followsstate = followsstate;
    }
}