package com.zhonghua.sdw.takkyuu.contract;

import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BaseView;
import com.zhonghua.sdw.takkyuu.data.model.UserModel;

/**
 * Created by shidawei on 16/8/6.
 */
public interface LoginContract {
    interface View extends BaseView<Presenter>{

        void toHome();

        UserModel getUser();

    }
    interface Presenter extends BasePresenter{

        void checkUser();

        void getUser();

    }
}
