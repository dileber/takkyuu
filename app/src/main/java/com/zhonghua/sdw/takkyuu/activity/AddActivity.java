package com.zhonghua.sdw.takkyuu.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.drcosu.ndileber.mvp.acivity.BaseActivity;
import com.zhonghua.sdw.takkyuu.R;

public class AddActivity extends BaseActivity {

    public static void start(Context context){
        Intent intent = new Intent();
        intent.setClass(context,AddActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void startView(Bundle savedInstanceState) {

    }

    @Override
    protected int layoutViewId() {
        return R.layout.activity_add;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }
}
