package com.zhonghua.sdw.takkyuu.data.source.remote;

import android.support.annotation.NonNull;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.mvp.data.source.remote.BaseRemoteDataSource;
import com.drcosu.ndileber.tools.HRetrofit;
import com.drcosu.ndileber.tools.net.RetCallback;
import com.zhonghua.sdw.takkyuu.app.Config;
import com.zhonghua.sdw.takkyuu.data.model.UserCommentModel;
import com.zhonghua.sdw.takkyuu.data.model.UserCommentWrapper;
import com.zhonghua.sdw.takkyuu.data.model.UserInfoModel;
import com.zhonghua.sdw.takkyuu.data.model.UserInfosWrapper;
import com.zhonghua.sdw.takkyuu.data.model.UserModel;
import com.zhonghua.sdw.takkyuu.data.model.UserWrapper;
import com.zhonghua.sdw.takkyuu.data.source.UserDataSource;
import com.zhonghua.sdw.takkyuu.data.source.service.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by shidawei on 16/8/6.
 */
public class UserRemoteDataSource extends BaseRemoteDataSource implements UserDataSource {

    private static volatile UserRemoteDataSource instance;

    HRetrofit hRetrofit;
    UserService userService;

    private UserRemoteDataSource(){

        hRetrofit = HRetrofit.getInstence(Config.HTTP_URL);
        userService = hRetrofit.retrofit.create(UserService.class);
    }


    public static UserRemoteDataSource getInstance(){
        if (instance==null){
            synchronized (UserRemoteDataSource.class){
                if(instance==null){
                    instance = new UserRemoteDataSource();
                }
            }
        }
        return instance;
    }

    @Override
    public void checkUser(@NonNull UserModel userModel, final BaseDataSource.BaseCallback<UserWrapper> callback) {


        Call<UserWrapper> call = userService.login(userModel.getUsername(),userModel.getUserpass());

        call.enqueue(new RetCallback<UserWrapper>() {
            @Override
            protected void onSuccess(Call<UserWrapper> call, Response<UserWrapper> response) {
                UserWrapper userWrapper = response.body();
                if(userWrapper!=null){
                    setCookie(response);
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new DataSourceException("数据返回为空"));
                }
            }

            @Override
            protected void failure(Call<UserWrapper> call, Throwable throwable) {
                callback.onFailure(new DataSourceException(throwable.getMessage()));
            }
        });
    }

    @Override
    public void saveUser(@NonNull UserModel userModel) {

    }

    @Override
    public void getUser(BaseDataSource.BaseCallback<UserModel> callback) {

    }

    @Override
    public void clearUser() {

    }

    @Override
    public void register(@NonNull UserModel userModel, final BaseCallback<UserWrapper> callback) {
        Call<UserWrapper> call = userService.register(userModel.getUsername(),userModel.getUserpass(),userModel.getUsernikename());

        call.enqueue(new RetCallback<UserWrapper>() {
            @Override
            protected void onSuccess(Call<UserWrapper> call, Response<UserWrapper> response) {
                UserWrapper userWrapper = response.body();
                if(userWrapper!=null){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new DataSourceException("数据返回为空"));
                }
            }

            @Override
            protected void failure(Call<UserWrapper> call, Throwable throwable) {
                callback.onFailure(new DataSourceException(throwable.getMessage()));
            }
        });
    }

    @Override
    public void repass(String oldpass, String newpass, final BaseCallback<SWrapper> callback) {
        Call<SWrapper> call = userService.repass(oldpass,newpass);

        call.enqueue(new RetCallback<SWrapper>() {
            @Override
            protected void onSuccess(Call<SWrapper> call, Response<SWrapper> response) {
                SWrapper sWrapper = response.body();
                if(sWrapper!=null){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new DataSourceException("数据返回为空"));
                }
            }

            @Override
            protected void failure(Call<SWrapper> call, Throwable throwable) {
                callback.onFailure(new DataSourceException(throwable.getMessage()));
            }
        });
    }

    @Override
    public void getUserComment(Integer userId, final BaseCallback<UserCommentWrapper> callback) {
        Call<UserCommentWrapper> call = userService.getUserComment(userId);
        call.enqueue(new RetCallback<UserCommentWrapper>() {
            @Override
            protected void onSuccess(Call<UserCommentWrapper> call, Response<UserCommentWrapper> response) {
                if(response!=null){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new DataSourceException("未知错误"));
                }
            }

            @Override
            protected void failure(Call<UserCommentWrapper> call, Throwable throwable) {
                callback.onFailure(new DataSourceException(throwable.getMessage()));
            }
        });

    }

    @Override
    public void addUserComment(UserCommentModel userCommentModel, BaseCallback<SWrapper> callback) {

    }

    @Override
    public void getUserInfo(Integer userId, final BaseCallback<UserInfoModel> callback) {
        List<Integer> userIds = new ArrayList<>();
        userIds.add(userId);
        Call<UserInfosWrapper> call = userService.getUserInfos(userIds);
        call.enqueue(new RetCallback<UserInfosWrapper>() {
            @Override
            protected void onSuccess(Call<UserInfosWrapper> call, Response<UserInfosWrapper> response) {
                UserInfosWrapper sWrapper = response.body();
                if(sWrapper!=null&&sWrapper.getData()!=null&&sWrapper.getData().size()>0){
                    callback.onSuccess(response.body().getData().get(0));
                }else{
                    callback.onFailure(new DataSourceException("数据返回为空"));
                }
            }

            @Override
            protected void failure(Call<UserInfosWrapper> call, Throwable throwable) {
                callback.onFailure(new DataSourceException(throwable.getMessage()));
            }
        });
    }

    @Override
    public void getUserInfos(List<Integer> userIds, final BaseCallback<UserInfosWrapper> callback) {
        Call<UserInfosWrapper> call = userService.getUserInfos(userIds);
        call.enqueue(new RetCallback<UserInfosWrapper>() {
            @Override
            protected void onSuccess(Call<UserInfosWrapper> call, Response<UserInfosWrapper> response) {
                UserInfosWrapper sWrapper = response.body();
                if(sWrapper!=null){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new DataSourceException("数据返回为空"));
                }
            }

            @Override
            protected void failure(Call<UserInfosWrapper> call, Throwable throwable) {
                callback.onFailure(new DataSourceException(throwable.getMessage()));
            }
        });
    }

    @Override
    public Integer isUserInfoInData(Integer userId) {
        return null;
    }

    @Override
    public void addUserInfos(List<UserInfoModel> userInfoModels, BaseCallback<SWrapper> callback) {

    }

    @Override
    public void addUserInfo(UserInfoModel userInfoModel, BaseCallback<SWrapper> callback) {

    }

    @Override
    public void updateUserInfo(UserInfoModel userInfoModel, BaseCallback<SWrapper> callback) {

    }
}
