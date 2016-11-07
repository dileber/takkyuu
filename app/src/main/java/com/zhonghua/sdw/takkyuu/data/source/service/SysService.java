package com.zhonghua.sdw.takkyuu.data.source.service;

import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.zhonghua.sdw.takkyuu.data.model.LaunchWrapper;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by shidawei on 2016/9/23.
 */
public interface SysService {


    public static final String LAUNCH = "api/base/v1/launch";

    @GET(LAUNCH)
    Call<LaunchWrapper> launch();

    public static final String UPDATE_USERINFO = "api/user/v1/save_user_info";


    @POST(UPDATE_USERINFO)
    Call<SWrapper> updateUserInfo(@Query("age") Long age, @Query("greden") Integer greden, @Query("weight") Integer weight,
                          @Query("height") Integer height, @Query("club") Integer club, @Query("floor")Integer floor,
                          @Query("hand") Integer hand, @Query("backhand") Integer backhand, @Query("flag") String flag,
                          @Query("instruction") String instruction,@Query("skill") String skill);


}
