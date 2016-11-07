package com.zhonghua.sdw.takkyuu.fragment.commen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.drcosu.ndileber.mvp.acivity.BaseActivity;
import com.drcosu.ndileber.mvp.fragment.BaseFragment;
import com.zhonghua.sdw.takkyuu.R;

public class UserTitleFragment extends BaseFragment {

    private static final String USER_NAME = "name";
    private static final String USER_IMAGE = "image";
    private static final String USER_OPTION = "option";

    private String name;
    private String image;
    private String option;

    public UserTitleFragment() {
        // Required empty public constructor
    }

    public static UserTitleFragment newInstance() {
        UserTitleFragment fragment = new UserTitleFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(USER_NAME);
            image = getArguments().getString(USER_IMAGE);
            option = getArguments().getString(USER_OPTION);
        }
    }


    TextView userName;
    TextView userOption;
    ImageView userImage;

    @Override
    protected int layoutViewId() {
        return R.layout.user_title;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        userName = getView(view,R.id.user_title_name);
        userOption = getView(view,R.id.user_option);
        userImage = getView(view,R.id.user_title_image);
    }

    @Override
    protected void show() {

    }

    @Override
    protected void hidden() {

    }

}
