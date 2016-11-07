package com.zhonghua.sdw.takkyuu.data.model;

import com.drcosu.ndileber.mvp.data.model.SModel;

import java.util.Date;

/**
 * Created by shidawei on 16/9/19.
 */
public class UserModel extends SModel {
    private Integer userid;

    private String username;

    private String userpass;

    private String usersalt;

    private Date userbrithday;

    private String usernikename;

    private Integer usergender;

    private String userphone;

    private String userimage;

    private Integer userlocal;

    private String userfeel;

    private Integer userstate;

    private Integer userimageversion;

    private Date usercreatetime;

    private UserInfoModel userInfoModel;

    public UserInfoModel getUserInfoModel() {
        return userInfoModel;
    }

    public void setUserInfoModel(UserInfoModel userInfoModel) {
        this.userInfoModel = userInfoModel;
    }


    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass == null ? null : userpass.trim();
    }

    public String getUsersalt() {
        return usersalt;
    }

    public void setUsersalt(String usersalt) {
        this.usersalt = usersalt == null ? null : usersalt.trim();
    }

    public Date getUserbrithday() {
        return userbrithday;
    }

    public void setUserbrithday(Date userbrithday) {
        this.userbrithday = userbrithday;
    }

    public String getUsernikename() {
        return usernikename;
    }

    public void setUsernikename(String usernikename) {
        this.usernikename = usernikename == null ? null : usernikename.trim();
    }

    public Integer getUsergender() {
        return usergender;
    }

    public void setUsergender(Integer usergender) {
        this.usergender = usergender;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone == null ? null : userphone.trim();
    }

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage == null ? null : userimage.trim();
    }

    public Integer getUserlocal() {
        return userlocal;
    }

    public void setUserlocal(Integer userlocal) {
        this.userlocal = userlocal;
    }

    public String getUserfeel() {
        return userfeel;
    }

    public void setUserfeel(String userfeel) {
        this.userfeel = userfeel == null ? null : userfeel.trim();
    }

    public Integer getUserstate() {
        return userstate;
    }

    public void setUserstate(Integer userstate) {
        this.userstate = userstate;
    }

    public Integer getUserimageversion() {
        return userimageversion;
    }

    public void setUserimageversion(Integer userimageversion) {
        this.userimageversion = userimageversion;
    }

    public Date getUsercreatetime() {
        return usercreatetime;
    }

    public void setUsercreatetime(Date usercreatetime) {
        this.usercreatetime = usercreatetime;
    }
}
