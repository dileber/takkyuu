package com.zhonghua.sdw.takkyuu.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.drcosu.ndileber.mvp.acivity.BaseActivity;
import com.drcosu.ndileber.tools.DialogLinstener;
import com.drcosu.ndileber.tools.UText;
import com.drcosu.ndileber.tools.UUi;
import com.drcosu.ndileber.utils.UToolBar;
import com.zhonghua.sdw.takkyuu.R;
import com.zhonghua.sdw.takkyuu.contract.LoginContract;
import com.zhonghua.sdw.takkyuu.data.model.UserModel;
import com.zhonghua.sdw.takkyuu.data.source.UserRepository;
import com.zhonghua.sdw.takkyuu.presenter.LoginPresenter;

public class LoginActivity extends TakkyuuBaseActivity implements LoginContract.View,View.OnClickListener{

    public static void start(Context context,Intent intent){
        if(intent == null){
            intent = new Intent();
        }
        intent.setClass(context,LoginActivity.class);
        context.startActivity(intent);
    }

    EditText login_name,login_pass;
    LoginContract.Presenter mPresenter;

    @Override
    protected void startView(Bundle savedInstanceState) {

    }

    @Override
    protected int layoutViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        presenter = new LoginPresenter(this, UserRepository.getInstance());

        UToolBar uToolBar = new UToolBar();
        uToolBar.setTitleId(R.string.takkyuu_login);
        uToolBar.setNeedNavigate(false);
        setToolBar(R.id.toolbar,uToolBar);
        login_name = getView(R.id.login_name);
        login_pass = getView(R.id.login_pass);
        UUi.setOnClickListener(this,getView(R.id.login_login),getView(R.id.login_register));

    }



    @Override
    public void toHome() {
        HomeActivity.start(this);
        finish();
    }

    @Override
    public UserModel getUser(){
        UserModel userModel = new UserModel();
        userModel.setUsername(login_name.getText().toString().trim());
        userModel.setUserpass(login_pass.getText().toString().trim());
        return userModel;
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter)  {
        mPresenter = presenter;
    }


    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getUser();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_login:
                mPresenter.checkUser();
                break;
            case R.id.login_register:
                RegisterActivity.start(LoginActivity.this);
                break;
        }

    }
}
