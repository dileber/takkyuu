package com.zhonghua.sdw.takkyuu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.drcosu.ndileber.view.flowtag.OnInitSelectedPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HanHailong on 15/10/19.
 */
public class TagAdapter<T> extends BaseAdapter implements OnInitSelectedPosition {

    private final Context mContext;
    private final List<T> mDataList;

    public TagAdapter(Context context) {
        this.mContext = context;
        mDataList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(com.drcosu.ndileber.R.layout.tag_item, null);

        TextView textView = (TextView) view.findViewById(com.drcosu.ndileber.R.id.tv_tag);
        T t = mDataList.get(position);

        if (t instanceof String) {
            textView.setText((String) t);
        }
        return view;
    }

    public void onlyAddAll(List<T> datas) {
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }

    public void clearAndAddAll(List<T> datas) {
        mDataList.clear();
        onlyAddAll(datas);
    }

    List<T> base = null;

    public void setBaseList(List<T> datas){
        base = datas;
    }

    @Override
    public boolean isSelectedPosition(int position) {
        T t = mDataList.get(position);
        if(base!=null&&base.size()>0){
            for(T m:base){
                if(m.equals(t)){
                    return true;
                }
            }
        }
//        if (position % 2 == 0) {
//            return true;
//        }
        return false;
    }
}
