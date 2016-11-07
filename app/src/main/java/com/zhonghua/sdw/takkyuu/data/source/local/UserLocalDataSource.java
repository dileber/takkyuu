package com.zhonghua.sdw.takkyuu.data.source.local;

import android.support.annotation.NonNull;
import android.util.Log;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.mvp.data.source.local.BaseLocalDataSource;
import com.drcosu.ndileber.mvp.data.source.local.DBManager;
import com.drcosu.ndileber.tools.HJson;
import com.orhanobut.logger.Logger;
import com.zhonghua.sdw.takkyuu.app.Config;
import com.zhonghua.sdw.takkyuu.app.TakkyuuApplication;
import com.zhonghua.sdw.takkyuu.data.model.UserCommentModel;
import com.zhonghua.sdw.takkyuu.data.model.UserCommentWrapper;
import com.zhonghua.sdw.takkyuu.data.model.UserInfoModel;
import com.zhonghua.sdw.takkyuu.data.model.UserInfosWrapper;
import com.zhonghua.sdw.takkyuu.data.model.UserModel;
import com.zhonghua.sdw.takkyuu.data.model.UserWrapper;
import com.zhonghua.sdw.takkyuu.data.source.UserDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shidawei on 16/8/6.
 */
public class UserLocalDataSource extends BaseLocalDataSource implements UserDataSource {

//    String comment = "CREATE TABLE usercomment (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,commentid INTEGER,commentcontext TEXT,commentfromuserid INTEGER,commenttouserid INTEGER,commentstate INTEGER)";
//    String userInfo = "CREATE TABLE userinfo (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,userinfoid INTEGER, uid INTEGER, userinfoname TEXT,userinfoheight INTEGER,userinfoweight INTEGER,userinfoclub INTEGER, userinfohand INTEGER, userinfobackhand INTEGER,userinfofloor INTEGER, userinfohandname TEXT, userinfobackhandname TEXT, userinfofloorname TEXT,userinfoflag TEXT,userinfogender INTEGER,userinfobirthday INTEGER,userinfolocal INTEGER,userinfolocalname TEXT,userinfointroduce TEXT,userinfoskill TEXT,usernikename TEXT,userphone TEXT,userimage TEXT,userimageversion INTEGER)";

    private static volatile UserLocalDataSource instance;

    private UserLocalDataSource(){
//        List<String> list = new ArrayList<>();
//        list.add(comment);
//        list.add(userInfo);
//        dbManager = dbManager.getDB("user",Config.DB_VERSION,list);

        dbManager = Config.dbManager;

    }

    public static UserLocalDataSource getInstance(){
        if (instance==null){
            synchronized (UserLocalDataSource.class){
                if(instance==null){
                    instance = new UserLocalDataSource();
                }
            }
        }
        return instance;
    }

    @Override
    public void checkUser(@NonNull UserModel userModel, BaseDataSource.BaseCallback<UserWrapper> callback) {

    }

    @Override
    public void saveUser(@NonNull UserModel userModel) {
        hPref.put(Config.PERF_APP, Config.PERF_USER, userModel);
    }

    @Override
    public void getUser(BaseDataSource.BaseCallback<UserModel> callback) {
        String user = hPref.get(Config.PERF_APP,Config.PERF_USER,null,String.class);
        if(user!=null){
            callback.onSuccess(HJson.fromJson(user,UserModel.class));
        }else{
            callback.onFailure(new DataSourceException("您尚未登录"));
        }
    }

    @Override
    public void clearUser() {
        hPref.remove(Config.PERF_APP, Config.PERF_USER);
    }

    @Override
    public void register(@NonNull UserModel userModel, BaseCallback<UserWrapper> callback) {

    }

    @Override
    public void repass(String oldpass, String newpass, BaseCallback<SWrapper> callback) {

    }

    @Override
    public void getUserComment(Integer userId, BaseCallback<UserCommentWrapper> callback) {

    }

    @Override
    public void addUserComment(UserCommentModel userCommentModel, BaseCallback<SWrapper> callback) {

    }

    @Override
    public void getUserInfo(Integer userId, BaseCallback<UserInfoModel> callback) {
        Object[] arg = {userId};
        try {
            callback.onSuccess(dbManager.queryData2TOne("select * from userinfo where uid = ?",arg,UserInfoModel.class));
        } catch (Exception e) {
            e.printStackTrace();
            callback.onFailure(new DataSourceException("数据出现异常"));
        }
    }

    @Override
    public void getUserInfos(List<Integer> userIds, BaseCallback<UserInfosWrapper> callback) {

    }

