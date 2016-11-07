package com.mianbaopai.sdw.imsystem.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.drcosu.ndileber.tools.permissions.PermissionsManager;
import com.drcosu.ndileber.utils.ActivityUtils;
import com.zhonghua.sdw.takkyuu.R;
import com.hyphenate.util.EasyUtils;

/**
 * chat activity，EaseChatFragment was used {@link #EaseChatFragment}
 *
 */
public class ChatActivity extends MBaseAcitvity{
    public static ChatActivity activityInstance;
    private ChatFragment chatFragment;
    public String toChatUsername;
    public String toChatUsernameName;

    public static void start(Context context,Intent it,String userId,String username){
        it.putExtra("userId", userId);
        it.putExtra("userName", username);
        it.setClass(context,ChatActivity.class);
        context.startActivity(it);
    }


    @Override
    protected int layoutViewId() {
        return R.layout.em_activity_chat;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        activityInstance = this;
        //get user id or group id
        toChatUsername = getIntent().getExtras().getString("userId");
        toChatUsernameName = getIntent().getExtras().getString("userName");
        //use EaseChatFratFragment
        chatFragment = ActivityUtils.getFragment(getSupportFragmentManager(),R.id.container, ChatFragment.newInstance(toChatUsername,toChatUsernameName));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityInstance = null;
    }
    
    @Override
    protected void onNewIntent(Intent intent) {
    	// make sure only one chat activity is opened
        String username = intent.getStringExtra("userId");
        if (toChatUsername.equals(username))
            super.onNewIntent(intent);
        else {
            finish();
            startActivity(intent);
        }

    }
    
    @Override
    public void onBackPressed() {
        chatFragment.onBackPressed();
        if (EasyUtils.isSingleActivity(this)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
    
    public String getToChatUsername(){
        return toChatUsername;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }
}
