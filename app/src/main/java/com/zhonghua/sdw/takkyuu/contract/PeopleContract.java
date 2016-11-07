package com.zhonghua.sdw.takkyuu.contract;

import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BaseView;
import com.zhonghua.sdw.takkyuu.data.enums.FloorEnum;
import com.zhonghua.sdw.takkyuu.data.enums.RubberEnum;
import com.zhonghua.sdw.takkyuu.data.model.UserCommentModel;
import com.zhonghua.sdw.takkyuu.data.model.UserInfoModel;

import java.util.HashMap;
import java.util.List;

/**
 * Created by shidawei on 2016/9/24.
 */
public interface PeopleContract {

    interface View extends BaseView<Presenter>{

        void showUserInfo(UserInfoModel userInfoModel);

        UserInfoModel getUserInfo();

        void setTitleName(String title);

        void showComment(List<UserCommentModel> userCommentModels);

    }

    interface Presenter extends BasePresenter{
        void getUserInfo();
        String getFloor(Integer id);
        String getRubber(Integer id);
        void getUserName();
        void updateUserInfo();
        HashMap<String, List<String>> createSubRubber(RubberEnum rubberEnum);
        List<String> createMainRubber(RubberEnum rubberEnum);
        List<String> createMainFloor(FloorEnum floorEnum);
        HashMap<String, List<String>> createSubFloor(FloorEnum floorEnum);
        List<String> floorEnum();
        List<Integer> floorEnumId();
        List<String> genderEnum();
        List<Integer> genderEnumId();
        List<String> rubberEnum();
        List<Integer> rubberEnumId();
        HashMap<String, List<String>> subFloorId(FloorEnum floorEnum);
        HashMap<String, List<String>> subRubberId(RubberEnum rubberEnum);
        List<String> getSkill();
        void getComment();

    }

}
