package com.zhonghua.sdw.takkyuu.app;

import android.app.Dialog;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.widget.Toast;

import com.drcosu.ndileber.app.ActivityManager;
import com.drcosu.ndileber.app.SApplication;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.tools.DialogLinstener;
import com.drcosu.ndileber.tools.UDialog;
import com.drcosu.ndileber.tools.UUi;
import com.drcosu.ndileber.tools.annotation.SFontdType;
import com.drcosu.ndileber.tools.net.RetCallback;
import com.mianbaopai.sdw.imsystem.DemoHelper;
import com.zhonghua.sdw.takkyuu.activity.LoginActivity;
import com.zhonghua.sdw.takkyuu.data.model.UserModel;
import com.zhonghua.sdw.takkyuu.data.model.UserWrapper;
import com.zhonghua.sdw.takkyuu.data.source.UserDataSource;
import com.zhonghua.sdw.takkyuu.data.source.UserRepository;
import com.zhonghua.sdw.takkyuu.data.source.remote.NetWorkEnum;
import com.zhonghua.sdw.takkyuu.utils.UiUtils;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by shidawei on 16/9/7.
 */
@SFontdType(value = "icomoon.ttf")
public class TakkyuuApplication extends SApplication {
    private static int forbidden = 0;

    @Override
    public void start() {
        crash = false;
    }

    @Override
    protected void init() {
        DemoHelper.getInstance().init(this);
    }

    private static final int maxForbidden = 10;

    @Override
    public void appForbidden(final Call call, Response response, final RetCallback retCallback) {
        forbidden ++;
        if(forbidden>10){
            UiUtils.loginAgain(this,"账户需要重新登录");
            forbidden = 0;
            return;
        }
        final UserDataSource userDataSource = UserRepository.getInstance();

        userDataSource.getUser(new BaseDataSource.BaseCallback<UserModel>() {
            @Override
            public void onSuccess(UserModel userModel) {
                userDataSource.checkUser(userModel, new BaseDataSource.BaseCallback<UserWrapper>() {
                    @Override
                    public void onSuccess(UserWrapper userWrapper) {


                        if(userWrapper.getState()== NetWorkEnum.Success.getVar()){
                            UserModel userModel1 = userWrapper.getData();
                            if(userModel1==null){
                                UUi.toast(ActivityManager.getCurrentActivity(),"用户异常,请检查", Toast.LENGTH_SHORT);
                            }else{
                                /**
                                 * 再次请求
                                 */
                                call.clone().enqueue(retCallback);
                            }

                        }else if(userWrapper.getState()==NetWorkEnum.Error.getVar()){
                            UiUtils.loginAgain(TakkyuuApplication.this,userWrapper.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(DataSourceException e) {
                        UiUtils.loginAgain(TakkyuuApplication.this,"账户需要重新登录");
                    }
                });
            }

            @Override
            public void onFailure(DataSourceException e) {

            }
        });
    }

    /**
     * 分割 Dex 支持
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
