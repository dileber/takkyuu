package com.zhonghua.sdw.takkyuu.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.drcosu.ndileber.mvp.fragment.BaseFragment;
import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BView;
import com.drcosu.ndileber.tools.DialogLinstener;
import com.drcosu.ndileber.tools.SFont;
import com.drcosu.ndileber.tools.UDialog;
import com.drcosu.ndileber.tools.UUi;
import com.orhanobut.logger.Logger;

/**
 * Created by shidawei on 2016/9/24.
 */
public abstract class TakkyuuBaseFragment extends BaseFragment implements BView{

    protected BasePresenter presenter;

    @Override
    public void toast(String msg, int duration) {
        UUi.toast(getActivity(),msg,duration);
    }

    @Override
    public void showAlert(Integer type, String message) {
        Logger.d("显示dialog");
        UDialog.alert(type,message).show();

    }

    Dialog dialog;

    @Override
    public void loading() {
        if(dialog==null){
            dialog =UDialog.loading();
        }
        dialog.show();
    }

    @Override
    public void loadDialogDismiss() {
        if(dialog!=null){
            dialog.dismiss();
        }
    }

    @Override
    public void dialogOk(String content, DialogLinstener dialogLinstener) {

    }

    @Override
    public Context getContext() {
        return getActivity();
    }


    @Override
    public void onResume() {
        super.onResume();
        if(presenter!=null){
            presenter.start();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.onDestroy();
        }
        if(dialog!=null){
            dialog.dismiss();
        }
    }

    public void setPresenter(BasePresenter presenter) {
        this.presenter = presenter;
    }

    protected OnFragmentInteractionListener mListener;

    public interface OnFragmentInteractionListener {
        SFont onRightButtonFont(Integer res);
        void onTitleName(String title);
    }

    public SFont setRightButtonFont(Integer res) {
        if (mListener != null) {
            return mListener.onRightButtonFont(res);
        }
        return null;
    }

    public void setTitle(String title){
        if (mListener != null) {
            mListener.onTitleName(title);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