    @Override
    public Integer isUserInfoInData(Integer userId) {
        Object[] arg = {userId};
        try {
            long count = dbManager.queryDataCount("select count(*) from userinfo where uid = ?",arg);
            return (int)count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addUserInfos(List<UserInfoModel> userInfoModels, BaseCallback<SWrapper> callback) {
        if(userInfoModels!=null&&userInfoModels.size()>0){
            List<Object[]> objects = new ArrayList<>();
            for(UserInfoModel userInfoModel:userInfoModels){
                if(isUserInfoInData(userInfoModel.getUid())>0){
                    continue;
                }
                Object[] ob = {userInfoModel.getUserinfoid(),userInfoModel.getUid(),userInfoModel.getUserinfoname(),
                        userInfoModel.getUserinfoheight(),userInfoModel.getUserinfoweight(),userInfoModel.getUserinfoclub(),
                        userInfoModel.getUserinfohand() , userInfoModel.getUserinfobackhand(),userInfoModel.getUserinfofloor(),
                        userInfoModel.getUserinfohandname() , userInfoModel.getUserinfobackhandname(), userInfoModel.getUserinfofloorname(),
                        userInfoModel.getUserinfoflag(),userInfoModel.getUserinfogender(),userInfoModel.getUserinfobirthday(),userInfoModel.getUserinfolocal(),
                        userInfoModel.getUserinfolocalname(),userInfoModel.getUserinfointroduce(),userInfoModel.getUserinfoskill(),userInfoModel.getUsernikename(),
                        userInfoModel.getUserphone(),userInfoModel.getUserimage(),userInfoModel.getUserimageversion(),userInfoModel.getUserinfofenshu()};

                objects.add(ob);
            }
            int m = dbManager.dataBatch("insert into userinfo(userinfoid, uid, userinfoname,userinfoheight," +
                    "userinfoweight,userinfoclub, userinfohand, userinfobackhand,userinfofloor, userinfohandname, " +
                    "userinfobackhandname, userinfofloorname,userinfoflag,userinfogender,userinfobirthday," +
                    "userinfolocal,userinfolocalname,userinfointroduce,userinfoskill,usernikename,userphone," +
                    "userimage,userimageversion,userinfofenshu) values(?,?,?,?,?" +
                    ",?,?,?,?,?" +
                    ",?,?,?,?,?" +
                    ",?,?,?,?,?" +
                    ",?,?,?,?)",objects, DBManager.Type.INSERT);
            Logger.sl(Log.DEBUG,"comment 数据库存了"+m+"条数据");
            callback.onSuccess(new SWrapper(0,"数据库存了"+m+"条数据"));

        }
        callback.onFailure(new DataSourceException("无数据"));
    }

    @Override
    public void addUserInfo(UserInfoModel userInfoModel, BaseCallback<SWrapper> callback) {
        List<UserInfoModel> userInfoModels = new ArrayList<>();
        userInfoModels.add(userInfoModel);
        addUserInfos(userInfoModels,callback);
    }

    @Override
    public void updateUserInfo(UserInfoModel userInfoModel, BaseCallback<SWrapper> callback) {
        Object[] ob = {userInfoModel.getUid(),userInfoModel.getUserinfoname(),
                userInfoModel.getUserinfoheight(),userInfoModel.getUserinfoweight(),userInfoModel.getUserinfoclub(),
                userInfoModel.getUserinfohand() , userInfoModel.getUserinfobackhand(),userInfoModel.getUserinfofloor(),
                userInfoModel.getUserinfohandname() , userInfoModel.getUserinfobackhandname(), userInfoModel.getUserinfofloorname(),
                userInfoModel.getUserinfoflag(),userInfoModel.getUserinfogender(),userInfoModel.getUserinfobirthday(),userInfoModel.getUserinfolocal(),
                userInfoModel.getUserinfolocalname(),userInfoModel.getUserinfointroduce(),userInfoModel.getUserinfoskill(),userInfoModel.getUsernikename(),
                userInfoModel.getUserphone(),userInfoModel.getUserimage(),userInfoModel.getUserimageversion(),userInfoModel.getUserinfofenshu(),userInfoModel.getUserinfoid()};

        try {
            dbManager.updateDataBySql("update userinfo set uid = ?, userinfoname = ?,userinfoheight = ?," +
                    "userinfoweight = ?,userinfoclub = ?, userinfohand = ?, userinfobackhand = ?,userinfofloor = ?, userinfohandname = ?, " +
                    "userinfobackhandname = ?, userinfofloorname = ?,userinfoflag = ?,userinfogender = ?,userinfobirthday = ?," +
                    "userinfolocal = ?,userinfolocalname = ?,userinfointroduce = ?,userinfoskill = ?,usernikename = ?,userphone = ?," +
                    "userimage = ?,userimageversion = ?,userinfofenshu = ?  where userinfoid = ?",ob);
            callback.onSuccess(new SWrapper(0,"更新成功"));
        } catch (Exception e) {
            e.printStackTrace();
            callback.onFailure(new DataSourceException(e.getMessage()));

        }
    }

}
