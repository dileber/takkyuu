package com.zhonghua.sdw.takkyuu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.zhonghua.sdw.takkyuu.R;
import com.zhonghua.sdw.takkyuu.data.model.FollowsModel;
import com.zhonghua.sdw.takkyuu.data.model.UserInfoModel;
import com.zhonghua.sdw.takkyuu.data.source.UserRepository;

import java.util.List;

/**
 * Created by H2 on 2016/10/12.
 */
public class ConsAdapter extends RecyclerView.Adapter<ConsAdapter.ConsHolder>{

    List<FollowsModel> userInfoModels;
    Context context;

    public ConsAdapter(Context context,List<FollowsModel> userInfoModels){
        this.context = context;
        this.userInfoModels = userInfoModels;
    }

    public void addUserInfoModel(List<FollowsModel> items){
        userInfoModels.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public ConsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_people, parent, false);
        return new ConsHolder(view);
    }

    @Override
    public void onBindViewHolder(final ConsHolder holder, final int position) {
        holder.item = userInfoModels.get(position);
        int userId = userInfoModels.get(position).getFollowstouserid();
        UserRepository.getInstance().getUserInfo(userId, new BaseDataSource.BaseCallback<UserInfoModel>() {
            @Override
            public void onSuccess(UserInfoModel userInfoModel) {
                holder.item_people_flag.setText(userInfoModel.getUserinfoflag());
                holder.item_people_name.setText(userInfoModel.getUserinfoname());
            }

            @Override
            public void onFailure(DataSourceException e) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return userInfoModels.size();
    }

    class ConsHolder extends RecyclerView.ViewHolder{
        private FollowsModel item;

        private final TextView item_people_name;
        private final TextView item_people_flag;
        private final TextView item_people_local;
        private final TextView item_people_sendmatch;
        private final View mView;
        public ConsHolder(View itemView) {
            super(itemView);
            mView = itemView;
            item_people_flag = (TextView) itemView.findViewById(R.id.item_people_flag);
            item_people_name = (TextView) itemView.findViewById(R.id.item_people_name);
            item_people_local = (TextView) itemView.findViewById(R.id.item_people_local);
            item_people_sendmatch = (TextView) itemView.findViewById(R.id.item_people_sendmatch);
        }
    }

}
