package com.zhonghua.sdw.takkyuu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.fragment.EmojiFragment;
import com.zhonghua.sdw.takkyuu.R;
import com.zhonghua.sdw.takkyuu.activity.PeopleDeatailActivity;
import com.zhonghua.sdw.takkyuu.data.model.UserCommentModel;
import com.zhonghua.sdw.takkyuu.data.model.UserInfoModel;
import com.zhonghua.sdw.takkyuu.data.source.UserDataSource;
import com.zhonghua.sdw.takkyuu.data.source.UserRepository;

import java.util.List;

/**
 * Created by shidawei on 2016/10/11.
 */
public class PeopleCommentAdapter extends RecyclerView.Adapter<PeopleCommentAdapter.CommentHolder>{

    private final List<UserCommentModel> mValues;
    UserDataSource userDataSource;
    Context context;


    public PeopleCommentAdapter(Context context,List<UserCommentModel> items) {
        userDataSource = UserRepository.getInstance();
        mValues = items;
        this.context = context;
    }

    public void addCommentModel(List<UserCommentModel> items){
        mValues.addAll(items);
        notifyDataSetChanged();
    }

    public void replace(List<UserCommentModel> items){
        mValues.clear();
        mValues.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment, parent, false);
        return new CommentHolder(view);
    }

    @Override
    public void onBindViewHolder(final CommentHolder holder, int position) {
        holder.item = mValues.get(position);
        holder.people_comment.setText( mValues.get(position).getCommentcontext());
        userDataSource.getUserInfo(mValues.get(position).getCommentfromuserid(), new BaseDataSource.BaseCallback<UserInfoModel>() {
            @Override
            public void onSuccess(final UserInfoModel userInfoModel) {
                holder.people_name.setText(userInfoModel.getUserinfoname());
                holder.people_comment_user.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PeopleDeatailActivity.start(context,userInfoModel.getUid());
                    }
                });
            }

            @Override
            public void onFailure(DataSourceException e) {

            }
        });
    }

    @Override
    public int getItemCount() {
        if(mValues==null){
            return 0;
        }else {
            return mValues.size();
        }

    }

    class CommentHolder extends RecyclerView.ViewHolder{
        public final View mView;

        private UserCommentModel item;
        private final TextView people_name;
        private final TextView people_comment;
        private final LinearLayout people_comment_user;

        public CommentHolder(View itemView) {
            super(itemView);
            mView = itemView;
            people_comment = (TextView) itemView.findViewById(R.id.people_comment);
            people_name = (TextView) itemView.findViewById(R.id.people_name);
            people_comment_user = (LinearLayout) itemView.findViewById(R.id.people_comment_user);
        }
    }

}
