package com.zhonghua.sdw.takkyuu.data.source;

import android.support.annotation.NonNull;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.BaseRepository;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.tools.HJson;
import com.orhanobut.logger.Logger;
import com.zhonghua.sdw.takkyuu.data.model.UserCommentModel;
import com.zhonghua.sdw.takkyuu.data.model.UserCommentWrapper;
import com.zhonghua.sdw.takkyuu.data.model.UserInfoModel;
import com.zhonghua.sdw.takkyuu.data.model.UserInfosWrapper;
import com.zhonghua.sdw.takkyuu.data.model.UserModel;
import com.zhonghua.sdw.takkyuu.data.model.UserWrapper;
import com.zhonghua.sdw.takkyuu.data.source.local.UserLocalDataSource;
import com.zhonghua.sdw.takkyuu.data.source.remote.UserRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shidawei on 16/8/7.
 */
public class UserRepository extends BaseRepository<UserLocalDataSource,UserRemoteDataSource> implements UserDataSource{

    public static volatile UserRepository instance;
//
//    private final UserDataSource userLocalDataSource;
//
//    private final UserDataSource userRemoteDataSource;

    protected UserRepository(UserLocalDataSource localDataSource, UserRemoteDataSource remoteDataSource) {
        super(localDataSource, remoteDataSource);
    }

//    private UserRepository(UserDataSource userRemoteDataSource, UserDataSource userLocalDataSource) {
//        this.userRemoteDataSource = userRemoteDataSource;
//        this.userLocalDataSource = userLocalDataSource;
//    }

    public static UserRepository getInstance(){
        if(instance==null){
            synchronized (UserRepository.class){
                if(instance==null){
                    instance = new UserRepository(UserLocalDataSource.getInstance(),UserRemoteDataSource.getInstance());
                }
            }
        }
        return instance;
    }

    @Override
    public void checkUser(@NonNull UserModel userModel, BaseDataSource.BaseCallback<UserWrapper> callback) {
        remoteDataSource.checkUser(userModel,callback);
    }

    @Override
    public void saveUser(@NonNull UserModel userModel) {
        localDataSource.saveUser(userModel);
    }

    @Override
    public void getUser(BaseDataSource.BaseCallback<UserModel> callback) {
        localDataSource.getUser(callback);
    }

    @Override
    public void clearUser() {
        localDataSource.clearUser();
    }

    @Override
    public void register(@NonNull UserModel userModel, BaseCallback<UserWrapper> callback) {
        remoteDataSource.register(userModel,callback);
    }

    @Override
    public void repass(String oldpass, String newpass, BaseCallback<SWrapper> callback) {
        remoteDataSource.repass(oldpass, newpass, callback);
    }

    @Override
    public void getUserComment(Integer userId, final BaseCallback<UserCommentWrapper> callback) {
        remoteDataSource.getUserComment(userId, new BaseCallback<UserCommentWrapper>() {
            @Override
            public void onSuccess(final UserCommentWrapper userCommentWrapper) {
                List<Integer> ids = new ArrayList<Integer>();
                for(UserCommentModel userInfoModel:userCommentWrapper.getData()){
                    Integer uidCount = isUserInfoInData(userInfoModel.getCommentfromuserid());
                    if(uidCount==null||uidCount==0){
                        ids.add(userInfoModel.getCommentfromuserid());
                    }
                }
                if(ids==null||ids.size()==0){
                    callback.onSuccess(userCommentWrapper);
                }else{
                    getUserInfos(ids, new BaseCallback<UserInfosWrapper>() {
                        @Override
                        public void onSuccess(UserInfosWrapper userInfosWrapper) {
                            Logger.d("1234qweq"+ HJson.toJson(userCommentWrapper));
                            callback.onSuccess(userCommentWrapper);
                        }

                        @Override
                        public void onFailure(DataSourceException e) {
                            callback.onSuccess(userCommentWrapper);
                        }
                    });
                }

            }

            @Override
            public void onFailure(DataSourceException e) {
                callback.onFailure(e);
            }
        });
    }

    @Override
    public void addUserComment(UserCommentModel userCommentModel, BaseCallback<SWrapper> callback) {

    }

    @Override
    public void getUserInfo(final Integer userId, final BaseCallback<UserInfoModel> callback) {
        if(isRefCache()){
            refOver();
            remoteDataSource.getUserInfo(userId, new BaseCallback<UserInfoModel>() {
                @Override
                public void onSuccess(UserInfoModel userInfoModel) {
                    callback.onSuccess(userInfoModel);
                    if(isUserInfoInData(userId)>0){
                        updateUserInfo(userInfoModel, new BaseCallback<SWrapper>() {
                            @Override
                            public void onSuccess(SWrapper sWrapper) {

                            }

                            @Override
                            public void onFailure(DataSourceException e) {

                            }
                        });
                    }else{
                        addUserInfo(userInfoModel, new BaseCallback<SWrapper>() {
                            @Override
                            public void onSuccess(SWrapper sWrapper) {

                            }

                            @Override
                            public void onFailure(DataSourceException e) {

                            }
                        });
                    }

                }

                @Override
                public void onFailure(DataSourceException e) {
                    callback.onFailure(e);
                }
            });
        }else{
            localDataSource.getUserInfo(userId, callback);
        }
    }

    @Override
    public synchronized void getUserInfos(List<Integer> userIds, final BaseCallback<UserInfosWrapper> callback) {
        remoteDataSource.getUserInfos(userIds, new BaseCallback<UserInfosWrapper>() {
            @Override
            public void onSuccess(final UserInfosWrapper userInfosWrapper) {
                localDataSource.addUserInfos(userInfosWrapper.getData(), new BaseCallback<SWrapper>() {
                    @Override
                    public void onSuccess(SWrapper sWrapper) {
                        callback.onSuccess(userInfosWrapper);
                    }

                    @Override
                    public void onFailure(DataSourceException e) {
                        callback.onFailure(e);
                    }
                });
            }

            @Override
            public void onFailure(DataSourceException e) {
                callback.onFailure(e);
            }
        });
    }

    @Override
    public Integer isUserInfoInData(Integer userId) {
        return localDataSource.isUserInfoInData(userId);
    }

    @Override
    public void addUserInfos(List<UserInfoModel> userInfoModels, BaseCallback<SWrapper> callback) {
        localDataSource.addUserInfos(userInfoModels,callback);
    }

    @Override
    public void addUserInfo(UserInfoModel userInfoModel, BaseCallback<SWrapper> callback) {
        localDataSource.addUserInfo(userInfoModel, callback);
    }

    @Override
    public void updateUserInfo(UserInfoModel userInfoModel, BaseCallback<SWrapper> callback) {
        localDataSource.updateUserInfo(userInfoModel, callback);
    }
}
