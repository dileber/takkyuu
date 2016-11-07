package com.zhonghua.sdw.takkyuu.presenter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.tools.HSafe;
import com.drcosu.ndileber.tools.HString;
import com.drcosu.ndileber.tools.UDialog;
import com.drcosu.ndileber.tools.UText;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.mianbaopai.sdw.imsystem.DemoHelper;
import com.mianbaopai.sdw.imsystem.db.DemoDBManager;
import com.mianbaopai.sdw.imsystem.ui.MainActivity;
import com.orhanobut.logger.Logger;
import com.zhonghua.sdw.takkyuu.R;
import com.zhonghua.sdw.takkyuu.activity.HomeActivity;
import com.zhonghua.sdw.takkyuu.contract.LoginContract;
import com.zhonghua.sdw.takkyuu.data.model.UserModel;
import com.zhonghua.sdw.takkyuu.data.model.UserWrapper;
import com.zhonghua.sdw.takkyuu.data.source.UserDataSource;
import com.zhonghua.sdw.takkyuu.data.source.remote.NetWorkEnum;

/**
 * Created by shidawei on 16/8/6.
 */
public class LoginPresenter implements LoginContract.Presenter{

    LoginContract.View mView;
    UserDataSource mUserDataSource;

    public LoginPresenter(@NonNull LoginContract.View view, @NonNull UserDataSource userDataSource){
        mView = view;
        mUserDataSource = userDataSource;
        mView.setPresenter(this);
    }

    @Override
    public void checkUser() {
        final UserModel userModel = mView.getUser();
        if(userModel==null){
            mView.toast("用户为空", Toast.LENGTH_SHORT);
            return;
        }
        final String userName = userModel.getUsername();
        final String pass = userModel.getUserpass();
        if(!UText.checkEditText(userName,6)||!UText.checkEditText(pass,6)){
            mView.toast("账号或密码不能小于6位数", Toast.LENGTH_SHORT);
            return;
        }
        checkUser(userModel);

    }

    private void checkUser(final UserModel userModel){
        mView.loading();
        mUserDataSource.checkUser(userModel, new BaseDataSource.BaseCallback<UserWrapper>() {
            @Override
            public void onSuccess(UserWrapper userWrapper) {
                if(userWrapper.getState()== NetWorkEnum.Success.getVar()){
                    UserModel userModel1 = userWrapper.getData();
                    if(userModel1==null){
                        mView.showAlert(UDialog.DIALOG_ERROR,"用户异常");
                        mUserDataSource.clearUser();
                    }else{
                        userModel1.setUserpass(userModel.getUserpass());
                        mUserDataSource.saveUser(userModel1);
                        login(String.valueOf(userModel.getUserid()),HSafe.getHashValue(userModel.getUserpass()+HSafe.getHashValue(userModel.getUsername(), HSafe.HashMethod.sha1), HSafe.HashMethod.sha1));
                        toHome(userModel1);
                    }

                }else if(userWrapper.getState()==NetWorkEnum.Error.getVar()){
                    mView.showAlert(UDialog.DIALOG_ERROR,userWrapper.getMsg());
                    mUserDataSource.clearUser();
                }

                mView.loadDialogDismiss();
            }

            @Override
            public void onFailure(DataSourceException e) {
                mView.showAlert(UDialog.DIALOG_ERROR,e.getMessage());
                mView.loadDialogDismiss();
                mUserDataSource.clearUser();

            }
        });
    }
    private void login(String name,String password){
//        EMClient.getInstance().login(name, password, new EMCallBack() {
//
//            @Override
//            public void onSuccess() {
//                Logger.d( "login: onSuccess");
//                EMClient.getInstance().groupManager().loadAllGroups();
//                EMClient.getInstance().chatManager().loadAllConversations();
//            }
//
//            @Override
//            public void onProgress(int progress, String status) {
//                Logger.d(  "login: onProgress");
//            }
//
//            @Override
//            public void onError(final int code, final String message) {
//
//            }
//        });

        // After logout，the DemoDB may still be accessed due to async callback, so the DemoDB will be re-opened again.
        // close it before login to make sure DemoDB not overlap
        DemoDBManager.getInstance().closeDB();

        // reset current user name before login
        DemoHelper.getInstance().setCurrentUserName(name);

        final long start = System.currentTimeMillis();
        // call login method
        Logger.d( "EMClient.getInstance().login");
        EMClient.getInstance().login(name, password, new EMCallBack() {

            @Override
            public void onSuccess() {
                Logger.d( "login: onSuccess");


                // ** manually load all local groups and conversation
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();

                // update current user's display name for APNs
                // get user's info (this should be get from App's server or 3rd party service)
                DemoHelper.getInstance().getUserProfileManager().asyncGetCurrentUserInfo();

            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(final int code, final String message) {

            }
        });

    }
    @Override
    public void getUser() {
        mUserDataSource.getUser(new BaseDataSource.BaseCallback<UserModel>() {
            @Override
            public void onSuccess(UserModel userModel) {
                toHome(userModel);
            }

            @Override
            public void onFailure(DataSourceException e) {

            }
        });
    }

    private void toHome(UserModel userModel){
        mView.toHome();
        mView.toast("欢迎" + userModel.getUsername(), Toast.LENGTH_SHORT);
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }
}
