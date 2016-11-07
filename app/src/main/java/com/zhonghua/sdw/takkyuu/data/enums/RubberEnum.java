package com.zhonghua.sdw.takkyuu.data.enums;

import java.util.EnumSet;

/**
 * 1:正胶、2:反胶、3:长胶、4:生胶
 * Created by shidawei on 2016/9/25.
 */
public enum RubberEnum {

    ZhengRubber(1,"正胶"),FanRubber(2,"反胶"),ChangRubber(3,"长胶"),ShengRubber(4,"生胶");

    int var;
    String name;

    private RubberEnum(int var,String name){
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
        EnumSet<RubberEnum> rubberEna = EnumSet.allOf(RubberEnum.class);
        for (RubberEnum rubberEnum : rubberEna) {
            if(rubberEnum.var == var){
                return rubberEnum.name;
            }
        }
        return "未知";
    }

    public static RubberEnum getTheRubber(Integer var){
        if(var == null){
            return null;
        }
        EnumSet<RubberEnum> rubberEna = EnumSet.allOf(RubberEnum.class);
        for (RubberEnum rubberEnum : rubberEna) {
            if(rubberEnum.var == var){
                return rubberEnum;
            }
        }
        return null;
    }


}
