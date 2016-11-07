package com.zhonghua.sdw.takkyuu.data.model;

import com.drcosu.ndileber.mvp.data.model.SWrapper;

/**
 * Created by shidawei on 2016/9/23.
 */
public class LaunchWrapper extends SWrapper{

    LaunchModel data;

    public LaunchModel getData() {
        return data;
    }

    public void setData(LaunchModel data) {
        this.data = data;
    }
}
