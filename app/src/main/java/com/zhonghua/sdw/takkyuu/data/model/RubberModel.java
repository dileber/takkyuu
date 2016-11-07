package com.zhonghua.sdw.takkyuu.data.model;

import com.drcosu.ndileber.mvp.data.model.SModel;

public class RubberModel  extends SModel {

    private Integer _id;
    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }


    private Integer rubberid;

    private String rubbername;

    private Integer rubbertype;

    private Integer rubberbrand;

    private Integer rubberstate;

    public Integer getRubberid() {
        return rubberid;
    }

    public void setRubberid(Integer rubberid) {
        this.rubberid = rubberid;
    }

    public String getRubbername() {
        return rubbername;
    }

    public void setRubbername(String rubbername) {
        this.rubbername = rubbername == null ? null : rubbername.trim();
    }

    public Integer getRubbertype() {
        return rubbertype;
    }

    public void setRubbertype(Integer rubbertype) {
        this.rubbertype = rubbertype;
    }

    public Integer getRubberbrand() {
        return rubberbrand;
    }

    public void setRubberbrand(Integer rubberbrand) {
        this.rubberbrand = rubberbrand;
    }

    public Integer getRubberstate() {
        return rubberstate;
    }

    public void setRubberstate(Integer rubberstate) {
        this.rubberstate = rubberstate;
    }
}