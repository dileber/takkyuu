package com.zhonghua.sdw.takkyuu.data.source;

import android.util.SparseArray;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.BaseRepository;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.tools.HJson;
import com.orhanobut.logger.Logger;
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
import com.zhonghua.sdw.takkyuu.data.model.UserInfosWrapper;
import com.zhonghua.sdw.takkyuu.data.model.UserModel;
import com.zhonghua.sdw.takkyuu.data.source.local.SysLocalDataSource;
import com.zhonghua.sdw.takkyuu.data.source.local.UserLocalDataSource;
import com.zhonghua.sdw.takkyuu.data.source.remote.SysRemoteDataSource;
import com.zhonghua.sdw.takkyuu.data.source.remote.UserRemoteDataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by shidawei on 2016/9/23.
 */
public class SysRepository extends BaseRepository<SysLocalDataSource,SysRemoteDataSource> implements SysDataSource{

    protected SysRepository(SysLocalDataSource localDataSource, SysRemoteDataSource remoteDataSource) {
        super(localDataSource, remoteDataSource);
    }

    public static volatile SysRepository instance;

    public static SysRepository getInstance() {
        if (instance == null) {
            synchronized (SysRepository.class) {
                if (instance == null) {
                    instance = new SysRepository(SysLocalDataSource.getInstance(), SysRemoteDataSource.getInstance());
                }
            }
        }
        return instance;
    }

    @Override
    public void launch(BaseDataSource.BaseCallback<LaunchWrapper> callback) {
        remoteDataSource.launch(callback);
    }


    private static final String LAUNCH = "launch";

    @Override
    public void saveLaunch(LaunchModel launchModel) {
        if(cache==null){
            cache = new HashMap<>();
        }
        cache.put(LAUNCH,launchModel);
        localDataSource.saveLaunch(launchModel);
    }

    @Override
    public void getLaunch(final BaseDataSource.BaseCallback<LaunchModel> callback) {
        if(cache!=null&&cache.containsKey(LAUNCH)){
            callback.onSuccess((LaunchModel) cache.get(LAUNCH));
        }else{
            localDataSource.getLaunch(new BaseDataSource.BaseCallback<LaunchModel>() {
                @Override
                public void onSuccess(LaunchModel launchModel) {
                    saveLaunch(launchModel);
                    callback.onSuccess(launchModel);
                }

                @Override
                public void onFailure(DataSourceException e) {
                    remoteDataSource.getLaunch(new BaseCallback<LaunchModel>() {
                        @Override
                        public void onSuccess(LaunchModel launchModel) {
                            saveLaunch(launchModel);
                            callback.onSuccess(launchModel);
                        }

                        @Override
                        public void onFailure(DataSourceException e) {
                            callback.onFailure(e);
                        }
                    });
                }
            });
        }
    }

    @Override
    public void clearLaunch() {
        localDataSource.clearLaunch();
    }

    @Override
    public void clearAll() {
        localDataSource.clearAll();
    }

    private static final String FLOOD = "flood";


    @Override
    public FloorModel getFlood(int id) {
        if(cache==null){
            cache = new HashMap<>();
        }
        FloorModel floorModel = getSparesArray(FLOOD,id);
        if(floorModel == null){
            floorModel = localDataSource.getFlood(id);
            if(floorModel!=null){
                putSparesArray(FLOOD,id,floorModel);
            }else{
                floorModel = new FloorModel();
                putSparesArray(FLOOD,id,floorModel);
            }
        }
        return floorModel;
    }

    private <T> void putSparesArray(String key,int id,T t){
        SparseArray<T> sparseArray = (SparseArray) cache.get(key);
        if(sparseArray!=null){
            sparseArray.put(id,t);
        }
    }


    private <T> T getSparesArray(String key,int id){
        if(cache.containsKey(key)){
            SparseArray<T> sparseArray = (SparseArray) cache.get(key);
            T t = sparseArray.get(id);
            if(t!=null){
                return t;
            }
        }else{
            cache.put(key,new SparseArray<T>());
        }
        return null;
    }


    private static final String RUBBER = "rubber";


    @Override
    public RubberModel getRubber(int id) {
        if(cache==null){
            cache = new HashMap<>();
        }
        RubberModel rubberModel = getSparesArray(RUBBER,id);
        if(rubberModel == null){
            rubberModel = localDataSource.getRubber(id);
            if(rubberModel!=null){
                putSparesArray(RUBBER,id,rubberModel);
            }else{
                rubberModel = new RubberModel();
                putSparesArray(RUBBER,id,rubberModel);
            }
        }
        return rubberModel;
    }

