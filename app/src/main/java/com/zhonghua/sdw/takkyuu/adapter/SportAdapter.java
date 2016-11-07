package com.zhonghua.sdw.takkyuu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by H2 on 2016/10/12.
 */
public class SportAdapter extends RecyclerView.Adapter<SportAdapter.SportHolder>{


    @Override
    public SportHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(SportHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class SportHolder extends RecyclerView.ViewHolder{

        public SportHolder(View itemView) {
            super(itemView);
        }
    }

}
