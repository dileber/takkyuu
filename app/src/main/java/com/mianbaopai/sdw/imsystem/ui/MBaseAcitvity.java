package com.mianbaopai.sdw.imsystem.ui;

import com.hyphenate.easeui.ui.EaseBaseActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by H2 on 2016/9/13.
 */
public abstract class MBaseAcitvity extends EaseBaseActivity {
    @Override
    protected void onResume() {
        super.onResume();
        // umeng
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // umeng
        MobclickAgent.onPause(this);
    }
}
