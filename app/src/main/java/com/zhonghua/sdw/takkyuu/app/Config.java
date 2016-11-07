package com.zhonghua.sdw.takkyuu.app;

import com.drcosu.ndileber.mvp.data.source.local.DBManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shidawei on 16/9/7.
 */
public class Config {

//    //bug model
//    public static final boolean DEBUG = true;
//
//    public static final String BUG_NAME = "takkyuu";

    //
    public static final String HTTP_URL = "http://192.168.1.100:8080/";

    public static final String PERF_USER = "takkyuu_user";

    public static final String PERF_LANUCH = "takkyuu_launch";


    public static final String PERF_APP = "takkyuu_app";

    public static final int DB_VERSION = 10;

    public static final String PREF_LOCAL_CACHE = "local_cache";

    public static final String PERF_CACHE_KEY_BRAND = "brand_";

    public static final String PERF_CACHE_KEY_FLOOD = "flood_";

    public static final String PERF_CACHE_KEY_RUBBER = "rubber_";

    //public static final String PERF_BRAND = "takkyuu_brand";


    //public static final String PERF_FLOOD_TYPE = "takkyuu_flood_type_";

    public static final String PERF_BRANDS = "takkyuu_brands";

    public static final String PERF_FLOODS = "takkyuu_floods";

    public static final String PERF_SKILLS = "takkyuu_skills";

    //public static final String PERF_MATCH = "takkyuu_match";

    public static final String PERF_COMMENTS = "takkyuu_comments";

    public static final String PERF_RUBBERS = "takkyuu_rubbers";

    public static final String PERF_BRAND_FLOOD = "takkyuu_brand_flood";

    public static final String PERF_BRAND_FLOOD_ID = "takkyuu_brand_flood_id";

    public static final String PERF_FLOOD_L1 = "takkyuu_flood_l1";

    public static final String PERF_BRAND_RUBBER = "takkyuu_brand_rubber";

    public static final String PERF_BRAND_RUBBER_ID = "takkyuu_brand_rubber_id";

    public static final String PERF_RUBBER_L1 = "takkyuu_rubber_l1";



    public static DBManager dbManager;
    static {
        List<String> list = new ArrayList<>();
        String brand = "CREATE TABLE brand (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,brandid INTEGER,brandname TEXT,brandstate INTEGER)";
        String rubber = "CREATE TABLE rubber (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,rubberid INTEGER,rubbername TEXT,rubbertype INTEGER,rubberbrand INTEGER,rubberstate INTEGER)";
        String floor = "CREATE TABLE floor (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,floorid INTEGER,floorname TEXT,floorbrand INTEGER,floortype INTEGER,floorstate INTEGER)";
        String comment = "CREATE TABLE comment (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,commentid INTEGER,commentcontext TEXT,commentfromuserid INTEGER,commenttouserid INTEGER,commentstate INTEGER)";
        String skill = "CREATE TABLE skill (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,skillid INTEGER,skillname TEXT,skillstate INTEGER)";
        String match = "CREATE TABLE match (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,matchid INTEGER,matchname TEXT,matchtime INTEGER,matchcreatetime INTEGER,matchfromuser INTEGER,matchtouser INTEGER,matchstate INTEGER,matchplace INTEGER,matchfenshu INTEGER,matchkachiid INTEGER)";
        String usercomment = "CREATE TABLE usercomment (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,commentid INTEGER,commentcontext TEXT,commentfromuserid INTEGER,commenttouserid INTEGER,commentstate INTEGER)";
        String userInfo = "CREATE TABLE userinfo (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,userinfoid INTEGER, uid INTEGER, userinfoname TEXT,userinfoheight INTEGER,userinfoweight INTEGER,userinfoclub INTEGER, userinfohand INTEGER, userinfobackhand INTEGER,userinfofloor INTEGER, userinfohandname TEXT, userinfobackhandname TEXT, userinfofloorname TEXT,userinfoflag TEXT,userinfogender INTEGER,userinfobirthday INTEGER,userinfolocal INTEGER,userinfolocalname TEXT,userinfointroduce TEXT,userinfoskill TEXT,usernikename TEXT,userphone TEXT,userimage TEXT,userimageversion INTEGER,userinfofenshu INTEGER)";
        String follows = "CREATE TABLE follows (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,followsid INTEGER,followstouserid INTEGER,followsfromuserid INTEGER,followsstate INTEGER)";
        list.add(usercomment);
        list.add(userInfo);
        list.add(brand);
        list.add(rubber);
        list.add(floor);
        list.add(comment);
        list.add(skill);
        list.add(match);
        list.add(follows);
        dbManager = DBManager.getInstance().getDB("takkyuu",Config.DB_VERSION,list);
    }


    public static final String PRE = "IM";
    public static final String PRE_USER = "pre_user";

}
