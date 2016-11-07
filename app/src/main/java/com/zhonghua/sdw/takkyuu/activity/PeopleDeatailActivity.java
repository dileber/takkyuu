package com.zhonghua.sdw.takkyuu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.drcosu.ndileber.tools.SFont;
import com.drcosu.ndileber.utils.ActivityUtils;
import com.drcosu.ndileber.utils.UToolBar;
import com.zhonghua.sdw.takkyuu.R;
import com.zhonghua.sdw.takkyuu.data.source.SysRepository;
import com.zhonghua.sdw.takkyuu.data.source.UserRepository;
import com.zhonghua.sdw.takkyuu.fragment.TakkyuuBaseFragment;
import com.zhonghua.sdw.takkyuu.fragment.people.PeopleDeatailFragment;
import com.zhonghua.sdw.takkyuu.fragment.people.PeopleFragment;
import com.zhonghua.sdw.takkyuu.presenter.PeopleDetailPresenter;
import com.zhonghua.sdw.takkyuu.presenter.PeoplePresenter;

public class PeopleDeatailActivity extends TakkyuuBaseActivity implements TakkyuuBaseFragment.OnFragmentInteractionListener{

    private static final String USERID = "userId";
    private int userId;

    public static void start(Context context,Integer userId){
        Intent intent = new Intent();
        intent.setClass(context,PeopleDeatailActivity.class);
        intent.putExtra(USERID,userId);
        context.startActivity(intent);
    }

    @Override
    protected void startView(Bundle savedInstanceState) {

    }

    @Override
    protected int layoutViewId() {
        return R.layout.activity_people;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        UToolBar uToolBar = new UToolBar();
        uToolBar.setNeedNavigate(true);
        setToolBar(R.id.toolbar,uToolBar);
        Intent it = getIntent();
        userId = it.getIntExtra(USERID,0);
        PeopleDeatailFragment peopleFragment = ActivityUtils.getFragment(getSupportFragmentManager(),R.id.peopleFrame,PeopleDeatailFragment.newInstance(userId));
        new PeopleDetailPresenter(peopleFragment, UserRepository.getInstance());

    }

    @Override
    public SFont onRightButtonFont(Integer res) {
        String text = "";
        if(res!=null){
            text = getResources().getString(res);
        }
        SFont sFont = getView(R.id.toolbar_bar_right);
        sFont.setText(text);
        return sFont;
    }

    @Override
    public void onTitleName(String title) {
        setTitle(title);
    }
}
