package com.zhonghua.sdw.takkyuu.data.source;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.zhonghua.sdw.takkyuu.data.enums.FloorEnum;
import com.zhonghua.sdw.takkyuu.data.enums.RubberEnum;
import com.zhonghua.sdw.takkyuu.data.model.BrandModel;
import com.zhonghua.sdw.takkyuu.data.model.FloorModel;
import com.zhonghua.sdw.takkyuu.data.model.FollowsModel;
import com.zhonghua.sdw.takkyuu.data.model.FollowsWrapper;
import com.zhonghua.sdw.takkyuu.data.model.LaunchModel;
import com.zhonghua.sdw.takkyuu.data.model.LaunchWrapper;
import com.zhonghua.sdw.takkyuu.data.model.MatchModel;
import com.zhonghua.sdw.takkyuu.data.model.MatchWrapper;
import com.zhonghua.sdw.takkyuu.data.model.RubberModel;
import com.zhonghua.sdw.takkyuu.data.model.UserCommentModel;
import com.zhonghua.sdw.takkyuu.data.model.UserInfoModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shidawei on 2016/9/23.
 */
public interface SysDataSource extends BaseDataSource{

    void launch( BaseDataSource.BaseCallback<LaunchWrapper> callback);

    void saveLaunch(LaunchModel launchModel);

    void getLaunch(BaseDataSource.BaseCallback<LaunchModel> callback);

    void clearLaunch();

    void clearAll();

    FloorModel getFlood(int id);

    RubberModel getRubber(int id);

    BrandModel getBrand(int id);

    void updateUserInfo(UserInfoModel userInfoModel,final BaseDataSource.BaseCallback<SWrapper> callback);

    List<String> createMainFloor(FloorEnum floorEnum);

    HashMap<String, List<String>> createSubFloor(FloorEnum floorEnum);

    HashMap<String, List<String>> createSubRubber(RubberEnum rubberEnum);

    List<String> createMainRubber(RubberEnum rubberEnum);

    HashMap<String,List<String>> subFloorId(FloorEnum floorEnum);

    HashMap<String,List<String>> subRubberId(RubberEnum rubberEnum);

    List<String> getSkill();

    List<UserCommentModel> getComment();

    void addMatch(MatchModel matchModel,BaseCallback<SWrapper> callback);

//    List<MatchModel> getMatch();
    void getMatch(BaseCallback<MatchWrapper> callback);

    void getFollowsToMe(BaseCallback<FollowsWrapper> callback);

    void getFollowsUser(BaseCallback<FollowsWrapper> callback);

    void followsToUser(Integer userId,BaseCallback<SWrapper> callback);

    void followsOk(Integer followsId,BaseCallback<SWrapper> callback);

    void checkFollows(Integer userId,BaseCallback<SWrapper> callback);

}
