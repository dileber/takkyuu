package com.zhonghua.sdw.takkyuu.contract;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BaseView;
import com.zhonghua.sdw.takkyuu.data.enums.FloorEnum;
import com.zhonghua.sdw.takkyuu.data.enums.RubberEnum;
import com.zhonghua.sdw.takkyuu.data.model.UserCommentModel;
import com.zhonghua.sdw.takkyuu.data.model.UserInfoModel;

import java.util.HashMap;
import java.util.List;

/**
 * Created by H2 on 2016/10/12.
 */
public interface PeopleDetailContract {

    interface View extends BaseView<Presenter> {

        void showUserInfo(UserInfoModel userInfoModel);

        void setTitleName(String title);

        void showComment(List<UserCommentModel> userCommentModels);

        void setRightButton(int state);

    }

    interface Presenter extends BasePresenter {
        void getUserInfo(Integer userId);
        String getFloor(Integer id);
        String getRubber(Integer id);
        void getComment(Integer userId);
        void checkFollows(Integer userId);
        void followsToUser(Integer userId);
    }

}
