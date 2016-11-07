package com.zhonghua.sdw.takkyuu.data.model;

import com.drcosu.ndileber.mvp.data.model.SWrapper;

import java.util.List;

/**
 * Created by H2 on 2016/10/11.
 */
public class MatchWrapper extends SWrapper {

    List<MatchModel> data;

    public List<MatchModel> getData() {
        return data;
    }

    public void setData(List<MatchModel> data) {
        this.data = data;
    }
}
