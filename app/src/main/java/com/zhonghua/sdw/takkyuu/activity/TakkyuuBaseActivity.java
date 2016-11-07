package com.zhonghua.sdw.takkyuu.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.drcosu.ndileber.mvp.acivity.BaseActivity;
import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BView;
import com.drcosu.ndileber.mvp.view.BaseView;
import com.drcosu.ndileber.tools.DialogLinstener;
import com.drcosu.ndileber.tools.UDialog;
import com.drcosu.ndileber.tools.UUi;
import com.drcosu.ndileber.utils.Check;
import com.orhanobut.logger.Logger;

/**
 * Created by shidawei on 16/9/10.
 */
public abstract class TakkyuuBaseActivity extends BaseActivity implements BView{

    BasePresenter presenter;

    @Override
    public void toast(String msg, int duration) {
        UUi.toast(this,msg,duration);
    }

    @Override
    public void showAlert(Integer type, String message) {
        Logger.d("显示dialog");
        UDialog.alert(type,message).show();

    }

    Dialog dialog;

    @Override
    public void loading() {
        if(dialog==null){
            dialog =UDialog.loading();
        }
        dialog.show();
    }

    @Override
    public void loadDialogDismiss() {
        if(dialog!=null){
            dialog.dismiss();
        }
    }

    @Override
    public void dialogOk(String content, DialogLinstener dialogLinstener) {
        UDialog.dialogOk(content,dialogLinstener).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(presenter!=null){
            presenter.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.onDestroy();
        }
        if(dialog!=null){
            dialog.dismiss();
        }

    }

}
