package com.zhonghua.sdw.takkyuu.fragment.people;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.platform.comapi.map.E;
import com.drcosu.ndileber.mvp.fragment.BaseFragment;
import com.drcosu.ndileber.tools.SFont;
import com.drcosu.ndileber.tools.UAge;
import com.drcosu.ndileber.tools.UDialog;
import com.drcosu.ndileber.tools.UTime;
import com.drcosu.ndileber.tools.UUi;
import com.drcosu.ndileber.view.GenderDialog;
import com.drcosu.ndileber.view.RecycleViewDivider;
import com.drcosu.ndileber.view.flowtag.FlowTagLayout;
import com.drcosu.ndileber.view.flowtag.OnTagClickListener;
import com.drcosu.ndileber.view.flowtag.OnTagSelectListener;
import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.orhanobut.logger.Logger;
import com.zhonghua.sdw.takkyuu.R;
import com.zhonghua.sdw.takkyuu.activity.AddActivity;
import com.zhonghua.sdw.takkyuu.activity.HomeActivity;
import com.zhonghua.sdw.takkyuu.adapter.PeopleCommentAdapter;
import com.zhonghua.sdw.takkyuu.adapter.TagAdapter;
import com.zhonghua.sdw.takkyuu.contract.PeopleContract;
import com.zhonghua.sdw.takkyuu.data.enums.FloorEnum;
import com.zhonghua.sdw.takkyuu.data.enums.GredenEnum;
import com.zhonghua.sdw.takkyuu.data.enums.RubberEnum;
import com.zhonghua.sdw.takkyuu.data.model.UserCommentModel;
import com.zhonghua.sdw.takkyuu.data.model.UserInfoModel;
import com.zhonghua.sdw.takkyuu.data.source.SysRepository;
import com.zhonghua.sdw.takkyuu.fragment.TakkyuuBaseFragment;
import com.zhonghua.sdw.takkyuu.utils.view.SelectPopupWindow;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class PeopleFragment extends TakkyuuBaseFragment implements PeopleContract.View,DatePickerDialog.OnDateSetListener {

    private static final String KEY_GREEND = "grend";
    private static final String KEY_AGE = "age";
    private static final String KEY_WEIGHT = "weght";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_CLUB = "club";
    private static final String KEY_FLOOR = "floor";
    private static final String KEY_HAND = "hand";
    private static final String KEY_BACKHAND = "backhand";
    private static final String KEY_FLAG = "flag";
    private static final String KEY_INSTURDUCTION = "instrudction";
    private static final String KEY_SKILL = "skill";
    private static final String KEY_SHOW = "show";

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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_GREEND, greden);
        outState.putSerializable(KEY_AGE, age);
        outState.putInt(KEY_WEIGHT, weight);
        outState.putInt(KEY_HEIGHT, height);
        outState.putInt(KEY_CLUB, club);
        outState.putInt(KEY_FLOOR, floor);
        outState.putInt(KEY_HAND, hand);
        outState.putInt(KEY_BACKHAND, backhand);
        outState.putString(KEY_FLAG, flag);
        outState.putString(KEY_INSTURDUCTION, instruction);
        outState.putString(KEY_SKILL,skill);
        outState.putBoolean(KEY_SHOW, show);
    }

    PeopleContract.Presenter mPresenter;

    public PeopleFragment() {
        // Required empty public constructor
    }


    public static PeopleFragment newInstance() {
        PeopleFragment fragment = new PeopleFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int layoutViewId() {
        return R.layout.fragment_people;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    EditText people_name,people_gerden,people_age,people_weight,people_height,
            people_club,people_floor,people_hand,people_backhand,people_flag,people_introduce;


    RecyclerView recyclerView;
    DatePickerDialog datePickerDialog;

    Calendar calendar ;

    private FlowTagLayout people_flow_layout;
    private TagAdapter<String> tagAdapter;
    private PeopleCommentAdapter peopleCommentAdapter;
    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        people_flow_layout = getView(view,R.id.people_skill_flow_layout);

        tagAdapter = new TagAdapter<>(getActivity().getApplicationContext());
        people_flow_layout.setAdapter(tagAdapter);

//        people_flow_layout.setOnTagClickListener(new OnTagClickListener() {
//            @Override
//            public void onItemClick(FlowTagLayout parent, View view, int position) {
//
//            }
//        });

        recyclerView = findView(R.id.comment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        peopleCommentAdapter = new PeopleCommentAdapter(getActivity(),new ArrayList<UserCommentModel>());
        recyclerView.setAdapter(peopleCommentAdapter);
        people_name = initItemView(view,R.id.people_name,"姓名：");
        people_gerden = initItemView(view,R.id.people_greden,"姓别：");
        people_age= initItemView(view,R.id.people_age,"年龄：");
        people_age.setFocusableInTouchMode(false);
        people_weight= initItemView(view,R.id.people_weight,"体重(kg)：");
        people_height= initItemView(view,R.id.people_height,"身高(cm)：");
        people_club= initItemView(view,R.id.people_club,"俱乐部：");
        people_floor= initItemView(view,R.id.people_floor,"底板：");
        people_floor.setFocusableInTouchMode(false);
        people_backhand= initItemView(view,R.id.people_backhand,"反手(胶皮)：");
        people_backhand.setFocusableInTouchMode(false);

        people_hand= initItemView(view,R.id.people_hand,"正手(胶皮)：");
        people_hand.setFocusableInTouchMode(false);

        people_flag= initItemView(view,R.id.people_flag,"参赛宣言：");
        people_introduce = initItemView(view,R.id.people_introduce,"自我介绍：");
        people_age.setClickable(false);

        mulitLine(people_introduce);
        calendar = Calendar.getInstance();
        datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), true);
        if(savedInstanceState!=null){
            show = savedInstanceState.getBoolean(KEY_SHOW);
            if(!show){
                greden = savedInstanceState.getInt(KEY_GREEND);
                age = (Date) savedInstanceState.getSerializable(KEY_AGE);
                weight = savedInstanceState.getInt(KEY_WEIGHT);
                height = savedInstanceState.getInt(KEY_HEIGHT);
                club = savedInstanceState.getInt(KEY_CLUB);
                floor = savedInstanceState.getInt(KEY_FLOOR);
                hand = savedInstanceState.getInt(KEY_HAND);
                backhand = savedInstanceState.getInt(KEY_BACKHAND);
                flag = savedInstanceState.getString(KEY_FLAG);
                instruction = savedInstanceState.getString(KEY_INSTURDUCTION);
                skill = savedInstanceState.getString(KEY_SKILL);
                Logger.sl(Log.DEBUG,greden,age,weight,height,club,floor,flag,hand,backhand,instruction);
            }
        }else {
            show = true;
        }

        mPresenter.getComment();

    }

    public static final String DATEPICKER_TAG = "datepicker";
    SelectPopupWindow selectPopupWindow;

    private void editView(){
        getView(getView(),R.id.people_name).setVisibility(View.GONE);
        people_weight.setInputType(InputType.TYPE_CLASS_NUMBER);
        people_height.setInputType(InputType.TYPE_CLASS_NUMBER);
        people_flag.setInputType(InputType.TYPE_CLASS_TEXT);
        people_introduce.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        people_age.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                datePickerDialog.setTitle("选择您的出生日期");
                datePickerDialog.setVibrate(true);
                datePickerDialog.setYearRange(1949,calendar.get(Calendar.YEAR));
                datePickerDialog.setMaxYearMonth(calendar.get(Calendar.MONTH));
                datePickerDialog.setCloseOnSingleTapDay(false);
                datePickerDialog.show(getChildFragmentManager(), DATEPICKER_TAG);
            }

        });


        people_floor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPopupWindow = new SelectPopupWindow(getContext(),
                        mPresenter.floorEnum(),
                        mPresenter.floorEnumId(),
                        new SelectPopupWindow.OnClickSaveListener() {
                    @Override
                    public void onSave(int id) {
                        Logger.sl(Log.DEBUG,"底板",id);
                        selectPopupWindow.dismiss();
                        selectPopupWindow = new SelectPopupWindow(getContext(),
                                mPresenter.createMainFloor(FloorEnum.getTheFloor(id)),
                                mPresenter.createSubFloor(FloorEnum.getTheFloor(id)),
                                mPresenter.subFloorId(FloorEnum.getTheFloor(id)),
                                new SelectPopupWindow.OnClickSaveListener() {
                            @Override
                            public void onSave(int id) {
                                floor = id;
                                addItems();
                                selectPopupWindow.dismiss();
                            }

                            @Override
                            public void error(String str) {
                                showAlert(UDialog.DIALOG_ERROR,str);

                            }
                        });
                        //显示窗口
                        selectPopupWindow.showAtLocation(getView(getView(),R.id.people_name), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置

                    }

                    @Override
                    public void error(String str) {
                        showAlert(UDialog.DIALOG_ERROR,str);

                    }
                });
                //显示窗口
                selectPopupWindow.showAtLocation(getView(getView(),R.id.people_name), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置


            }
        });

        people_hand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPopupWindow = new SelectPopupWindow(getContext(),mPresenter.rubberEnum(),
                        mPresenter.rubberEnumId(),new SelectPopupWindow.OnClickSaveListener() {
                    @Override
                    public void onSave(int id) {
                        Logger.sl(Log.DEBUG,"正手",id);
                        selectPopupWindow.dismiss();
                        selectPopupWindow = new SelectPopupWindow(getContext(),
                                mPresenter.createMainRubber(RubberEnum.getTheRubber(id)),
                                mPresenter.createSubRubber(RubberEnum.getTheRubber(id)),
                                mPresenter.subRubberId(RubberEnum.getTheRubber(id)),
                                new SelectPopupWindow.OnClickSaveListener() {
                            @Override
                            public void onSave(int id) {
                                hand = id;
                                addItems();
                                selectPopupWindow.dismiss();
                            }

                            @Override
                            public void error(String str) {
                                showAlert(UDialog.DIALOG_ERROR,str);

                            }
                        });
                        //显示窗口
                        selectPopupWindow.showAtLocation(getView(getView(),R.id.people_name), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置

                    }

                    @Override
                    public void error(String str) {
                        showAlert(UDialog.DIALOG_ERROR,str);

                    }
                });
                //显示窗口
                selectPopupWindow.showAtLocation(getView(getView(),R.id.people_name), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置

            }
        });

        people_backhand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPopupWindow = new SelectPopupWindow(getContext(),mPresenter.rubberEnum(),
                        mPresenter.rubberEnumId(),new SelectPopupWindow.OnClickSaveListener() {
                    @Override
                    public void onSave(int id) {
                        Logger.sl(Log.DEBUG,"反手",id);
                        selectPopupWindow.dismiss();
                        selectPopupWindow = new SelectPopupWindow(getContext(),
                                mPresenter.createMainRubber(RubberEnum.getTheRubber(id)),
                                mPresenter.createSubRubber(RubberEnum.getTheRubber(id)),
                                mPresenter.subRubberId(RubberEnum.getTheRubber(id)),new SelectPopupWindow.OnClickSaveListener() {
                            @Override
                            public void onSave(int id) {
                                backhand = id;
                                addItems();
                                selectPopupWindow.dismiss();
                            }

                            @Override
                            public void error(String str) {
                                showAlert(UDialog.DIALOG_ERROR,str);
                            }
                        });
                        //显示窗口
                        selectPopupWindow.showAtLocation(getView(getView(),R.id.people_name), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置

                    }

                    @Override
                    public void error(String str) {
                        showAlert(UDialog.DIALOG_ERROR,str);
                    }
                });
                //显示窗口
                selectPopupWindow.showAtLocation(getView(getView(),R.id.people_name), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置

            }
        });




        people_weight.addTextChangedListener(weightWatcher);
        people_height.addTextChangedListener(heightWatcher);
        people_flag.addTextChangedListener(flagWatcher);

        people_introduce.addTextChangedListener(introduceWatcher);



        people_gerden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectPopupWindow = new SelectPopupWindow(getContext(),mPresenter.genderEnum(),mPresenter.genderEnumId(),new SelectPopupWindow.OnClickSaveListener() {
                    @Override
                    public void onSave(int id) {
                        greden = id;
                        addItems();
                        selectPopupWindow.dismiss();
                    }

                    @Override
                    public void error(String str) {
                        showAlert(UDialog.DIALOG_ERROR,str);

                    }
                });
                //显示窗口
                selectPopupWindow.showAtLocation(getView(getView(),R.id.people_name), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置


//                new GenderDialog.Builder(getContext()).setMenButton(new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        greden = GredenEnum.Men.getVar();
//                        addItems();
//
//                    }
//                }).setWomenButton(new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        greden = GredenEnum.Women.getVar();
//                        addItems();
//
//                    }
//                }).create().show();

            }
        });
        mulitLine(people_introduce);
        List<String> dataSource = mPresenter.getSkill();
        people_flow_layout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        if(skill!=null){
            String[] data = skill.split(",");
            List<String> base = new ArrayList<>();
            if(data!=null&&data.length>0){
                for(String s:data){
                    base.add(s);
                }
            }
            tagAdapter.setBaseList(base);
        }else{
            tagAdapter.setBaseList(null);
        }

        tagAdapter.clearAndAddAll(dataSource);
        people_flow_layout.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i : selectedList) {
                        sb.append(parent.getAdapter().getItem(i));
                        sb.append(",");
                    }
                    skill = sb.substring(0,sb.length()-1);
                    //UUi.toast(getActivity(),sb.toString(),Toast.LENGTH_SHORT);
                }
            }
        });
    }


    TextWatcher flagWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            flag = charSequence.toString().trim();

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    TextWatcher heightWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String data = charSequence.toString().trim();
            if(data.length()==0){
                height = 0;
            }else{
                height = Integer.valueOf(data);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    TextWatcher weightWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String data = charSequence.toString().trim();
            if(data.length()==0){
                weight = 0;
            }else{
                weight = Integer.valueOf(data);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    TextWatcher introduceWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            instruction = charSequence.toString().trim();

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private void mulitLine(EditText editText){
        editText.setMaxLines(5);
        editText.setMinLines(3);
        editText.setGravity(Gravity.TOP|Gravity.LEFT);
        editText.setSingleLine(false);

    }

    private void showView(){
        getView(getView(),R.id.people_name).setVisibility(View.VISIBLE);
        people_weight.setInputType(InputType.TYPE_NULL);
        people_height.setInputType(InputType.TYPE_NULL);
        people_flag.setInputType(InputType.TYPE_NULL);
        people_introduce.setInputType(InputType.TYPE_NULL);
        people_age.setOnClickListener(null);
        people_gerden.setOnClickListener(null);
        mulitLine(people_introduce);
        people_height.removeTextChangedListener(heightWatcher);
        people_weight.removeTextChangedListener(weightWatcher);
        people_flag.removeTextChangedListener(flagWatcher);
        people_introduce.removeTextChangedListener(introduceWatcher);
        people_floor.setOnClickListener(null);
        people_backhand.setOnClickListener(null);
        people_hand.setOnClickListener(null);
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
    }

    private EditText initItemView(View view,int id,String text){
        View v = getView(view,id);
        TextView textView = (TextView) v.findViewById(R.id.people_title);
        EditText editText = (EditText) v.findViewById(R.id.people_item);
        editText.setInputType(InputType.TYPE_NULL);
        textView.setText(text);
        return editText;
    }

    private void addItem(TextView textView,Object value){
        if(value==null||value.toString().length()==0){
            textView.setHint("暂无");
            textView.setText("");
        }else{
            textView.setText(value.toString());
        }
    }

    boolean show = true;

    @Override
    protected void show() {
        Logger.sl(Log.DEBUG,show,"show");
        SFont sFont ;
        mPresenter.getUserName();
        if(show){
            sFont = setRightButtonFont(R.string.edit);
            mPresenter.getUserInfo();
            showView();
        }else{
            sFont = setRightButtonFont(R.string.checkmark);
            addItems();
            editView();
        }
        sFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AddActivity.start(getActivity());
                if(show) {
                    editView();
                    setRightButtonFont(R.string.checkmark);
                }else{
                    mPresenter.updateUserInfo();
                    showView();
                    setRightButtonFont(R.string.edit);
                }
                show = !show;
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void hidden() {

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showUserInfo(UserInfoModel userInfoModel) {
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
    }

    private void addItems(){
        addItem(people_gerden,GredenEnum.getTheName(greden));
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

    @Override
    public UserInfoModel getUserInfo() {
        UserInfoModel userInfoModel = new UserInfoModel();
        userInfoModel.setUserinfobackhand(backhand);
        userInfoModel.setUserinfobirthday(age);
        userInfoModel.setUserinfofloor(floor);
        userInfoModel.setUserinfogender(greden);
        userInfoModel.setUserinfoheight(height);
        userInfoModel.setUserinfoweight(weight);
        userInfoModel.setUserinfoflag(flag);
        userInfoModel.setUserinfointroduce(instruction);
        userInfoModel.setUserinfoskill(skill);
        return userInfoModel;
    }

    @Override
    public void setTitleName(String title) {
        setTitle(title);
    }

    @Override
    public void showComment(List<UserCommentModel> userCommentModels) {
        peopleCommentAdapter.addCommentModel(userCommentModels);
    }

    @Override
    public void setPresenter(PeopleContract.Presenter presenter) {
        super.setPresenter(presenter);
        this.mPresenter = presenter;
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
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
        age = UTime.getDatetime(year, month, day);
        addItems();
    }

}
