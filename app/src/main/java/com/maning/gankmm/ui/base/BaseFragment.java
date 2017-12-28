package com.maning.gankmm.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.maning.mndialoglibrary.MProgressDialog;
import com.maning.mndialoglibrary.MStatusDialog;
import com.umeng.analytics.MobclickAgent;

public class BaseFragment extends Fragment {

    //统计名字判断
    public String className;

    private MStatusDialog mStatusDialog;
    private MProgressDialog mProgressDialog;


    public Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        className = this.getClass().getSimpleName();

        context = getActivity();

        initDialog();

    }


    private void initDialog() {
        //新建一个Dialog
        mProgressDialog = new MProgressDialog.Builder(context)
                .build()
        ;
    }

    public void showProgressDialog() {
        dissmissProgressDialog();
        mProgressDialog.show();
    }

    public void dissmissProgressDialog() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //CategoryFragment 内嵌套Fragment,统计子页面
        if (!"CategoryFragment".equals(className)) {
            MobclickAgent.onPageStart(className);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //CategoryFragment 内嵌套Fragment,统计子页面
        if (!"CategoryFragment".equals(className)) {
            MobclickAgent.onPageEnd(className);
        }

    }

}
