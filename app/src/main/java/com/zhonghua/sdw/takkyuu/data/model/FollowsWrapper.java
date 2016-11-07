package com.zhonghua.sdw.takkyuu.data.model;

import com.drcosu.ndileber.mvp.data.model.SWrapper;

import java.util.List;

/**
 * Created by shidawei on 2016/10/15.
 */
public class FollowsWrapper extends SWrapper{

    List<FollowsModel> data;

    public List<FollowsModel> getData() {
        return data;
    }

    public void setData(List<FollowsModel> data) {
        this.data = data;
    }
}
