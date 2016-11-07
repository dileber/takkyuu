package com.zhonghua.sdw.takkyuu.data.source;

import android.support.annotation.NonNull;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.zhonghua.sdw.takkyuu.data.model.UserCommentModel;
import com.zhonghua.sdw.takkyuu.data.model.UserCommentWrapper;
import com.zhonghua.sdw.takkyuu.data.model.UserInfoModel;
import com.zhonghua.sdw.takkyuu.data.model.UserInfosWrapper;
import com.zhonghua.sdw.takkyuu.data.model.UserModel;
import com.zhonghua.sdw.takkyuu.data.model.UserWrapper;

import java.util.List;

/**
 * Created by shidawei on 16/8/6.
 */
public interface UserDataSource extends BaseDataSource{

    void checkUser(@NonNull UserModel userModel, BaseCallback<UserWrapper> callback);

    void saveUser(@NonNull UserModel userModel);

    void getUser(BaseCallback<UserModel> callback);

    void clearUser();

    void register(@NonNull UserModel userModel, BaseCallback<UserWrapper> callback);

    void repass(String oldpass,String newpass,BaseCallback<SWrapper> callback);

    void getUserComment(Integer userId, BaseCallback<UserCommentWrapper> callback);

    void addUserComment(UserCommentModel userCommentModel,BaseCallback<SWrapper> callback);

    void getUserInfo(Integer userId,BaseCallback<UserInfoModel> callback);

    void getUserInfos(List<Integer> userIds, BaseCallback<UserInfosWrapper> callback);

    Integer isUserInfoInData(Integer userId);

    void addUserInfos(List<UserInfoModel> userInfoModels,BaseCallback<SWrapper> callback);

    void addUserInfo(UserInfoModel userInfoModel,BaseCallback<SWrapper> callback);

    void updateUserInfo(UserInfoModel userInfoModel,BaseCallback<SWrapper> callback);
}
