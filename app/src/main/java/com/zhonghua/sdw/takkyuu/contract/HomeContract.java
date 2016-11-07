package com.zhonghua.sdw.takkyuu.contract;

import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BaseView;
import com.zhonghua.sdw.takkyuu.data.model.MatchModel;

import java.util.List;

/**
 * Created by H2 on 2016/10/12.
 */
public interface HomeContract {

    interface View extends BaseView<Presenter>{
        void showMatch(List<MatchModel> matchModels);
    }

    interface Presenter extends BasePresenter{
        void getMatch();
    }

}
