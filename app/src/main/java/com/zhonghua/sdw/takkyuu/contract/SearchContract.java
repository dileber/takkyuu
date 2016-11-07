package com.zhonghua.sdw.takkyuu.contract;

import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BaseView;
import com.zhonghua.sdw.takkyuu.data.model.FollowsModel;
import com.zhonghua.sdw.takkyuu.data.model.UserInfoModel;

import java.util.List;

/**
 * Created by shidawei on 2016/10/16.
 */
public interface SearchContract {

    interface View extends BaseView<Presenter>{

        void showUserInfos(List<FollowsModel> list);

    }

    interface Presenter extends BasePresenter{
        void getUserInfos();
    }

}
