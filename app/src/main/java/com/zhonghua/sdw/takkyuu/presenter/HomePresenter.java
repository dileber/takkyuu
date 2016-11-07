package com.zhonghua.sdw.takkyuu.presenter;

import android.support.annotation.NonNull;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.presenter.DileberPresenter;
import com.zhonghua.sdw.takkyuu.contract.HomeContract;
import com.zhonghua.sdw.takkyuu.contract.PeopleContract;
import com.zhonghua.sdw.takkyuu.data.model.MatchWrapper;
import com.zhonghua.sdw.takkyuu.data.source.SysDataSource;

/**
 * Created by H2 on 2016/10/12.
 */
public class HomePresenter extends DileberPresenter<HomeContract.View,SysDataSource> implements HomeContract.Presenter{

    public HomePresenter(@NonNull HomeContract.View view, @NonNull SysDataSource mDataSource) {
        super(view, mDataSource);
    }

    @Override
    public void getMatch() {
        mDataSource.getMatch(new BaseDataSource.BaseCallback<MatchWrapper>() {
            @Override
            public void onSuccess(MatchWrapper matchWrapper) {
                mView.showMatch(matchWrapper.getData());
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