    private static final String BRAND = "brand";


    @Override
    public BrandModel getBrand(int id) {
        if(cache==null){
            cache = new HashMap<>();
        }
        BrandModel brandModel = getSparesArray(BRAND,id);
        if(brandModel == null){
            brandModel = localDataSource.getBrand(id);
            if(brandModel!=null){
                putSparesArray(BRAND,id,brandModel);
            }else{
                brandModel = new BrandModel();
                putSparesArray(BRAND,id,brandModel);
            }
        }
        return brandModel;
    }

    @Override
    public void updateUserInfo(final UserInfoModel userInfoModel, final BaseCallback<SWrapper> callback) {
        remoteDataSource.updateUserInfo(userInfoModel, new BaseCallback<SWrapper>() {
            @Override
            public void onSuccess(SWrapper sWrapper) {
                if(cache!=null&&cache.containsKey(LAUNCH)){
                    LaunchModel launchModel = (LaunchModel) cache.get(LAUNCH);
                    UserInfoModel uf = launchModel.getUserInfoModel();
                    uf.updateUserInfo(userInfoModel);
                }
                localDataSource.updateUserInfo(userInfoModel,null);
                callback.onSuccess(sWrapper);
            }

            @Override
            public void onFailure(DataSourceException e) {
                callback.onFailure(e);
            }
        });

    }

    @Override
    public List<String> createMainFloor(FloorEnum floorEnum) {
        return localDataSource.createMainFloor(floorEnum);
    }

    @Override
    public HashMap<String, List<String>> createSubFloor(FloorEnum floorEnum) {
        return localDataSource.createSubFloor(floorEnum);
    }

    @Override
    public HashMap<String, List<String>> createSubRubber(RubberEnum rubberEnum) {
        return localDataSource.createSubRubber(rubberEnum);
    }

    @Override
    public List<String> createMainRubber(RubberEnum rubberEnum) {
        return localDataSource.createMainRubber(rubberEnum);
    }

    @Override
    public HashMap<String, List<String>> subFloorId(FloorEnum floorEnum) {
        return localDataSource.subFloorId(floorEnum);
    }

    @Override
    public HashMap<String, List<String>> subRubberId(RubberEnum rubberEnum) {
        return localDataSource.subRubberId(rubberEnum);
    }

    @Override
    public List<String> getSkill() {
        return localDataSource.getSkill();
    }

    @Override
    public List<UserCommentModel> getComment() {
        return localDataSource.getComment();
    }

    @Override
    public void addMatch(final MatchModel matchModel, final BaseCallback<SWrapper> callback) {
        remoteDataSource.addMatch(matchModel, new BaseCallback<SWrapper>() {
            @Override
            public void onSuccess(SWrapper sWrapper) {
                localDataSource.addMatch(matchModel,callback);
            }

            @Override
            public void onFailure(DataSourceException e) {
                callback.onFailure(e);
            }
        });
    }

