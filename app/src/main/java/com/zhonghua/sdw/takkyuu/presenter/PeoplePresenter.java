package com.zhonghua.sdw.takkyuu.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.presenter.DileberPresenter;
import com.drcosu.ndileber.tools.HJson;
import com.drcosu.ndileber.tools.UDialog;
import com.drcosu.ndileber.tools.UUi;
import com.orhanobut.logger.Logger;
import com.zhonghua.sdw.takkyuu.contract.LoginContract;
import com.zhonghua.sdw.takkyuu.contract.PeopleContract;
import com.zhonghua.sdw.takkyuu.data.enums.FloorEnum;
import com.zhonghua.sdw.takkyuu.data.enums.GredenEnum;
import com.zhonghua.sdw.takkyuu.data.enums.RubberEnum;
import com.zhonghua.sdw.takkyuu.data.model.BrandModel;
import com.zhonghua.sdw.takkyuu.data.model.FloorModel;
import com.zhonghua.sdw.takkyuu.data.model.LaunchModel;
import com.zhonghua.sdw.takkyuu.data.model.RubberModel;
import com.zhonghua.sdw.takkyuu.data.model.UserCommentModel;
import com.zhonghua.sdw.takkyuu.data.model.UserCommentWrapper;
import com.zhonghua.sdw.takkyuu.data.model.UserInfoModel;
import com.zhonghua.sdw.takkyuu.data.model.UserModel;
import com.zhonghua.sdw.takkyuu.data.source.SysDataSource;
import com.zhonghua.sdw.takkyuu.data.source.UserDataSource;
import com.zhonghua.sdw.takkyuu.data.source.UserRepository;
import com.zhonghua.sdw.takkyuu.utils.TakkyuuUtils;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by shidawei on 2016/9/24.
 */
public class PeoplePresenter extends DileberPresenter<PeopleContract.View,SysDataSource> implements PeopleContract.Presenter{
    UserRepository userRepository;

    public PeoplePresenter(@NonNull PeopleContract.View view, @NonNull SysDataSource sysDataSource) {
        super(view, sysDataSource);
        userRepository = UserRepository.getInstance();
    }

    @Override
    public void getUserInfo() {
        mDataSource.getLaunch(new BaseDataSource.BaseCallback<LaunchModel>() {
            @Override
            public void onSuccess(LaunchModel launchModel) {
                mView.showUserInfo(launchModel.getUserInfoModel());
            }

            @Override
            public void onFailure(DataSourceException e) {
                mView.showAlert(UDialog.DIALOG_ERROR,e.getMessage());
            }
        });
    }



    @Override
    public String getFloor(Integer id) {
        return TakkyuuUtils.getFloor(id);
    }

    @Override
    public String getRubber(Integer id) {
        return TakkyuuUtils.getRubber(id);
    }

    @Override
    public void getUserName() {
        mDataSource.getLaunch(new BaseDataSource.BaseCallback<LaunchModel>() {
            @Override
            public void onSuccess(LaunchModel launchModel) {
                mView.setTitleName(launchModel.getUserInfoModel().getUserinfoname());
            }

            @Override
            public void onFailure(DataSourceException e) {
            }
        });
    }

    @Override
    public void updateUserInfo() {
        mView.loading();
        mDataSource.updateUserInfo(mView.getUserInfo(), new BaseDataSource.BaseCallback<SWrapper>() {
            @Override
            public void onSuccess(SWrapper sWrapper) {
                mView.loadDialogDismiss();
                if(sWrapper.getState()==0){
                    mView.showAlert(UDialog.DIALOG_SUCCESS,sWrapper.getMsg());
                }else{
                    mView.showAlert(UDialog.DIALOG_ERROR,sWrapper.getMsg());
                }
                Logger.d(HJson.toJson(sWrapper));
            }

            @Override
            public void onFailure(DataSourceException e) {
                mView.loadDialogDismiss();
                mView.showAlert(UDialog.DIALOG_ERROR,e.getMessage());

            }
        });
    }

