package com.zhonghua.sdw.takkyuu.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.drcosu.ndileber.app.ActivityManager;
import com.drcosu.ndileber.tools.UDialog;
import com.zhonghua.sdw.takkyuu.activity.LoginActivity;
import com.zhonghua.sdw.takkyuu.app.Config;
import com.zhonghua.sdw.takkyuu.data.source.UserRepository;

/**
 * Created by shidawei on 16/9/22.
 */
public class UiUtils {

    /**
     * 在没有权限的情况下,跳转登录页面
     * @param context
     * @param msg
     */
    public static void loginAgain(Context context,String msg){
        UDialog.alert(UDialog.DIALOG_ERROR,msg);
        UserRepository.getInstance().clearUser();
//
//        ActivityManager.getInstance().finishOtherActivity(new ActivityManager.NewActivityCallBack() {
//            @Override
//            public void createActivity(Activity activity) {
//                LoginActivity.start(activity,null);
//            }
//        });
    }

}
