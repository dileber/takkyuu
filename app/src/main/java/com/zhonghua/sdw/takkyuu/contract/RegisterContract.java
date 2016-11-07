package com.zhonghua.sdw.takkyuu.contract;

import com.drcosu.ndileber.mvp.contract.BaseContract;
import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BaseView;
import com.zhonghua.sdw.takkyuu.data.model.UserModel;

/**
 * Created by shidawei on 16/9/20.
 */
public interface RegisterContract extends BaseContract{

    interface View extends BaseView<Presenter>{
        void toFinish();
        UserModel getUser();
    }

    interface Presenter extends BasePresenter{

        void register();

    }

}
