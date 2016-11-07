package com.zhonghua.sdw.takkyuu.data.enums;

import com.zhonghua.sdw.takkyuu.data.model.FloorModel;

import java.util.EnumSet;

/**
 * 1:直板 2:横版 3:日式直板
 * Created by shidawei on 2016/9/24.
 */
public enum FloorEnum {

    Straight(1,"直板"),diaphragm(2,"横版"),JapanType(3,"日式直板");

    int var;
    String name;

    private FloorEnum(int var,String name){
        this.var = var;
        this.name = name;
    }

    public int getVar() {
        return var;
    }

    public String getName() {
        return name;
    }

    public static String getTheName(Integer var){
        if(var == null){
            return null;
        }
        EnumSet<FloorEnum> floorEna = EnumSet.allOf(FloorEnum.class);
        for (FloorEnum floorEnum : floorEna) {
            if(floorEnum.var == var){
                return floorEnum.name;
            }
        }
        return "未知";
    }


    public static FloorEnum getTheFloor(Integer var){
        if(var == null){
            return null;
        }
        EnumSet<FloorEnum> floorEna = EnumSet.allOf(FloorEnum.class);
        for (FloorEnum floorEnum : floorEna) {
            if(floorEnum.var == var){
                return floorEnum;
            }
        }
        return null;
    }


    public static int getSize(){
        EnumSet<FloorEnum> floorEna = EnumSet.allOf(FloorEnum.class);
        return floorEna.size();
    }
}