    @Override
    public void getMatch(final BaseCallback<MatchWrapper> callback) {
        localDataSource.getMatch(new BaseCallback<MatchWrapper>() {
            @Override
            public void onSuccess(final MatchWrapper matchWrapper) {
                UserRepository.getInstance().getUser(new BaseCallback<UserModel>() {
                    @Override
                    public void onSuccess(UserModel userModel) {
                        int user = userModel.getUserid();
                        List<Integer> ids = new ArrayList<Integer>();
                        for(MatchModel matchModel:matchWrapper.getData()){
                            int userTemp;
                            if(matchModel.getMatchtouser().equals(user)){
                                userTemp = matchModel.getMatchfromuser();
                            }else{
                                userTemp = matchModel.getMatchtouser();
                            }
                            Integer uidCount = UserRepository.getInstance().isUserInfoInData(userTemp);
                            if(uidCount==null||uidCount==0){
                                ids.add(userTemp);
                            }
                        }
                        if(ids==null||ids.size()==0){
                            callback.onSuccess(matchWrapper);
                        }else{
                            UserRepository.getInstance().getUserInfos(ids, new BaseCallback<UserInfosWrapper>() {
                                @Override
                                public void onSuccess(UserInfosWrapper userInfosWrapper) {
                                    Logger.d("1234qweq"+ HJson.toJson(matchWrapper));
                                    callback.onSuccess(matchWrapper);
                                }

                                @Override
                                public void onFailure(DataSourceException e) {
                                    callback.onSuccess(matchWrapper);
                                }
                            });
                        }



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

    @Override
    public void getFollowsToMe(final BaseCallback<FollowsWrapper> callback) {
        localDataSource.getFollowsToMe(new BaseCallback<FollowsWrapper>() {
            @Override
            public void onSuccess(final FollowsWrapper followsWrapper) {
                UserRepository.getInstance().getUser(new BaseCallback<UserModel>() {
                    @Override
                    public void onSuccess(UserModel userModel) {
                        int user = userModel.getUserid();
                        List<Integer> ids = new ArrayList<Integer>();
                        for(FollowsModel followsModel:followsWrapper.getData()){
                            Integer uidCount = UserRepository.getInstance().isUserInfoInData(followsModel.getFollowsfromuserid());
                            if(uidCount==null||uidCount==0){
                                ids.add(followsModel.getFollowsfromuserid());
                            }
                        }
                        if(ids==null||ids.size()==0){
                            callback.onSuccess(followsWrapper);
                        }else{
                            UserRepository.getInstance().getUserInfos(ids, new BaseCallback<UserInfosWrapper>() {
                                @Override
                                public void onSuccess(UserInfosWrapper userInfosWrapper) {
                                    callback.onSuccess(followsWrapper);
                                }

                                @Override
                                public void onFailure(DataSourceException e) {
                                    callback.onSuccess(followsWrapper);
                                }
                            });
                        }



                    }

                    @Override
                    public void onFailure(DataSourceException e) {
                        callback.onFailure(e);
                    }
                });
            }

            @Override
            public void onFailure(DataSourceException e) {

            }
        });
    }

    @Override
    public void getFollowsUser(final BaseCallback<FollowsWrapper> callback) {
        localDataSource.getFollowsUser(new BaseCallback<FollowsWrapper>() {
            @Override
            public void onSuccess(final FollowsWrapper followsWrapper) {
                UserRepository.getInstance().getUser(new BaseCallback<UserModel>() {
                    @Override
                    public void onSuccess(UserModel userModel) {
                        int user = userModel.getUserid();
                        List<Integer> ids = new ArrayList<Integer>();
                        for(FollowsModel followsModel:followsWrapper.getData()){
                            Integer uidCount = UserRepository.getInstance().isUserInfoInData(followsModel.getFollowstouserid());
                            if(uidCount==null||uidCount==0){
                                ids.add(followsModel.getFollowstouserid());
                            }
                        }
                        if(ids==null||ids.size()==0){
                            callback.onSuccess(followsWrapper);
                        }else{
                            UserRepository.getInstance().getUserInfos(ids, new BaseCallback<UserInfosWrapper>() {
                                @Override
                                public void onSuccess(UserInfosWrapper userInfosWrapper) {
                                    callback.onSuccess(followsWrapper);
                                }

                                @Override
                                public void onFailure(DataSourceException e) {
                                    callback.onSuccess(followsWrapper);
                                }
                            });
                        }



                    }

                    @Override
                    public void onFailure(DataSourceException e) {
                        callback.onFailure(e);
                    }
                });
            }

            @Override
            public void onFailure(DataSourceException e) {

            }
        });
    }

    @Override
    public void followsToUser(final Integer userId, BaseCallback<SWrapper> callback) {
        remoteDataSource.followsToUser(userId, callback);
    }

    @Override
    public void followsOk(final Integer followsId, final BaseCallback<SWrapper> callback) {
        remoteDataSource.followsOk(followsId, new BaseCallback<SWrapper>() {
            @Override
            public void onSuccess(SWrapper sWrapper) {
                if(sWrapper.getState()==0){
                    localDataSource.followsOk(followsId, new BaseCallback<SWrapper>() {
                        @Override
                        public void onSuccess(SWrapper sWrapper) {

                        }

                        @Override
                        public void onFailure(DataSourceException e) {

                        }
                    });
                }
                callback.onSuccess(sWrapper);

            }

            @Override
            public void onFailure(DataSourceException e) {
                callback.onFailure(e);
            }
        });
    }

    @Override
    public void checkFollows(Integer userId, BaseCallback<SWrapper> callback) {
        localDataSource.checkFollows(userId, callback);
    }

}
