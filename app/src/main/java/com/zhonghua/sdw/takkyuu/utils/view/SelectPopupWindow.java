package com.zhonghua.sdw.takkyuu.utils.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.tools.HJson;
import com.drcosu.ndileber.utils.Check;
import com.orhanobut.logger.Logger;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.common.WheelConstants;
import com.wx.wheelview.widget.WheelView;
import com.zhonghua.sdw.takkyuu.R;
import com.zhonghua.sdw.takkyuu.data.source.SysDataSource;
import com.zhonghua.sdw.takkyuu.data.source.SysRepository;
import com.zhonghua.sdw.takkyuu.data.source.remote.SysRemoteDataSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by shidawei on 2016/9/25.
 */
public class SelectPopupWindow extends PopupWindow{

    private WheelView wheelview;
    private WheelView subWheelView;
    private TextView pop_select_save;

    public interface OnClickSaveListener {
        void onSave(int id);
        void error(String str);
    }

//    OnClickSaveListener onClickSaveListener;
//
//    public void setOnClickSaveListener(OnClickSaveListener onClickSaveListener) {
//        this.onClickSaveListener = onClickSaveListener;
//    }

    /**
     * 要修改
     * @param context
     * @param strings
     * @param integers
     */
    public SelectPopupWindow(Context context, List<String> strings, final List<Integer> integers,final OnClickSaveListener onClickSaveListener){
        super(context);
        if(strings.isEmpty()||integers.isEmpty()){
            if(onClickSaveListener!=null){
                onClickSaveListener.error("您选择的数据不存在，请重新选择");
            }
            return;
        }
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.pop_select, null);
        pop_select_save = (TextView) view.findViewById(R.id.pop_select_save);

        subWheelView =  (WheelView) view.findViewById(R.id.sub_wheelview);
        subWheelView.setVisibility(View.GONE);

        wheelview =  (WheelView) view.findViewById(R.id.wheelview);
        wheelview.setWheelAdapter(new ArrayWheelAdapter(context));
        wheelview.setSkin(WheelView.Skin.Holo);
        wheelview.setWheelData(strings);
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.selectedTextSize = 20;
        style.textSize = 16;
        wheelview.setStyle(style);
        pop_select_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClickSaveListener!=null){
                    Logger.d(wheelview.getCurrentPosition()+"fdsa");
                    onClickSaveListener.onSave(integers.get(wheelview.getCurrentPosition()));
                }
            }
        });
        init(view);

    }

    public SelectPopupWindow(Context context, final List<String> list, final HashMap<String,List<String>> hashMap, final HashMap<String,List<String>> integers,final OnClickSaveListener onClickSaveListener){
        super(context);
        if(list.isEmpty()||hashMap.isEmpty()||integers.isEmpty()){
            if(onClickSaveListener!=null){
                onClickSaveListener.error("您选择的数据不存在，请重新选择");
            }
            return;
        }

        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.pop_select, null);
        wheelview =  (WheelView) view.findViewById(R.id.wheelview);
        wheelview.setWheelAdapter(new ArrayWheelAdapter(context));
        wheelview.setSkin(WheelView.Skin.Holo);
        wheelview.setWheelData(list);
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.selectedTextSize = 20;
        style.textSize = 16;
        wheelview.setStyle(style);

        subWheelView = (WheelView) view.findViewById(R.id.sub_wheelview);
        subWheelView.setWheelAdapter(new ArrayWheelAdapter(context));
        subWheelView.setSkin(WheelView.Skin.Holo);
        subWheelView.setWheelData(hashMap.get(list.get(wheelview.getSelection())));
        subWheelView.setStyle(style);
        wheelview.join(subWheelView);
        wheelview.joinDatas(hashMap);
        pop_select_save = (TextView) view.findViewById(R.id.pop_select_save);

        pop_select_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClickSaveListener!=null){
                    onClickSaveListener.onSave(Integer.parseInt(integers.get(String.valueOf(wheelview.getCurrentPosition())).get(subWheelView.getCurrentPosition())));
                }
            }
        });

        init(view);
    }



    private void init(View view){
        //设置SelectPicPopupWindow的View
        this.setContentView(view);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

//                int height = v.findViewById(R.id.wheelview).getTop();
//                int y=(int) event.getY();
//                if(event.getAction()==MotionEvent.ACTION_UP){
//                    if(y<height){
//                        dismiss();
//                    }
//                }
                return true;
            }
        });
    }


}
