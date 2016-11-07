package com.zhonghua.sdw.takkyuu.data.enums;

import java.util.EnumSet;

/**
 * Created by shidawei on 2016/9/25.
 */
public enum  GredenEnum {

    Women(0,"女"),Men(1,"男");

    int var;
    String name;

    private GredenEnum(int var,String name){
        this.var = var;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVar() {
        return var;
    }

    public static String getTheName(Integer var){
        if(var == null){
            return null;
        }
        EnumSet<GredenEnum> gredenEna = EnumSet.allOf(GredenEnum.class);
        for (GredenEnum gredenEnum : gredenEna) {
            if(gredenEnum.var == var){
                return gredenEnum.name;
            }
        }
        return "未知";
    }

    public static GredenEnum getTheGreden(Integer var){
        if(var == null){
            return null;
        }
        EnumSet<GredenEnum> gredenEna = EnumSet.allOf(GredenEnum.class);
        for (GredenEnum gredenEnum : gredenEna) {
            if(gredenEnum.var == var){
                return gredenEnum;
            }
        }
        return null;
    }


}
