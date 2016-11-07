package com.zhonghua.sdw.takkyuu.data.source.local;

import android.media.MediaActionSound;
import android.util.Log;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.data.model.SModel;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.mvp.data.source.local.BaseLocalDataSource;
import com.drcosu.ndileber.mvp.data.source.local.DBManager;
import com.drcosu.ndileber.tools.HJson;
import com.drcosu.ndileber.tools.HPref;
import com.orhanobut.logger.Logger;
import com.zhonghua.sdw.takkyuu.app.Config;
import com.zhonghua.sdw.takkyuu.app.TakkyuuApplication;
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
import com.zhonghua.sdw.takkyuu.data.model.UserSkillModel;
import com.zhonghua.sdw.takkyuu.data.source.SysDataSource;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by shidawei on 2016/9/23.
 */
public class SysLocalDataSource extends BaseLocalDataSource implements SysDataSource{

//    String brand = "CREATE TABLE brand (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,brandid INTEGER,brandname TEXT,brandstate INTEGER)";
//    String rubber = "CREATE TABLE rubber (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,rubberid INTEGER,rubbername TEXT,rubbertype INTEGER,rubberbrand INTEGER,rubberstate INTEGER)";
//    String floor = "CREATE TABLE floor (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,floorid INTEGER,floorname TEXT,floorbrand INTEGER,floortype INTEGER,floorstate INTEGER)";
//    String comment = "CREATE TABLE comment (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,commentid INTEGER,commentcontext TEXT,commentfromuserid INTEGER,commenttouserid INTEGER,commentstate INTEGER)";
//    String skill = "CREATE TABLE skill (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,skillid INTEGER,skillname TEXT,skillstate INTEGER)";
//    String match = "CREATE TABLE match (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,matchid INTEGER,matchname TEXT,matchtime INTEGER,matchcreatetime INTEGER,matchfromuser INTEGER,matchtouser INTEGER,matchstate INTEGER,matchplace INTEGER)";



    private static volatile SysLocalDataSource instance;

    private SysLocalDataSource(){

//        List<String> list = new ArrayList<>();
//        list.add(brand);
//        list.add(rubber);
//        list.add(floor);
//        list.add(comment);
//        list.add(skill);
//        list.add(match);
//        dbManager = dbManager.getDB("launch",Config.DB_VERSION,list);
        dbManager = Config.dbManager;


    }

    public static SysLocalDataSource getInstance(){
        if (instance==null){
            synchronized (SysLocalDataSource.class){
                if(instance==null){
                    instance = new SysLocalDataSource();
                }
            }
        }
        return instance;
    }


    @Override
    public void launch(BaseDataSource.BaseCallback<LaunchWrapper> callback) {

    }

    private void saveBrand(List<BrandModel> brandModels){
        if(brandModels!=null&&brandModels.size()>0){
            hPref.put(Config.PREF_LOCAL_CACHE,Config.PERF_BRANDS,HJson.toJson(brandModels));
            List<Object[]> objectsBrand = new ArrayList<>();
            for(BrandModel brandModel:brandModels){
                Object[] ob = {brandModel.getBrandid(),brandModel.getBrandname(),brandModel.getBrandstate()};
                objectsBrand.add(ob);
            }
            int m = dbManager.dataBatch("insert into brand(brandid,brandname,brandstate) values(?,?,?)",objectsBrand, DBManager.Type.INSERT);
            Logger.sl(Log.DEBUG,"brand 数据库存了"+m+"条数据");
        }
    }

    private void saveRubber(List<RubberModel> rubberModels){
        if(rubberModels!=null&&rubberModels.size()>0) {
            hPref.put(Config.PREF_LOCAL_CACHE,Config.PERF_RUBBERS,HJson.toJson(rubberModels));
            List<Object[]> objects = new ArrayList<>();
            for (RubberModel rubberModel : rubberModels) {
                Object[] ob = {rubberModel.getRubberid(), rubberModel.getRubbername(), rubberModel.getRubbertype(), rubberModel.getRubberbrand(), rubberModel.getRubberstate()};
                objects.add(ob);
            }
            int m = dbManager.dataBatch("insert into rubber(rubberId, rubberName, rubberType, rubberBrand, rubberState) values(?,?,?,?,?)", objects, DBManager.Type.INSERT);
            Logger.sl(Log.DEBUG,"rubber 数据库存了"+m+"条数据");

        }
    }

