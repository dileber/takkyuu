package com.zhonghua.sdw.takkyuu.data.model;

import com.drcosu.ndileber.mvp.data.model.SModel;

import java.util.List;

/**
 * Created by shidawei on 2016/9/23.
 */
public class LaunchModel extends SModel {

    UserInfoModel userInfoModel;

    List<RubberModel> rubberModels;

    List<FloorModel> floorModels;

    List<BrandModel> brandModels;

    List<UserCommentModel> userCommentModels;

    List<UserSkillModel> userSkillModels;

    List<MatchModel> matchModels;

    List<FollowsModel> followsModels;

    public List<FollowsModel> getFollowsModels() {
        return followsModels;
    }

    public void setFollowsModels(List<FollowsModel> followsModels) {
        this.followsModels = followsModels;
    }


    public List<MatchModel> getMatchModels() {
        return matchModels;
    }

    public void setMatchModels(List<MatchModel> matchModels) {
        this.matchModels = matchModels;
    }


    public List<UserCommentModel> getUserCommentModels() {
        return userCommentModels;
    }

    public void setUserCommentModels(List<UserCommentModel> userCommentModels) {
        this.userCommentModels = userCommentModels;
    }

    public List<UserSkillModel> getUserSkillModels() {
        return userSkillModels;
    }

    public void setUserSkillModels(List<UserSkillModel> userSkillModels) {
        this.userSkillModels = userSkillModels;
    }

    public List<RubberModel> getRubberModels() {
        return rubberModels;
    }

    public void setRubberModels(List<RubberModel> rubberModels) {
        this.rubberModels = rubberModels;
    }

    public List<FloorModel> getFloorModels() {
        return floorModels;
    }

    public void setFloorModels(List<FloorModel> floorModels) {
        this.floorModels = floorModels;
    }

    public List<BrandModel> getBrandModels() {
        return brandModels;
    }

    public void setBrandModels(List<BrandModel> brandModels) {
        this.brandModels = brandModels;
    }

    public UserInfoModel getUserInfoModel() {
        return userInfoModel;
    }

    public void setUserInfoModel(UserInfoModel userInfoModel) {
        this.userInfoModel = userInfoModel;
    }

}
