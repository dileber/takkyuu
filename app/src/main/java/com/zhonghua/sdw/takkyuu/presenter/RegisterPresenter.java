package com.zhonghua.sdw.takkyuu.presenter;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.tools.UDialog;
import com.drcosu.ndileber.tools.UText;
import com.drcosu.ndileber.utils.Check;
import com.zhonghua.sdw.takkyuu.contract.RegisterContract;
import com.zhonghua.sdw.takkyuu.data.model.UserModel;
import com.zhonghua.sdw.takkyuu.data.model.UserWrapper;
import com.zhonghua.sdw.takkyuu.data.source.UserDataSource;
import com.zhonghua.sdw.takkyuu.data.source.remote.NetWorkEnum;

/**
 * Created by shidawei on 16/9/20.
 */
public class RegisterPresenter implements RegisterContract.Presenter {

    RegisterContract.View mView;
    UserDataSource mUserDataSource;

    public RegisterPresenter(@NonNull RegisterContract.View view,@NonNull UserDataSource userDataSource){
        this.mView = Check.checkNotNull(view);
        mUserDataSource = userDataSource;
        mView.setPresenter(this);
    }

    @Override
    public void register() {
        final UserModel userModel = mView.getUser();
        if(userModel==null){
            mView.toast("用户为空", Toast.LENGTH_SHORT);
            return;
        }
        final String userName = userModel.getUsername();
        final String pass = userModel.getUserpass();
        final String nickName = userModel.getUsernikename();
        if(!UText.checkEditText(userName,6)||!UText.checkEditText(pass,6)){
            mView.toast("账号或密码不能小于6位数", Toast.LENGTH_SHORT);
            return;
        }
        if(!UText.checkEditText(nickName,1)){
            mView.toast("昵称不能为空", Toast.LENGTH_SHORT);
            return;
        }
        mUserDataSource.register(userModel, new BaseDataSource.BaseCallback<UserWrapper>() {
            @Override
            public void onSuccess(UserWrapper userWrapper) {
                if(userWrapper.getState()== NetWorkEnum.Success.getVar()){
                    UserModel userModel1 = userWrapper.getData();
                    if(userModel1==null){
                        mView.showAlert(UDialog.DIALOG_ERROR,"用户异常");
                    }else{
                        userModel1.setUserpass(userModel.getUserpass());
                        mUserDataSource.saveUser(userModel1);
                        mView.toFinish();
                    }
                }else if(userWrapper.getState()==-1){
                    mView.showAlert(UDialog.DIALOG_ERROR,userWrapper.getMsg());
                }

                mView.loadDialogDismiss();
            }

            @Override
            public void onFailure(DataSourceException e) {
                mView.showAlert(UDialog.DIALOG_ERROR,e.getMessage());
                mView.loadDialogDismiss();
            }
        });
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }


}
