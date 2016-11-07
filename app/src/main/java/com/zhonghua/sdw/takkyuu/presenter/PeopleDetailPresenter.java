package com.zhonghua.sdw.takkyuu.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.mvp.presenter.DileberPresenter;
import com.drcosu.ndileber.tools.HJson;
import com.drcosu.ndileber.tools.UDialog;
import com.orhanobut.logger.Logger;
import com.zhonghua.sdw.takkyuu.contract.PeopleContract;
import com.zhonghua.sdw.takkyuu.contract.PeopleDetailContract;
import com.zhonghua.sdw.takkyuu.data.model.UserCommentWrapper;
import com.zhonghua.sdw.takkyuu.data.model.UserInfoModel;
import com.zhonghua.sdw.takkyuu.data.source.SysDataSource;
import com.zhonghua.sdw.takkyuu.data.source.SysRepository;
import com.zhonghua.sdw.takkyuu.data.source.UserDataSource;
import com.zhonghua.sdw.takkyuu.data.source.UserRepository;
import com.zhonghua.sdw.takkyuu.utils.TakkyuuUtils;

/**
 * Created by H2 on 2016/10/12.
 */
public class PeopleDetailPresenter extends DileberPresenter<PeopleDetailContract.View,UserRepository> implements PeopleDetailContract.Presenter{
    SysRepository sysRepository;

    public PeopleDetailPresenter(@NonNull PeopleDetailContract.View view, @NonNull UserRepository mDataSource) {
        super(view, mDataSource);
        sysRepository = SysRepository.getInstance();
    }


    @Override
    public void getUserInfo(Integer userId) {
        /**
         * 刷新用户数据
         */
        mView.loading();
        mDataSource.refStart();
        mDataSource.getUserInfo(userId, new BaseDataSource.BaseCallback<UserInfoModel>() {
            @Override
            public void onSuccess(UserInfoModel userInfoModel) {
                mView.loadDialogDismiss();
                mView.showUserInfo(userInfoModel);
                mView.setTitleName(userInfoModel.getUserinfoname());
            }

            @Override
            public void onFailure(DataSourceException e) {
                mView.loadDialogDismiss();
                mView.showAlert(UDialog.DIALOG_ERROR,e.getMessage());
            }
        });
    }

    @Override
    public String getFloor(Integer id) {
        return TakkyuuUtils.getFloor(id);
    }

    @Override
    public String getRubber(Integer id) {
        return TakkyuuUtils.getRubber(id);
    }

    @Override
    public void getComment(Integer userId) {
        mDataSource.getUserComment(userId, new BaseDataSource.BaseCallback<UserCommentWrapper>() {
            @Override
            public void onSuccess(UserCommentWrapper userCommentWrapper) {
                Logger.sl(Log.DEBUG, HJson.toJson(userCommentWrapper));
                mView.showComment(userCommentWrapper.getData());
            }

            @Override
            public void onFailure(DataSourceException e) {

            }
        });
    }

    @Override
    public void checkFollows(Integer userId) {
        sysRepository.checkFollows(userId, new BaseDataSource.BaseCallback<SWrapper>() {
            @Override
            public void onSuccess(SWrapper sWrapper) {
                mView.setRightButton(sWrapper.getState());
            }

            @Override
            public void onFailure(DataSourceException e) {

            }
        });
    }

    @Override
    public void followsToUser(Integer userId) {
        sysRepository.followsToUser(userId, new BaseDataSource.BaseCallback<SWrapper>() {
            @Override
            public void onSuccess(SWrapper sWrapper) {
                mView.showAlert(UDialog.DIALOG_SUCCESS,sWrapper.getMsg());

            }

            @Override
            public void onFailure(DataSourceException e) {

            }
        });
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }
}
