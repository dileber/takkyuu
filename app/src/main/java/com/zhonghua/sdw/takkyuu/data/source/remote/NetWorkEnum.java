package com.zhonghua.sdw.takkyuu.data.source.remote;

import com.facebook.imagepipeline.producers.NetworkFetchProducer;

/**
 * Created by shidawei on 16/9/20.
 */
public enum  NetWorkEnum {

    Error(-1),Success(0);

    int var;
    private NetWorkEnum(int state){
        var = state;
    }

    public int getVar() {
        return var;
    }
}
