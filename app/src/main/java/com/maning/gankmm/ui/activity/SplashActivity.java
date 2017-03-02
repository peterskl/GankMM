package com.maning.gankmm.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.maning.gankmm.R;
import com.maning.gankmm.app.MyApplication;
import com.maning.gankmm.skin.SkinManager;
import com.umeng.analytics.MobclickAgent;

import net.youmi.android.AdManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;

public class SplashActivity extends Activity {

    @Bind(R.id.tv_app_version)
    TextView tv_app_version;
    @Bind(R.id.shade_bg)
    TextView shadeBg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        int currentSkinType = SkinManager.getCurrentSkinType(this);
        if (currentSkinType == SkinManager.THEME_NIGHT) {
            shadeBg.setVisibility(View.VISIBLE);
        }

        MyApplication.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, SplashAdActivity.class));
                SplashActivity.this.finish();
            }
        }, 2000);

        tv_app_version.setText(String.valueOf("V " + MyApplication.getVersionName()));


        initYoumiSDK();

    }

    private void initYoumiSDK() {
        //初始化
        AdManager.getInstance(this).init("f990efa85f85257b", "7a55d045a3ab5fe6", true, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
        MobclickAgent.onPageStart("CodesActivity");
        MobclickAgent.onResume(this);       //统计时长
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
        MobclickAgent.onPageStart("CodesActivity");
        MobclickAgent.onPause(this);
    }

}
