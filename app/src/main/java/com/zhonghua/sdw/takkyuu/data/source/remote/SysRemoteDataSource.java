package com.zhonghua.sdw.takkyuu.data.source.remote;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.mvp.data.source.remote.BaseRemoteDataSource;
import com.drcosu.ndileber.tools.HRetrofit;
import com.drcosu.ndileber.tools.net.RetCallback;
import com.zhonghua.sdw.takkyuu.app.Config;
import com.zhonghua.sdw.takkyuu.data.enums.FloorEnum;
import com.zhonghua.sdw.takkyuu.data.enums.RubberEnum;
import com.zhonghua.sdw.takkyuu.data.model.BrandModel;
import com.zhonghua.sdw.takkyuu.data.model.FloorModel;
import com.zhonghua.sdw.takkyuu.data.model.FollowsWrapper;
import com.zhonghua.sdw.takkyuu.data.model.LaunchModel;
import com.zhonghua.sdw.takkyuu.data.model.LaunchWrapper;
import com.zhonghua.sdw.takkyuu.data.model.MatchModel;
import com.zhonghua.sdw.takkyuu.data.model.MatchWrapper;
import com.zhonghua.sdw.takkyuu.data.model.RubberModel;
import com.zhonghua.sdw.takkyuu.data.model.UserCommentModel;
import com.zhonghua.sdw.takkyuu.data.model.UserInfoModel;
import com.zhonghua.sdw.takkyuu.data.model.UserWrapper;
import com.zhonghua.sdw.takkyuu.data.source.SysDataSource;
import com.zhonghua.sdw.takkyuu.data.source.service.FollowsService;
import com.zhonghua.sdw.takkyuu.data.source.service.MatchServie;
import com.zhonghua.sdw.takkyuu.data.source.service.SysService;
import com.zhonghua.sdw.takkyuu.data.source.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by shidawei on 2016/9/23.
 */
public class SysRemoteDataSource extends BaseRemoteDataSource implements SysDataSource{

    private static volatile SysRemoteDataSource instance;

    HRetrofit hRetrofit;
    SysService sysService;
    MatchServie matchServie;
    FollowsService followsService;

    private SysRemoteDataSource(){

        hRetrofit = HRetrofit.getInstence(Config.HTTP_URL);
        sysService = hRetrofit.retrofit.create(SysService.class);
        matchServie = hRetrofit.retrofit.create(MatchServie.class);
        followsService = hRetrofit.retrofit.create(FollowsService.class);
    }


    public static SysRemoteDataSource getInstance(){
        if (instance==null){
            synchronized (SysRemoteDataSource.class){
                if(instance==null){
                    instance = new SysRemoteDataSource();
                }
            }
        }
        return instance;
    }

