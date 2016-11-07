package com.zhonghua.sdw.takkyuu.data.model;

import com.drcosu.ndileber.mvp.data.model.SWrapper;

import java.util.List;

/**
 * Created by H2 on 2016/10/11.
 */
public class UserInfosWrapper extends SWrapper {

    List<UserInfoModel> data;

    public List<UserInfoModel> getData() {
        return data;
    }

    public void setData(List<UserInfoModel> data) {
        this.data = data;
    }
}
