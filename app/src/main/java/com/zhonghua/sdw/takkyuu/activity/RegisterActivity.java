package com.zhonghua.sdw.takkyuu.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.drcosu.ndileber.mvp.acivity.BaseActivity;
import com.drcosu.ndileber.tools.UUi;
import com.drcosu.ndileber.utils.UToolBar;
import com.zhonghua.sdw.takkyuu.R;
import com.zhonghua.sdw.takkyuu.contract.LoginContract;
import com.zhonghua.sdw.takkyuu.contract.RegisterContract;
import com.zhonghua.sdw.takkyuu.data.model.UserModel;
import com.zhonghua.sdw.takkyuu.data.source.UserRepository;
import com.zhonghua.sdw.takkyuu.data.source.remote.UserRemoteDataSource;
import com.zhonghua.sdw.takkyuu.presenter.RegisterPresenter;

public class RegisterActivity extends TakkyuuBaseActivity implements RegisterContract.View,View.OnClickListener {

    RegisterContract.Presenter mPresenter;

    public static void start(Context context){
        Intent intent = new Intent();
        intent.setClass(context,RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void startView(Bundle savedInstanceState) {

    }

    @Override
    protected int layoutViewId() {
        return R.layout.activity_register;
    }

    EditText register_email,register_pass,register_nickname;

    @Override
    protected void initView(Bundle savedInstanceState) {
        presenter = new RegisterPresenter(this, UserRepository.getInstance());
        UToolBar uToolBar = new UToolBar();
        uToolBar.setTitleId(R.string.takkyuu_register);
        uToolBar.setNeedNavigate(false);
        setToolBar(R.id.toolbar,uToolBar);
        UUi.setOnClickListener(this,getView(R.id.register_button));
        register_email = getView(R.id.register_email);
        register_pass = getView(R.id.register_pass);
        register_nickname = getView(R.id.register_nickname);

    }

    @Override
    public void toFinish() {
        finish();
    }

    @Override
    public UserModel getUser() {
        UserModel userModel = new UserModel();
        userModel.setUsername(register_email.getText().toString().trim());
        userModel.setUserpass(register_pass.getText().toString().trim());
        userModel.setUsernikename(register_nickname.getText().toString().trim());
        return userModel;
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_button:
                mPresenter.register();
                break;
        }
    }
}
