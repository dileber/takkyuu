package com.zhonghua.sdw.takkyuu.fragment.people;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.drcosu.ndileber.tools.SFont;
import com.drcosu.ndileber.tools.UAge;
import com.drcosu.ndileber.tools.UDialog;
import com.drcosu.ndileber.view.flowtag.FlowTagLayout;
import com.mianbaopai.sdw.imsystem.ui.ChatActivity;
import com.zhonghua.sdw.takkyuu.R;
import com.zhonghua.sdw.takkyuu.adapter.PeopleCommentAdapter;
import com.zhonghua.sdw.takkyuu.adapter.TagAdapter;
import com.zhonghua.sdw.takkyuu.contract.PeopleContract;
import com.zhonghua.sdw.takkyuu.contract.PeopleDetailContract;
import com.zhonghua.sdw.takkyuu.data.enums.GredenEnum;
import com.zhonghua.sdw.takkyuu.data.model.UserCommentModel;
import com.zhonghua.sdw.takkyuu.data.model.UserInfoModel;
import com.zhonghua.sdw.takkyuu.fragment.TakkyuuBaseFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by H2 on 2016/10/12.
 */
public class PeopleDeatailFragment extends TakkyuuBaseFragment  implements PeopleDetailContract.View{

    PeopleDetailContract.Presenter mPresenter;


    private static final String USERID = "userId";
    private int userId;
    public static PeopleDeatailFragment newInstance(Integer userId) {
        Bundle args = new Bundle();
        args.putInt(USERID,userId);
        PeopleDeatailFragment fragment = new PeopleDeatailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            userId = getArguments().getInt(USERID);
        }
    }

    @Override
    protected int layoutViewId() {
        return R.layout.fragment_people;
    }

    EditText people_name,people_gerden,people_age,people_weight,people_height,
            people_club,people_floor,people_hand,people_backhand,people_flag,people_introduce;


    RecyclerView recyclerView;

    private FlowTagLayout people_flow_layout;
    private TagAdapter<String> tagAdapter;
    private PeopleCommentAdapter peopleCommentAdapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        people_flow_layout = findView(R.id.people_skill_flow_layout);

        tagAdapter = new TagAdapter<>(getActivity().getApplicationContext());
        people_flow_layout.setAdapter(tagAdapter);

        recyclerView = findView(R.id.comment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        peopleCommentAdapter = new PeopleCommentAdapter(getActivity(),new ArrayList<UserCommentModel>());
        recyclerView.setAdapter(peopleCommentAdapter);
        people_name = initItemView(R.id.people_name,"姓名：");
        people_gerden = initItemView(R.id.people_greden,"姓别：");
        people_age= initItemView(R.id.people_age,"年龄：");
        people_age.setFocusableInTouchMode(false);
        people_weight= initItemView(R.id.people_weight,"体重(kg)：");
        people_height= initItemView(R.id.people_height,"身高(cm)：");
        people_club= initItemView(R.id.people_club,"俱乐部：");
        people_floor= initItemView(R.id.people_floor,"底板：");
        people_floor.setFocusableInTouchMode(false);
        people_backhand= initItemView(R.id.people_backhand,"反手(胶皮)：");
        people_backhand.setFocusableInTouchMode(false);

        people_hand= initItemView(R.id.people_hand,"正手(胶皮)：");
        people_hand.setFocusableInTouchMode(false);

        people_flag= initItemView(R.id.people_flag,"参赛宣言：");
        people_introduce = initItemView(R.id.people_introduce,"自我介绍：");
        people_age.setClickable(false);

        mulitLine(people_introduce);
    }

    private EditText initItemView(int id,String text){
        View v = findView(id);
        TextView textView = (TextView) v.findViewById(R.id.people_title);
        EditText editText = (EditText) v.findViewById(R.id.people_item);
        editText.setInputType(InputType.TYPE_NULL);
        textView.setText(text);
        return editText;
    }

    private void mulitLine(EditText editText){
        editText.setMaxLines(5);
        editText.setMinLines(3);
        editText.setGravity(Gravity.TOP|Gravity.LEFT);
        editText.setSingleLine(false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showView();
    }

    private void showView(){
        mPresenter.getUserInfo(userId);
        mPresenter.getComment(userId);
        mPresenter.checkFollows(userId);

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void show() {

    }

    @Override
    protected void hidden() {

    }
    Date age;
    int greden;
    int weight;
    int height;
    int club;
    int floor;
    int hand;
    int backhand;
    String flag;
    String instruction;
    String skill;
    String userName;
    @Override
    public void showUserInfo(UserInfoModel userInfoModel) {
        userName = userInfoModel.getUserinfoname();
        age = userInfoModel.getUserinfobirthday();
        greden = userInfoModel.getUserinfogender();
        weight = userInfoModel.getUserinfoweight();
        height = userInfoModel.getUserinfoheight();
        club = userInfoModel.getUserinfoclub();
        floor = userInfoModel.getUserinfofloor();
        hand = userInfoModel.getUserinfohand();
        backhand = userInfoModel.getUserinfobackhand();
        flag = userInfoModel.getUserinfoflag();
        instruction = userInfoModel.getUserinfointroduce();
        skill = userInfoModel.getUserinfoskill();
        addItem(people_name,userInfoModel.getUserinfoname());
        addItems();
        if(skill!=null){
            String[] data = skill.split(",");
            List<String> dataSource = new ArrayList<>();
            if(data!=null&&data.length>0){
                for(String s:data){
                    dataSource.add(s);
                }
            }
            people_flow_layout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_NONE);
            people_flow_layout.setOnTagSelectListener(null);
            tagAdapter.setBaseList(null);
            tagAdapter.clearAndAddAll(dataSource);

        }
//        mPresenter.getUserInfo(userId);
    }
    private void addItems(){
        addItem(people_gerden, GredenEnum.getTheName(greden));
        addItem(people_age, UAge.getAge(age));
        addItem(people_weight,weight);
        addItem(people_height,height);
        addItem(people_club,club);
        addItem(people_floor,mPresenter.getFloor(floor));
        addItem(people_hand,mPresenter.getRubber(hand));
        addItem(people_backhand,mPresenter.getRubber(backhand));
        addItem(people_flag,flag);
        addItem(people_introduce,instruction);

    }
    private void addItem(TextView textView,Object value){
        if(value==null||value.toString().length()==0){
            textView.setHint("暂无");
            textView.setText("");
        }else{
            textView.setText(value.toString());
        }
    }

    @Override
    public void setTitleName(String title)  {
        setTitle(title);
    }

    @Override
    public void showComment(List<UserCommentModel> userCommentModels) {
        peopleCommentAdapter.addCommentModel(userCommentModels);
    }

    @Override
    public void setRightButton(int state) {
        SFont sFont ;
        if(state==-1){
            sFont = setRightButtonFont(R.string.plus);
            sFont.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPresenter.followsToUser(userId);
                }
            });
        }else if(state==0){
            sFont = setRightButtonFont(R.string.chat2);
            sFont.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ChatActivity.start(getContext(),new Intent(),String.valueOf(userId),userName);
                }
            });
        }else{
            sFont = setRightButtonFont(R.string.notice2);
            sFont.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

    }

    @Override
    public void setPresenter(PeopleDetailContract.Presenter presenter) {
        super.setPresenter(presenter);
        mPresenter = presenter;
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
}