    @Override
    public void launch(final BaseDataSource.BaseCallback<LaunchWrapper> callback) {
        Call<LaunchWrapper> call = sysService.launch();

        call.enqueue(new RetCallback<LaunchWrapper>() {
            @Override
            protected void onSuccess(Call<LaunchWrapper> call, Response<LaunchWrapper> response) {
                LaunchWrapper launchWrapper = response.body();
                if(launchWrapper!=null){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new DataSourceException("数据返回为空"));
                }
            }

            @Override
            protected void failure(Call<LaunchWrapper> call, Throwable throwable) {
                callback.onFailure(new DataSourceException(throwable.getMessage()));
            }
        });
    }

    @Override
    public void saveLaunch(LaunchModel launchModel) {

    }

    @Override
    public void getLaunch(final BaseDataSource.BaseCallback<LaunchModel> callback) {
        launch(new BaseDataSource.BaseCallback<LaunchWrapper>() {
            @Override
            public void onSuccess(LaunchWrapper launchWrapper) {
                if(launchWrapper.getState()==0){
                    callback.onSuccess(launchWrapper.getData());
                }else{
                    callback.onFailure(new DataSourceException(launchWrapper.getMsg()));
                }
            }

            @Override
            public void onFailure(DataSourceException e) {
                callback.onFailure(e);
            }
        });
    }

    @Override
    public void clearLaunch() {

    }

    @Override
    public void clearAll() {

    }

    @Override
    public FloorModel getFlood(int id) {
        return null;
    }

    @Override
    public RubberModel getRubber(int id) {
        return null;
    }

    @Override
    public BrandModel getBrand(int id) {
        return null;
    }

    @Override
    public void updateUserInfo(UserInfoModel userInfoModel,final BaseDataSource.BaseCallback<SWrapper> callback) {

        Call<SWrapper> call = sysService.updateUserInfo(userInfoModel.getUserinfobirthday().getTime(),userInfoModel.getUserinfogender(),
                userInfoModel.getUserinfoweight(),userInfoModel.getUserinfoheight(),userInfoModel.getUserinfoclub(),userInfoModel.getUserinfofloor(),
                userInfoModel.getUserinfohand(),userInfoModel.getUserinfobackhand(),userInfoModel.getUserinfoflag(),userInfoModel.getUserinfointroduce(),userInfoModel.getUserinfoskill());

        call.enqueue(new RetCallback<SWrapper>() {
            @Override
            protected void onSuccess(Call<SWrapper> call, Response<SWrapper> response) {
                if(response.body()!=null){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new DataSourceException("数据返回为空"));
                }
            }

            @Override
            protected void failure(Call<SWrapper> call, Throwable throwable) {
                callback.onFailure(new DataSourceException("数据返回为空"));
            }
        });

    }

    @Override
    public List<String> createMainFloor(FloorEnum floorEnum) {
        return null;
    }

    @Override
    public HashMap<String, List<String>> createSubFloor(FloorEnum floorEnum) {
        return null;
    }

    @Override
    public HashMap<String, List<String>> createSubRubber(RubberEnum rubberEnum) {
        return null;
    }

    @Override
    public List<String> createMainRubber(RubberEnum rubberEnum) {
        return null;
    }

    @Override
    public HashMap<String, List<String>> subFloorId(FloorEnum floorEnum) {
        return null;
    }

    @Override
    public HashMap<String, List<String>> subRubberId(RubberEnum rubberEnum) {
        return null;
    }

    @Override
    public List<String> getSkill() {
        return null;
    }

    @Override
    public List<UserCommentModel> getComment() {
        return null;
    }

    @Override
    public void addMatch(MatchModel matchModel, final BaseCallback<SWrapper> callback) {
        Call<SWrapper> call =matchServie.addMatch(matchModel.getMatchtouser(),matchModel.getMatchplace(),matchModel.getMatchtime().getTime());
        call.enqueue(new RetCallback<SWrapper>() {
            @Override
            protected void onSuccess(Call<SWrapper> call, Response<SWrapper> response) {
                if(response.body()!=null){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new DataSourceException("数据返回为空"));
                }
            }

            @Override
            protected void failure(Call<SWrapper> call, Throwable throwable) {
                callback.onFailure(new DataSourceException("数据返回为空"));
            }
        });
    }

    @Override
    public void getMatch(BaseCallback<MatchWrapper> callback) {

    }

    @Override
    public void getFollowsToMe(BaseCallback<FollowsWrapper> callback) {

    }

    @Override
    public void getFollowsUser(BaseCallback<FollowsWrapper> callback) {

    }

    @Override
    public void followsToUser(Integer userId, final BaseCallback<SWrapper> callback) {
        Call<SWrapper> call =followsService.followsUser(userId);
        call.enqueue(new RetCallback<SWrapper>() {
            @Override
            protected void onSuccess(Call<SWrapper> call, Response<SWrapper> response) {
                if(response.body()!=null){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new DataSourceException("数据返回为空"));
                }
            }

            @Override
            protected void failure(Call<SWrapper> call, Throwable throwable) {
                callback.onFailure(new DataSourceException("数据返回为空"));
            }
        });
    }

    @Override
    public void followsOk(Integer followsId, final BaseCallback<SWrapper> callback) {
        Call<SWrapper> call =followsService.followsUserOk(followsId);
        call.enqueue(new RetCallback<SWrapper>() {
            @Override
            protected void onSuccess(Call<SWrapper> call, Response<SWrapper> response) {
                if(response.body()!=null){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new DataSourceException("数据返回为空"));
                }
            }

            @Override
            protected void failure(Call<SWrapper> call, Throwable throwable) {
                callback.onFailure(new DataSourceException("数据返回为空"));
            }
        });
    }

    @Override
    public void checkFollows(Integer userId, BaseCallback<SWrapper> callback) {

    }


}
