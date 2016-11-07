package com.zhonghua.sdw.takkyuu.data.model;

import com.drcosu.ndileber.mvp.data.model.SWrapper;

import java.util.List;

/**
 * Created by H2 on 2016/10/11.
 */
public class UserCommentWrapper extends SWrapper{

    List<UserCommentModel> data;

    public List<UserCommentModel> getData() {
        return data;
    }

    public void setData(List<UserCommentModel> data) {
        this.data = data;
    }
}