    private void saveFloor(List<FloorModel> floorModels){
        if(floorModels!=null&&floorModels.size()>0){
            hPref.put(Config.PREF_LOCAL_CACHE,Config.PERF_FLOODS,HJson.toJson(floorModels));
            List<Object[]> objects = new ArrayList<>();
            for(FloorModel floorModel:floorModels){
                Object[] ob = {floorModel.getFloorid(),floorModel.getFloorname(),floorModel.getFloorbrand(),floorModel.getFloortype(),floorModel.getFloorstate()};
                objects.add(ob);
            }
            int m = dbManager.dataBatch("insert into floor(floorId, floorName, floorBrand, floorType, floorState) values(?,?,?,?,?)",objects, DBManager.Type.INSERT);
            Logger.sl(Log.DEBUG,"floor 数据库存了"+m+"条数据");
        }
    }

    private void saveSkill(List<UserSkillModel> userSkillModels){
        if(userSkillModels!=null&&userSkillModels.size()>0){
            hPref.put(Config.PREF_LOCAL_CACHE,Config.PERF_SKILLS,HJson.toJson(userSkillModels));
            List<Object[]> objects = new ArrayList<>();
            for(UserSkillModel userSkillModel:userSkillModels){
                Object[] ob = {userSkillModel.getSkillid(),userSkillModel.getSkillname(),userSkillModel.getSkillstate()};
                objects.add(ob);
            }
            int m = dbManager.dataBatch("insert into skill(skillid, skillname, skillstate) values(?,?,?)",objects, DBManager.Type.INSERT);
            Logger.sl(Log.DEBUG,"skill 数据库存了"+m+"条数据");
        }
    }

    private void saveMatch(List<MatchModel> matchModels) {
        if(matchModels!=null&&matchModels.size()>0){
            //hPref.put(Config.PREF_LOCAL_CACHE,Config.PERF_MATCH,HJson.toJson(matchModels));
            List<Object[]> objects = new ArrayList<>();
            for(MatchModel match:matchModels){
                Object[] ob = {match.getMatchid(),match.getMatchname(),match.getMatchtime(),match.getMatchcreatetime(),match.getMatchfromuser(),match.getMatchtouser(),match.getMatchstate(),match.getMatchplace(),match.getMatchfenshu(),match.getMatchkachiid()};
                objects.add(ob);
            }
            int m = dbManager.dataBatch("insert into match(matchid,matchname,matchtime,matchcreatetime,matchfromuser,matchtouser,matchstate,matchplace,matchfenshu,matchkachiid) values(?,?,?,?,?,?,?,?,?,?)",objects, DBManager.Type.INSERT);
            Logger.sl(Log.DEBUG,"match 数据库存了"+m+"条数据");
        }
    }


    private void saveComment(List<UserCommentModel> userCommentModels){
        if(userCommentModels!=null&&userCommentModels.size()>0){
            hPref.put(Config.PREF_LOCAL_CACHE,Config.PERF_COMMENTS,HJson.toJson(userCommentModels));
            List<Object[]> objects = new ArrayList<>();
            for(UserCommentModel userCommentModel:userCommentModels){
                Object[] ob = {userCommentModel.getCommentid(),userCommentModel.getCommentcontext(),userCommentModel.getCommentfromuserid(),userCommentModel.getCommenttouserid(),userCommentModel.getCommentstate()};
                objects.add(ob);
            }
            int m = dbManager.dataBatch("insert into comment(commentid,commentcontext,commentfromuserid,commenttouserid,commentstate) values(?,?,?,?,?)",objects, DBManager.Type.INSERT);
            Logger.sl(Log.DEBUG,"comment 数据库存了"+m+"条数据");
        }
    }




//    private List<FloorModel> getType(FloorEnum floorEnum){
//        HPref.getInstance().get(Config.PREF_LOCAL_CACHE,Config.PERF_FLOOD_TYPE+floorEnum.getVar(),null,)
//        Object[] arg = {floorEnum.getVar()};
//        try {
//            List<FloorModel> floorModel = dbManager.queryData2T("select * from floor where floorType = ?",arg,FloorModel.class);
//
//            return floorModel;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


