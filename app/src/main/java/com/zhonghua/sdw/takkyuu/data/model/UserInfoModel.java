package com.zhonghua.sdw.takkyuu.data.model;

import com.drcosu.ndileber.mvp.data.model.SModel;

import java.util.Date;

public class UserInfoModel extends SModel {
    private Integer _id;
    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }
    private Integer userinfoid;

    private Integer uid;

    private String userinfoname;

    private Integer userinfoheight;

    private Integer userinfoweight;

    private Integer userinfoclub;

    private Integer userinfohand;

    private Integer userinfobackhand;

    private Integer userinfofloor;

    private String userinfohandname;

    private String userinfobackhandname;

    private String userinfofloorname;

    private String userinfoflag;

    private Integer userinfogender;

    private Date userinfobirthday;

    private Integer userinfolocal;

    private String userinfolocalname;

    private String userinfointroduce;

    private String userinfoskill;

    private String usernikename;

    private String userphone;

    private String userimage;

    private Integer userimageversion;

    private Integer userinfofenshu;

    public Integer getUserinfofenshu() {
        return userinfofenshu;
    }

    public void setUserinfofenshu(Integer userinfofenshu) {
        this.userinfofenshu = userinfofenshu;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }

    public Integer getUserimageversion() {
        return userimageversion;
    }

    public void setUserimageversion(Integer userimageversion) {
        this.userimageversion = userimageversion;
    }

    public String getUsernikename() {

        return usernikename;
    }

    public void setUsernikename(String usernikename) {
        this.usernikename = usernikename;
    }

    public String getUserinfoskill() {
        return userinfoskill;
    }

    public void setUserinfoskill(String userinfoskill) {
        this.userinfoskill = userinfoskill == null ? null : userinfoskill.trim();
    }


    public Integer getUserinfoid() {
        return userinfoid;
    }

    public void setUserinfoid(Integer userinfoid) {
        this.userinfoid = userinfoid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserinfoname() {
        return userinfoname;
    }

    public void setUserinfoname(String userinfoname) {
        this.userinfoname = userinfoname == null ? null : userinfoname.trim();
    }

    public Integer getUserinfoheight() {
        return userinfoheight;
    }

    public void setUserinfoheight(Integer userinfoheight) {
        this.userinfoheight = userinfoheight;
    }

    public Integer getUserinfoweight() {
        return userinfoweight;
    }

    public void setUserinfoweight(Integer userinfoweight) {
        this.userinfoweight = userinfoweight;
    }

    public Integer getUserinfoclub() {
        return userinfoclub;
    }

    public void setUserinfoclub(Integer userinfoclub) {
        this.userinfoclub = userinfoclub;
    }

    public Integer getUserinfohand() {
        return userinfohand;
    }

    public void setUserinfohand(Integer userinfohand) {
        this.userinfohand = userinfohand;
    }

    public Integer getUserinfobackhand() {
        return userinfobackhand;
    }

    public void setUserinfobackhand(Integer userinfobackhand) {
        this.userinfobackhand = userinfobackhand;
    }

    public Integer getUserinfofloor() {
        return userinfofloor;
    }

    public void setUserinfofloor(Integer userinfofloor) {
        this.userinfofloor = userinfofloor;
    }

    public String getUserinfohandname() {
        return userinfohandname;
    }

    public void setUserinfohandname(String userinfohandname) {
        this.userinfohandname = userinfohandname == null ? null : userinfohandname.trim();
    }

    public String getUserinfobackhandname() {
        return userinfobackhandname;
    }

    public void setUserinfobackhandname(String userinfobackhandname) {
        this.userinfobackhandname = userinfobackhandname == null ? null : userinfobackhandname.trim();
    }

    public String getUserinfofloorname() {
        return userinfofloorname;
    }

    public void setUserinfofloorname(String userinfofloorname) {
        this.userinfofloorname = userinfofloorname == null ? null : userinfofloorname.trim();
    }

    public String getUserinfoflag() {
        return userinfoflag;
    }

    public void setUserinfoflag(String userinfoflag) {
        this.userinfoflag = userinfoflag == null ? null : userinfoflag.trim();
    }

    public Integer getUserinfogender() {
        return userinfogender;
    }

    public void setUserinfogender(Integer userinfogender) {
        this.userinfogender = userinfogender;
    }

    public Date getUserinfobirthday() {
        return userinfobirthday;
    }

    public void setUserinfobirthday(Date userinfobirthday) {
        this.userinfobirthday = userinfobirthday;
    }

    public Integer getUserinfolocal() {
        return userinfolocal;
    }

    public void setUserinfolocal(Integer userinfolocal) {
        this.userinfolocal = userinfolocal;
    }

    public String getUserinfolocalname() {
        return userinfolocalname;
    }

    public void setUserinfolocalname(String userinfolocalname) {
        this.userinfolocalname = userinfolocalname == null ? null : userinfolocalname.trim();
    }

    public String getUserinfointroduce() {
        return userinfointroduce;
    }

    public void setUserinfointroduce(String userinfointroduce) {
        this.userinfointroduce = userinfointroduce == null ? null : userinfointroduce.trim();
    }

    public void updateUserInfo(UserInfoModel userInfoModel){
        setUserinfobackhand(userInfoModel.getUserinfobackhand());
        setUserinfobirthday(userInfoModel.getUserinfobirthday());
        setUserinfofloor(userInfoModel.getUserinfofloor());
        setUserinfogender(userInfoModel.getUserinfogender());
        setUserinfoheight(userInfoModel.getUserinfoheight());
        setUserinfoweight(userInfoModel.getUserinfoweight());
        setUserinfoflag(userInfoModel.getUserinfoflag());
        setUserinfointroduce(userInfoModel.getUserinfointroduce());
        setUserinfoskill(userInfoModel.getUserinfoskill());
        setUserimage(userInfoModel.getUserimage());
        setUserimageversion(userInfoModel.getUserimageversion());
    }

}