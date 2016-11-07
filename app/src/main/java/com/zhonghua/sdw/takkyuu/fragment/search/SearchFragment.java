package com.zhonghua.sdw.takkyuu.fragment.search;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drcosu.ndileber.mvp.fragment.BaseFragment;
import com.drcosu.ndileber.tools.SFont;
import com.zhonghua.sdw.takkyuu.R;
import com.zhonghua.sdw.takkyuu.activity.AddActivity;
import com.zhonghua.sdw.takkyuu.activity.HomeActivity;
import com.zhonghua.sdw.takkyuu.adapter.ConsAdapter;
import com.zhonghua.sdw.takkyuu.contract.SearchContract;
import com.zhonghua.sdw.takkyuu.data.model.FollowsModel;
import com.zhonghua.sdw.takkyuu.data.model.UserInfoModel;
import com.zhonghua.sdw.takkyuu.fragment.TakkyuuBaseFragment;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends TakkyuuBaseFragment implements SearchContract.View{

    public SearchFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    RecyclerView search_recycle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    protected int layoutViewId() {
        return R.layout.fragment_search;
    }

    ConsAdapter consAdapter;
    @Override
    protected void initView(Bundle savedInstanceState) {
        search_recycle = findView(R.id.search_recycle);
        consAdapter = new ConsAdapter(getContext(),new ArrayList<FollowsModel>());
        search_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        search_recycle.setAdapter(consAdapter);
        mPresenter.getUserInfos();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void show() {
        SFont sFont = setRightButtonFont(R.string.m_search);
        sFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddActivity.start(getActivity());
            }
        });
        setTitle(getResources().getString(R.string.takkyuu_serch));
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
    public void showUserInfos(List<FollowsModel> list) {
        consAdapter.addUserInfoModel(list);
    }

    SearchContract.Presenter mPresenter;
    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        super.setPresenter(presenter);
        mPresenter = presenter;
    }
}
