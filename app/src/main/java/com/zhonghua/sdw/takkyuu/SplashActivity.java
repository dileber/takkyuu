package com.zhonghua.sdw.takkyuu;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.drcosu.ndileber.mvp.acivity.BaseActivity;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.tools.HNetwork;
import com.drcosu.ndileber.tools.UUi;
import com.drcosu.ndileber.tools.annotation.CloseStatusBar;
import com.drcosu.ndileber.tools.annotation.CloseTitle;
import com.zhonghua.sdw.takkyuu.activity.LoginActivity;
import com.zhonghua.sdw.takkyuu.data.model.LaunchWrapper;
import com.zhonghua.sdw.takkyuu.data.source.SysRepository;

@CloseStatusBar
public class SplashActivity extends BaseActivity {

    Splashhandler splashhandler;
    Handler x;

    boolean launch;
    boolean handler;

    @Override
    protected void startView(Bundle savedInstanceState) {

    }

    @Override
    protected int layoutViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        launch = false;
        handler = false;
        SysRepository.getInstance().launch(new BaseDataSource.BaseCallback<LaunchWrapper>() {
            @Override
            public void onSuccess(LaunchWrapper launchWrapper) {
                launch = true;
                SysRepository.getInstance().saveLaunch(launchWrapper.getData());
                startTo();
            }

            

            @Override
            public void onFailure(DataSourceException e) {
                launch = true;
                startTo();
            }
        });
        x = new Handler();
        splashhandler = new Splashhandler();
        if(!HNetwork.getInstance().checkNetwork()){
            UUi.toast(SplashActivity.this,"网络未连接", Toast.LENGTH_SHORT);
        }
    }

    private void startTo() {
        if(launch&&handler){
            LoginActivity.start(SplashActivity.this,null);
            finish();
        }
    }

    class Splashhandler implements Runnable{

        public void run() {
            handler = true;
            startTo();
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        if(x!=null&&splashhandler!=null){
            x.removeCallbacks(splashhandler);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(x!=null&&splashhandler!=null){
            x.postDelayed(splashhandler, 3000);
        }
    }

}
