package com.zhonghua.sdw.takkyuu.data.source.service;

import com.drcosu.ndileber.mvp.data.model.SWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by shidawei on 2016/10/11.
 */
public interface MatchServie {

    public static final String ADDMATCH = "api/match/v1/add_match";

    @POST(ADDMATCH)
    Call<SWrapper> addMatch(@Query("toUserId") Integer toUserId, @Query("place") Integer place, @Query("time") Long time);

}