    @Override
    public void saveLaunch(LaunchModel launchModel) {
        HPref.getInstance().put(Config.PERF_APP,Config.PERF_LANUCH,launchModel);
        deleteLaunchDatabase();
        List<BrandModel> brandModels = launchModel.getBrandModels();
        saveBrand(brandModels);
        List<FloorModel> floorModels = launchModel.getFloorModels();
        saveFloor(floorModels);
        saveRubber(launchModel.getRubberModels());
        saveComment(launchModel.getUserCommentModels());
        saveSkill(launchModel.getUserSkillModels());
        saveMatch(launchModel.getMatchModels());
        
        saveFollows(launchModel.getFollowsModels());

        createFloor(FloorEnum.diaphragm);
        createFloor(FloorEnum.JapanType);
        createFloor(FloorEnum.Straight);

        createRubber(RubberEnum.ChangRubber);
        createRubber(RubberEnum.FanRubber);
        createRubber(RubberEnum.ShengRubber);
        createRubber(RubberEnum.ZhengRubber);

//        List<String> brand = new ArrayList<>();
//        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
//
//        for(BrandModel brandModel:brandModels){
//            brand.add(brandModel.getBrandname());
//            List<String> floors = new ArrayList<>();
//            Iterator<FloorModel> iterator = floorModels.iterator();
//            while (iterator.hasNext()){
//                FloorModel floor = iterator.next();
//                if(brandModel.getBrandid().equals(floor.getFloorbrand())){
//                    floors.add(floor.getFloorname());
//                    iterator.remove();
//                }
//            }
//            if(floors.size()>0){
//                map.put(brandModel.getBrandname(),floors);
//            }
//        }
        //hPref.put(Config.PREF_LOCAL_CACHE,Config.PERF_BRAND,HJson.toJson(brand));
        //hPref.put(Config.PREF_LOCAL_CACHE,Config.PERF_FLOOD_L1,HJson.toJson(map));
    }

    private void saveFollows(List<FollowsModel> followsModels) {
        if(followsModels!=null&&followsModels.size()>0){
            //hPref.put(Config.PREF_LOCAL_CACHE,Config.PERF_MATCH,HJson.toJson(matchModels));
            List<Object[]> objects = new ArrayList<>();
            for(FollowsModel follows:followsModels){
                Object[] ob = {follows.getFollowsid(),follows.getFollowsfromuserid(),follows.getFollowstouserid(),follows.getFollowsstate()};
                objects.add(ob);
            }
            int m = dbManager.dataBatch("insert into follows(followsid,followsfromuserid,followstouserid,followsstate) values(?,?,?,?)",objects, DBManager.Type.INSERT);
            Logger.sl(Log.DEBUG,"match 数据库存了"+m+"条数据");
        }
    }


