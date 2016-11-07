package com.zhonghua.sdw.takkyuu.presenter;

import android.support.annotation.NonNull;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.presenter.DileberPresenter;
import com.zhonghua.sdw.takkyuu.contract.SearchContract;
import com.zhonghua.sdw.takkyuu.data.model.FollowsWrapper;
import com.zhonghua.sdw.takkyuu.data.source.SysRepository;
import com.zhonghua.sdw.takkyuu.fragment.search.SearchFragment;

/**
 * Created by shidawei on 2016/10/16.
 */
public class SearchPresenter extends DileberPresenter<SearchContract.View,SysRepository> implements SearchContract.Presenter{
    public SearchPresenter(@NonNull SearchContract.View view, @NonNull SysRepository mDataSource) {
        super(view, mDataSource);
    }

    @Override
    public void getUserInfos() {
        mDataSource.getFollowsUser(new BaseDataSource.BaseCallback<FollowsWrapper>() {
            @Override
            public void onSuccess(FollowsWrapper followsWrapper) {


                mView.showUserInfos(followsWrapper.getData());
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
