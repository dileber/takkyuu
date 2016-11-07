package com.zhonghua.sdw.takkyuu.data.source.service;

import com.drcosu.ndileber.mvp.data.model.SWrapper;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by shidawei on 2016/10/15.
 */
public interface FollowsService {

    public static final String FOLLOWSUSEROK = "api/follows/v1/follows_user_ok";
    @POST(FOLLOWSUSEROK)
    Call<SWrapper> followsUserOk(@Query("followsId")Integer followsId);

    public static final String FOLLOWSUSER = "api/follows/v1/follows_user";
    @POST(FOLLOWSUSER)
    Call<SWrapper> followsUser(@Query("userId")Integer userId);
}
