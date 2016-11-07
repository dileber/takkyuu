package com.zhonghua.sdw.takkyuu.data.model;

import com.drcosu.ndileber.mvp.data.model.SModel;

/**
 * Created by shidawei on 2016/10/9.
 */
public class UserSkillModel extends SModel{
    private Integer _id;
    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }
    private Integer skillid;

    private String skillname;

    private Integer skillstate;

    public Integer getSkillid() {
        return skillid;
    }

    public void setSkillid(Integer skillid) {
        this.skillid = skillid;
    }

    public String getSkillname() {
        return skillname;
    }

    public void setSkillname(String skillname) {
        this.skillname = skillname == null ? null : skillname.trim();
    }

    public Integer getSkillstate() {
        return skillstate;
    }

    public void setSkillstate(Integer skillstate) {
        this.skillstate = skillstate;
    }
}
