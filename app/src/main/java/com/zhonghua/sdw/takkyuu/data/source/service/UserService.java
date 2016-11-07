package com.zhonghua.sdw.takkyuu.data.source.service;


import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.zhonghua.sdw.takkyuu.data.model.UserCommentWrapper;
import com.zhonghua.sdw.takkyuu.data.model.UserInfosWrapper;
import com.zhonghua.sdw.takkyuu.data.model.UserWrapper;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by shidawei on 16/8/7.
 */
public interface UserService {
    /**
     * 登录
     */
    public static final String LOGIN = "api/login";

    public static final String REGISTER = "api/user/v1/spring_register";
    public static final String REPASS = "api/user/v1/repass";

    public static final String USERINFOS = "api/user/v1/get_user_infos";
    public static final String USERCOMMENT = "api/user/v1/get_user_comment";


    @POST(LOGIN)
    Call<UserWrapper> login(@Query("userName") String userName, @Query("passWord") String passWord);

    @POST(REGISTER)
    Call<UserWrapper> register(@Query("name") String userName, @Query("pass") String passWord,@Query("nikeName") String nikeName);

    @POST(REPASS)
    Call<SWrapper> repass(@Query("oldPass") String oldPass, @Query("newPass") String newPass);

    @POST(USERINFOS)
    Call<UserInfosWrapper> getUserInfos(@Query("userIds[]") List<Integer> userIds);

    @POST(USERCOMMENT)
    Call<UserCommentWrapper> getUserComment(@Query("userId") Integer userIds);

}
