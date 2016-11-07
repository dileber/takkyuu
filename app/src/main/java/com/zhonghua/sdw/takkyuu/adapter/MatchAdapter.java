package com.zhonghua.sdw.takkyuu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.tools.UTime;
import com.zhonghua.sdw.takkyuu.R;
import com.zhonghua.sdw.takkyuu.activity.PeopleDeatailActivity;
import com.zhonghua.sdw.takkyuu.data.model.MatchModel;
import com.zhonghua.sdw.takkyuu.data.model.MatchWrapper;
import com.zhonghua.sdw.takkyuu.data.model.UserCommentModel;
import com.zhonghua.sdw.takkyuu.data.model.UserInfoModel;
import com.zhonghua.sdw.takkyuu.data.model.UserModel;
import com.zhonghua.sdw.takkyuu.data.source.UserDataSource;
import com.zhonghua.sdw.takkyuu.data.source.UserRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by H2 on 2016/10/12.
 */
public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchHolder>{

    private final List<MatchModel> mValues;
    UserDataSource userDataSource;
    Context context;

    public MatchAdapter(Context context,List<MatchModel> matchModels){
        userDataSource = UserRepository.getInstance();
        this.mValues = matchModels;
        this.context = context;
    }


    @Override
    public MatchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_item, parent, false);
        return new MatchHolder(view);
    }

    @Override
    public void onBindViewHolder(final MatchHolder holder, final int position) {
        holder.item = mValues.get(position);
        Date matchTime = mValues.get(position).getMatchtime();
        if(matchTime!=null){
            holder.home_item_date.setText("参赛时间："+UTime.getTimeShowStringBest(matchTime));
        }else{
            holder.home_item_date.setText("暂无约定时间");
        }
        /**
         * 1 准备参加的比赛 5.邀请比赛 6.已经完结的比赛
         */
        int state = mValues.get(position).getMatchstate();

        final int user = mValues.get(position).getMatchfromuser();



        userDataSource.getUser(new BaseDataSource.BaseCallback<UserModel>() {
            @Override
            public void onSuccess(UserModel userModel) {
                final int userId;
                if(userModel.getUserid().equals(user)){
                    userId = mValues.get(position).getMatchtouser();
                }else{
                    userId = mValues.get(position).getMatchfromuser();
                }
                holder.home_item_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PeopleDeatailActivity.start(context,userId);
                    }
                });
                userDataSource.getUserInfo(userId, new BaseDataSource.BaseCallback<UserInfoModel>() {
                    @Override
                    public void onSuccess(UserInfoModel userInfoModel) {
                        holder.home_item_name.setText(userInfoModel.getUserinfoname());
                        holder.home_item_nickname.setText(userInfoModel.getUsernikename());
                        holder.home_item_space.setText(userInfoModel.getUserinfoflag());
                    }

                    @Override
                    public void onFailure(DataSourceException e) {

                    }
                });
            }

            @Override
            public void onFailure(DataSourceException e) {

            }
        });




    }
    public void addCommentModel(List<MatchModel> items){
        mValues.addAll(items);
        notifyDataSetChanged();
    }

    public void replace(List<MatchModel> items){
        mValues.clear();
        mValues.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class MatchHolder extends RecyclerView.ViewHolder{
        public final View mView;

        private MatchModel item;

        private final TextView home_item_name;
        private final TextView home_item_nickname;
        private final TextView home_item_space;
        private final TextView home_item_date;
        private final ImageView home_item_image;

        public MatchHolder(View itemView) {
            super(itemView);
            mView = itemView;
            home_item_date = (TextView) itemView.findViewById(R.id.home_item_date);
            home_item_name = (TextView) itemView.findViewById(R.id.home_item_name);
            home_item_nickname = (TextView) itemView.findViewById(R.id.home_item_nickname);
            home_item_space = (TextView) itemView.findViewById(R.id.home_item_space);
            home_item_image = (ImageView) itemView.findViewById(R.id.home_item_image);

        }
    }

}
