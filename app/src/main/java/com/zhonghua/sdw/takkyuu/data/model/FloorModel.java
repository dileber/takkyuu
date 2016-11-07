package com.zhonghua.sdw.takkyuu.data.model;

import com.drcosu.ndileber.mvp.data.model.SModel;

public class FloorModel  extends SModel {
    private Integer _id;
    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }


    private Integer floorid;

    private String floorname;

    private Integer floorbrand;

    private Integer floortype;

    private Integer floorstate;

    public Integer getFloorid() {
        return floorid;
    }

    public void setFloorid(Integer floorid) {
        this.floorid = floorid;
    }

    public String getFloorname() {
        return floorname;
    }

    public void setFloorname(String floorname) {
        this.floorname = floorname == null ? null : floorname.trim();
    }

    public Integer getFloorbrand() {
        return floorbrand;
    }

    public void setFloorbrand(Integer floorbrand) {
        this.floorbrand = floorbrand;
    }

    public Integer getFloortype() {
        return floortype;
    }

    public void setFloortype(Integer floortype) {
        this.floortype = floortype;
    }

    public Integer getFloorstate() {
        return floorstate;
    }

    public void setFloorstate(Integer floorstate) {
        this.floorstate = floorstate;
    }
}