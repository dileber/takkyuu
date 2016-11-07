package com.zhonghua.sdw.takkyuu.fragment.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drcosu.ndileber.mvp.acivity.BaseActivity;
import com.drcosu.ndileber.mvp.fragment.BaseFragment;
import com.drcosu.ndileber.tools.SFont;
import com.drcosu.ndileber.utils.ActivityUtils;
import com.zhonghua.sdw.takkyuu.R;
import com.zhonghua.sdw.takkyuu.activity.AddActivity;
import com.zhonghua.sdw.takkyuu.activity.HomeActivity;
import com.zhonghua.sdw.takkyuu.adapter.MatchAdapter;
import com.zhonghua.sdw.takkyuu.contract.HomeContract;
import com.zhonghua.sdw.takkyuu.contract.PeopleContract;
import com.zhonghua.sdw.takkyuu.data.model.MatchModel;
import com.zhonghua.sdw.takkyuu.fragment.TakkyuuBaseFragment;
import com.zhonghua.sdw.takkyuu.fragment.commen.UserTitleFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends TakkyuuBaseFragment implements HomeContract.View{

    public HomeFragment() {

    }

    HomeContract.Presenter mPresenter;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int layoutViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    MatchAdapter matchAdapter;
    RecyclerView home_recycle;
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ActivityUtils.getFragment(getChildFragmentManager(),R.id.home_user, UserTitleFragment.newInstance());
        home_recycle = findView(R.id.home_recycle);
        home_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        matchAdapter = new MatchAdapter(getActivity(),new ArrayList<MatchModel>());
        home_recycle.setAdapter(matchAdapter);
        mPresenter.getMatch();
    }

    @Override
    protected void show() {
        SFont sFont = setRightButtonFont(R.string.plus);
        setTitle(getResources().getString(R.string.takkyuu_home));

        sFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddActivity.start(getActivity());
            }
        });
    }

    @Override
    protected void hidden() {
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void showMatch(List<MatchModel> matchModels) {
        matchAdapter.addCommentModel(matchModels);
    }


    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        super.setPresenter(presenter);
        mPresenter = presenter;
    }
}
