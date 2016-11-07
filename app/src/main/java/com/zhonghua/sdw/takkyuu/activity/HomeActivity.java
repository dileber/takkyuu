package com.zhonghua.sdw.takkyuu.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.Log;
import android.widget.TextView;

import com.drcosu.ndileber.mvp.acivity.BaseActivity;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.mvp.fragment.BaseFragment;
import com.drcosu.ndileber.tools.DialogLinstener;
import com.drcosu.ndileber.tools.HJson;
import com.drcosu.ndileber.tools.SFont;
import com.drcosu.ndileber.tools.UDialog;
import com.drcosu.ndileber.utils.ActivityUtils;
import com.drcosu.ndileber.utils.UToolBar;
import com.drcosu.ndileber.view.BottomBar;
import com.drcosu.ndileber.view.BottomBarTab;
import com.orhanobut.logger.Logger;
import com.zhonghua.sdw.takkyuu.R;
import com.zhonghua.sdw.takkyuu.data.model.LaunchWrapper;
import com.zhonghua.sdw.takkyuu.data.model.UserWrapper;
import com.zhonghua.sdw.takkyuu.data.source.SysDataSource;
import com.zhonghua.sdw.takkyuu.data.source.SysRepository;
import com.zhonghua.sdw.takkyuu.data.source.UserRepository;
import com.zhonghua.sdw.takkyuu.data.source.remote.SysRemoteDataSource;
import com.zhonghua.sdw.takkyuu.fragment.TakkyuuBaseFragment;
import com.zhonghua.sdw.takkyuu.fragment.cons.ConsFragment;
import com.zhonghua.sdw.takkyuu.fragment.home.HomeFragment;
import com.zhonghua.sdw.takkyuu.fragment.people.PeopleFragment;
import com.zhonghua.sdw.takkyuu.fragment.search.SearchFragment;
import com.zhonghua.sdw.takkyuu.presenter.HomePresenter;
import com.zhonghua.sdw.takkyuu.presenter.PeoplePresenter;
import com.zhonghua.sdw.takkyuu.presenter.SearchPresenter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends TakkyuuBaseActivity implements TakkyuuBaseFragment.OnFragmentInteractionListener{
    private List<BaseFragment> list = new ArrayList<>();

    private final int[] title ={
            R.string.takkyuu_home,
            R.string.takkyuu_cons,
            R.string.takkyuu_serch,
            R.string.takkyuu_myname,
    };

    public static void start(Context context){
        Intent intent = new Intent();
        intent.setClass(context,HomeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void startView(Bundle savedInstanceState) {

    }

    @Override
    protected int layoutViewId() {
        return R.layout.activity_home;
    }
    private final static String SELECT = "select";
    BottomBar mBar;
    Fragment temp;
    @Override
    protected void initView(final Bundle savedInstanceState) {
        UToolBar uToolBar = new UToolBar();
        uToolBar.setTitleId(R.string.takkyuu_home);
        uToolBar.setNeedNavigate(false);
        setToolBar(R.id.toolbar,uToolBar);
        mBar= getView(R.id.bar);
        mBar.addItem(new BottomBarTab(this,R.string.home3,BottomBarTab.TYPE_FONT)).
                addItem(new BottomBarTab(this,R.string.tree,BottomBarTab.TYPE_FONT)).
                addItem(new BottomBarTab(this,R.string.m_search,BottomBarTab.TYPE_FONT)).
                addItem(new BottomBarTab(this,R.string.user,BottomBarTab.TYPE_FONT));
        mBar.setOnClickItemMenu(new BottomBar.OnClickItemMenu() {
            @Override
            public void onClickItem(int nowPosition,int position) {
                FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();
                switchContent(list.get(nowPosition),list.get(position));
                setTitle(getResources().getString(title[position]));

            }
        });

        HomeFragment homeFragment = ActivityUtils.getFragment(getSupportFragmentManager(),R.id.homeFrame,HomeFragment.newInstance());
        ConsFragment consFragment = ActivityUtils.getFragment(getSupportFragmentManager(),R.id.consFrame,ConsFragment.newInstance("3","2"));
        SearchFragment searchFragment = ActivityUtils.getFragment(getSupportFragmentManager(),R.id.searchFrame,SearchFragment.newInstance());
        PeopleFragment peopleFragment = ActivityUtils.getFragment(getSupportFragmentManager(),R.id.peopleFrame,PeopleFragment.newInstance());

        new HomePresenter(homeFragment,SysRepository.getInstance());
        new PeoplePresenter(peopleFragment,SysRepository.getInstance());
        new SearchPresenter(searchFragment,SysRepository.getInstance());

        list.add(homeFragment);
        list.add(consFragment);
        list.add(searchFragment);
        list.add(peopleFragment);

        FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();
        transaction.hide(homeFragment).hide(consFragment).hide(searchFragment).hide(peopleFragment).show(homeFragment).commit();

        if(savedInstanceState!=null){
            mBar.setCurrentItem(savedInstanceState.getInt(SELECT));
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECT,mBar.getmCurrentPosition());
        super.onSaveInstanceState(outState);
    }

//    @Deprecated
//    public SFont getRightButton(Integer strResId) {
//        String text = "";
//        if(strResId!=null){
//            text = getResources().getString(strResId);
//        }
//        SFont sFont = getView(R.id.toolbar_bar_right);
//        sFont.setText(text);
//        return sFont;
//    }

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

    @Override
    public void onBackPressed() {
        dialogOk("确定要退出乒乓么", new DialogLinstener() {
            @Override
            public void confirm(Dialog dialog) {
                dialog.dismiss();
                HomeActivity.super.onBackPressed();
            }

            @Override
            public void cancel(Dialog dialog) {
                dialog.dismiss();
            }
        });

    }
}