    private void createFloor(FloorEnum floorEnum) {
        String brands = hPref.get(Config.PREF_LOCAL_CACHE,Config.PERF_BRANDS,null,String.class);
        if(brands!=null){
            List<BrandModel> brandModels = HJson.fromJsonList(brands,BrandModel.class);
            Object[] arg = {floorEnum.getVar()};

            try {
                List<FloorModel> floorModel = dbManager.queryData2T("select * from floor where floorType = ?",arg,FloorModel.class);
                List<String> brand = new ArrayList<>();
                HashMap<String, List<String>> map = new HashMap<String, List<String>>();
                HashMap<Integer,List<String>> floorId = new HashMap<>();
                int stem = 0;
                for(BrandModel brandModel:brandModels){
                    List<String> floors = new ArrayList<>();
                    List<String> fid = new ArrayList<>();
                    Iterator<FloorModel> iterator = floorModel.iterator();
                    while (iterator.hasNext()){
                        FloorModel floor = iterator.next();
                        if(brandModel.getBrandid().equals(floor.getFloorbrand())){
                            fid.add(String.valueOf(floor.getFloorid()));
                            floors.add(floor.getFloorname());
                            iterator.remove();
                        }
                    }
                    if(floors.size()>0){
                        brand.add(brandModel.getBrandname());
                        map.put(brandModel.getBrandname(),floors);
                        floorId.put(stem,fid);
                    }
                    stem++;
                }

                hPref.put(Config.PREF_LOCAL_CACHE,Config.PERF_BRAND_FLOOD+floorEnum.getVar(),HJson.toJson(brand));
                hPref.put(Config.PREF_LOCAL_CACHE,Config.PERF_BRAND_FLOOD_ID+floorEnum.getVar(),HJson.toJson(floorId));
                hPref.put(Config.PREF_LOCAL_CACHE,Config.PERF_FLOOD_L1+floorEnum.getVar(),HJson.toJson(map));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void createRubber(RubberEnum rubberEnum) {
        String brands = hPref.get(Config.PREF_LOCAL_CACHE,Config.PERF_BRANDS,null,String.class);
        if(brands!=null){
            List<BrandModel> brandModels = HJson.fromJsonList(brands,BrandModel.class);
            Object[] arg = {rubberEnum.getVar()};
            try {
                List<RubberModel> rubberModels = dbManager.queryData2T("select * from rubber where rubbertype = ?",arg,RubberModel.class);
                List<String> brand = new ArrayList<>();
                HashMap<String, List<String>> map = new HashMap<String, List<String>>();
                HashMap<Integer,List<String>> rubberId = new HashMap<>();
                int stem = 0;
                for(BrandModel brandModel:brandModels){
                    List<String> rubberl = new ArrayList<>();
                    List<String> rid = new ArrayList<>();

                    Iterator<RubberModel> iterator = rubberModels.iterator();
                    while (iterator.hasNext()){
                        RubberModel rubber = iterator.next();
                        if(brandModel.getBrandid().equals(rubber.getRubberbrand())){
                            rid.add(String.valueOf(rubber.getRubberid()));
                            rubberl.add(rubber.getRubbername());
                            iterator.remove();
                        }
                    }
                    if(rubberl.size()>0){
                        brand.add(brandModel.getBrandname());
                        map.put(brandModel.getBrandname(),rubberl);
                        rubberId.put(stem,rid);
                    }
                    stem++;
                }

                hPref.put(Config.PREF_LOCAL_CACHE,Config.PERF_BRAND_RUBBER_ID+rubberEnum.getVar(),HJson.toJson(rubberId));
                hPref.put(Config.PREF_LOCAL_CACHE,Config.PERF_BRAND_RUBBER+rubberEnum.getVar(),HJson.toJson(brand));
                hPref.put(Config.PREF_LOCAL_CACHE,Config.PERF_RUBBER_L1+rubberEnum.getVar(),HJson.toJson(map));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public void getLaunch(BaseDataSource.BaseCallback<LaunchModel> callback) {
        String launch = hPref.get(Config.PERF_APP,Config.PERF_LANUCH,null,String.class);
        Logger.sl(Log.DEBUG,launch);
        if(launch!=null){
            callback.onSuccess(HJson.fromJson(launch,LaunchModel.class));
        }else{
            callback.onFailure(new DataSourceException("数据为空"));
        }

    }

    @Override
    public void clearLaunch() {
        hPref.remove(Config.PERF_APP, Config.PERF_LANUCH);
    }

    @Override
    public void clearAll() {
        hPref.clear(Config.PERF_APP);
    }



    @Override
    public FloorModel getFlood(int id) {
        FloorModel prefFloorModel = hPref.get(Config.PREF_LOCAL_CACHE,Config.PERF_CACHE_KEY_FLOOD+id,null,FloorModel.class);
        if(prefFloorModel!=null){
            return prefFloorModel;
        }
        Object[] arg = {id};
        try {
            FloorModel floorModel = dbManager.queryData2TOne("select * from floor where floorId = ?",arg,FloorModel.class);
            hPref.put(Config.PREF_LOCAL_CACHE,Config.PERF_CACHE_KEY_FLOOD+id,floorModel);
            return floorModel;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RubberModel getRubber(int id) {
        RubberModel prefRubberModel = hPref.get(Config.PREF_LOCAL_CACHE,Config.PERF_CACHE_KEY_RUBBER+id,null,RubberModel.class);
        if(prefRubberModel!=null){
            return prefRubberModel;
        }
        Object[] arg = {id};
        try {
            RubberModel rubberModel = dbManager.queryData2TOne("select * from rubber where rubberid = ?",arg,RubberModel.class);
            hPref.put(Config.PREF_LOCAL_CACHE,Config.PERF_CACHE_KEY_RUBBER+id,rubberModel);
            return rubberModel;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BrandModel getBrand(int id) {
        BrandModel hrefBrandModel = hPref.get(Config.PREF_LOCAL_CACHE,Config.PERF_CACHE_KEY_BRAND+id,null,BrandModel.class);
        if(hrefBrandModel!=null){
            return hrefBrandModel;
        }
        Object[] arg = {id};
        try {
            BrandModel brandModel =  dbManager.queryData2TOne("select * from brand where brandid = ?",arg,BrandModel.class);
            hPref.put(Config.PREF_LOCAL_CACHE,Config.PERF_CACHE_KEY_BRAND+id,brandModel);
            return brandModel;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateUserInfo(UserInfoModel userInfoModel,final BaseDataSource.BaseCallback<SWrapper> callback) {
        String launch = hPref.get(Config.PERF_APP,Config.PERF_LANUCH,null,String.class);
        if(launch!=null){
            LaunchModel launchModel = HJson.fromJson(launch,LaunchModel.class);
            UserInfoModel uf = launchModel.getUserInfoModel();
            uf.updateUserInfo(userInfoModel);
            hPref.put(Config.PERF_APP,Config.PERF_LANUCH,launchModel);

        }
    }


    @Override
    public HashMap<String, List<String>> createSubFloor(FloorEnum floorEnum) {
        String subFlood = hPref.get(Config.PREF_LOCAL_CACHE,Config.PERF_FLOOD_L1+floorEnum.getVar(),null,String.class);
        if(subFlood!=null){
            return HJson.toMapsList(subFlood,String.class);
        }
        return null;
    }

    @Override
    public List<String> createMainFloor(FloorEnum floorEnum) {
        String brandFlood = hPref.get(Config.PREF_LOCAL_CACHE,Config.PERF_BRAND_FLOOD+floorEnum.getVar(),null,String.class);
        Logger.sl(Log.DEBUG,brandFlood,"????");
        if(brandFlood!=null){
            return HJson.toList(brandFlood,String.class);
        }
        return null;
    }

    @Override
    public HashMap<String, List<String>> createSubRubber(RubberEnum rubberEnum) {
        String subFlood = hPref.get(Config.PREF_LOCAL_CACHE,Config.PERF_RUBBER_L1+rubberEnum.getVar(),null,String.class);
        if(subFlood!=null){
            return HJson.toMapsList(subFlood,String.class);
        }
        return null;
    }

    @Override
    public List<String> createMainRubber(RubberEnum rubberEnum) {
        String brandFlood = hPref.get(Config.PREF_LOCAL_CACHE,Config.PERF_BRAND_RUBBER+rubberEnum.getVar(),null,String.class);
        if(brandFlood!=null){
            return HJson.toList(brandFlood,String.class);
        }
        return null;
    }

    @Override
    public HashMap<String, List<String>> subFloorId(FloorEnum floorEnum) {
        String floor = hPref.get(Config.PREF_LOCAL_CACHE,Config.PERF_BRAND_FLOOD_ID+floorEnum.getVar(),null,String.class);
        if(floor!=null){
            return HJson.toMapsList(floor,String.class);
        }
        return null;
    }

    @Override
    public HashMap<String, List<String>> subRubberId(RubberEnum rubberEnum) {
        String rubber = hPref.get(Config.PREF_LOCAL_CACHE,Config.PERF_BRAND_RUBBER_ID+rubberEnum.getVar(),null,String.class);
        if(rubber!=null){
            return HJson.toMapsList(rubber,String.class);
        }
        return null;
    }

    @Override
    public List<String> getSkill() {
        String skill = hPref.get(Config.PREF_LOCAL_CACHE,Config.PERF_SKILLS,null,String.class);
        if(skill!=null){
            List<UserSkillModel> skillModels = HJson.fromJsonList(skill,UserSkillModel.class);
            List<String> skills = new ArrayList<>();
            for(UserSkillModel skillModel:skillModels){
                skills.add(skillModel.getSkillname());
            }
            return skills;
        }
        return null;
    }

    @Override
    public List<UserCommentModel> getComment() {
        String comment = hPref.get(Config.PREF_LOCAL_CACHE,Config.PERF_COMMENTS,null,String.class);
        if(comment!=null){
            return HJson.fromJsonList(comment,UserCommentModel.class);
        }
        return null;
    }

    @Override
    public void addMatch(MatchModel matchModel,BaseCallback<SWrapper> callback) {
        Object[] o = {matchModel.getMatchid(),matchModel.getMatchname(),matchModel.getMatchtime(),matchModel.getMatchcreatetime(),matchModel.getMatchfromuser(),matchModel.getMatchtouser(),matchModel.getMatchstate(),matchModel.getMatchplace(),matchModel.getMatchfenshu(),matchModel.getMatchkachiid()};
        try {
            dbManager.insertDataBySql("insert into match(matchid,matchname,matchtime,matchcreatetime,matchfromuser,matchtouser,matchstate,matchplace,matchfenshu,matchkachiid) values(?,?,?,?,?,?,?,?,?,?)", o);
            callback.onSuccess(new SWrapper(0,"本地存储OK"));
        } catch (Exception e) {
            e.printStackTrace();
            callback.onFailure(new DataSourceException(e.getMessage()));
        }
    }

    @Override
    public void getMatch(BaseCallback<MatchWrapper> callback) {
        MatchWrapper marchWrapper = new MatchWrapper();
        try {
            List<MatchModel> matchModel = dbManager.queryData2T("select * from match order by _id desc",null,MatchModel.class);
            marchWrapper.setData(matchModel);
            callback.onSuccess(marchWrapper);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e("march is error",e);
            callback.onFailure(new DataSourceException("march is error"));
        }
    }

    @Override
    public void getFollowsToMe(BaseCallback<FollowsWrapper> callback) {
        FollowsWrapper followsWrapper = new FollowsWrapper();
        try {
            List<FollowsModel> follows = dbManager.queryData2T("select * from follows where followsstate = 0",null,FollowsModel.class);
            followsWrapper.setData(follows);
            callback.onSuccess(followsWrapper);
        } catch (Exception e) {
            e.printStackTrace();
            callback.onFailure(new DataSourceException("follows is error"));
        }
    }

    @Override
    public void getFollowsUser(BaseCallback<FollowsWrapper> callback) {
        FollowsWrapper followsWrapper = new FollowsWrapper();
        try {
            List<FollowsModel> follows = dbManager.queryData2T("select * from follows where followsstate = 1",null,FollowsModel.class);
            followsWrapper.setData(follows);
            callback.onSuccess(followsWrapper);
        } catch (Exception e) {
            e.printStackTrace();
            callback.onFailure(new DataSourceException("follows is error"));
        }
    }

    @Override
    public void followsToUser(Integer userId, BaseCallback<SWrapper> callback) {

    }

    @Override
    public void followsOk(Integer followsId, BaseCallback<SWrapper> callback) {
        Object[] ob = {followsId};
        try {
            FollowsModel follow = dbManager.queryData2TOne("select * from follows where followsId = ?",ob,FollowsModel.class);
            Object[] ob2 = {followsId,follow.getFollowsfromuserid(),follow.getFollowstouserid()};
            dbManager.updateDataBySql("update followsstate set -2,followstouserid set ?,followsfromuserid set ? where followsId = ?",ob2);
            callback.onSuccess(new SWrapper(0,"更新成功"));
        } catch (Exception e) {
            e.printStackTrace();
            callback.onFailure(new DataSourceException(e.getMessage()));

        }
    }

    @Override
    public void checkFollows(Integer userId, BaseCallback<SWrapper> callback) {
        Object[] ob = {userId};
        try {
            FollowsModel follow = dbManager.queryData2TOne("select * from follows where followstouserid = ?",ob,FollowsModel.class);
            if(follow!=null){
                if(follow.getFollowsstate()==1){
                    callback.onSuccess(new SWrapper(0,"您的好友"));
                    return;
                }
            }
            callback.onSuccess(new SWrapper(-1,"不是您的好友"));
        } catch (Exception e) {
            e.printStackTrace();
            callback.onFailure(new DataSourceException(e.getMessage()));
        }
    }


    private void deleteLaunchDatabase() {
        try {
            dbManager.deleteDataBySql("delete from floor",null);
            dbManager.deleteDataBySql("delete from brand",null);
            dbManager.deleteDataBySql("delete from rubber",null);
            dbManager.deleteDataBySql("delete from comment",null);
            dbManager.deleteDataBySql("delete from skill",null);
            dbManager.deleteDataBySql("delete from match",null);
            dbManager.deleteDataBySql("delete from follows",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