    @Override
    public HashMap<String, List<String>> createSubRubber(RubberEnum rubberEnum) {
        return mDataSource.createSubRubber(rubberEnum);
    }

    @Override
    public List<String> createMainRubber(RubberEnum rubberEnum) {
        return mDataSource.createMainRubber(rubberEnum);
    }

    @Override
    public List<String> createMainFloor(FloorEnum floorEnum) {
        return mDataSource.createMainFloor(floorEnum);
    }

    @Override
    public HashMap<String, List<String>> createSubFloor(FloorEnum floorEnum) {
        return mDataSource.createSubFloor(floorEnum);
    }

    @Override
    public List<String> floorEnum() {
        return floorEnum;
    }

    @Override
    public List<Integer> floorEnumId() {
        return floorId;
    }

    @Override
    public List<String> genderEnum() {
        return genderEnum;
    }

    @Override
    public List<Integer> genderEnumId() {
        return genderId;
    }

    @Override
    public List<String> rubberEnum() {
        return rubberEnum;
    }

    @Override
    public List<Integer> rubberEnumId() {
        return rubberId;
    }

    @Override
    public HashMap<String, List<String>> subFloorId(FloorEnum floorEnum) {
        return mDataSource.subFloorId(floorEnum);
    }

    @Override
    public HashMap<String, List<String>> subRubberId(RubberEnum rubberEnum) {
        return mDataSource.subRubberId(rubberEnum);
    }

    @Override
    public List<String> getSkill() {
        return mDataSource.getSkill();
    }

    @Override
    public void getComment() {
        mDataSource.getLaunch(new BaseDataSource.BaseCallback<LaunchModel>() {
            @Override
            public void onSuccess(LaunchModel launchModel) {
                UserInfoModel userInfoModel = launchModel.getUserInfoModel();
                userRepository.getUserComment(userInfoModel.getUid(), new BaseDataSource.BaseCallback<UserCommentWrapper>() {
                    @Override
                    public void onSuccess(UserCommentWrapper userCommentWrapper) {
                        Logger.sl(Log.DEBUG,HJson.toJson(userCommentWrapper));
                        mView.showComment(userCommentWrapper.getData());
                    }

                    @Override
                    public void onFailure(DataSourceException e) {

                    }
                });


            }

            @Override
            public void onFailure(DataSourceException e) {

            }
        });
    }

    List<String> floorEnum = null;
    List<Integer> floorId = null;
    List<String> rubberEnum = null;
    List<Integer> rubberId = null;
    List<String> genderEnum = null;
    List<Integer> genderId = null;

    @Override
    public void start() {
        EnumSet<FloorEnum> floorEna = EnumSet.allOf(FloorEnum.class);
        Iterator<FloorEnum> floorEnumIterator = floorEna.iterator();
        floorEnum = new ArrayList<>();
        floorId = new ArrayList<>();

        while (floorEnumIterator.hasNext()){
            FloorEnum f = floorEnumIterator.next();
            floorEnum.add(f.getName());
            floorId.add(f.getVar());
        }

        EnumSet<RubberEnum> rubberEna = EnumSet.allOf(RubberEnum.class);
        Iterator<RubberEnum> rubberEnumIterator = rubberEna.iterator();
        rubberEnum = new ArrayList<>();
        rubberId = new ArrayList<>();
        while (rubberEnumIterator.hasNext()){
            RubberEnum r = rubberEnumIterator.next();
            rubberEnum.add(r.getName());
            rubberId.add(r.getVar());
        }

        EnumSet<GredenEnum> gredenEna = EnumSet.allOf(GredenEnum.class);
        Iterator<GredenEnum> gredenEnumIterator = gredenEna.iterator();
        genderEnum = new ArrayList<>();
        genderId = new ArrayList<>();
        while (gredenEnumIterator.hasNext()){
            GredenEnum r = gredenEnumIterator.next();
            genderEnum.add(r.getName());
            genderId.add(r.getVar());
        }
    }

    @Override
    public void onDestroy() {

    }
}
