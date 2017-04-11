package com.maning.gankmm.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maning.gankmm.R;
import com.maning.gankmm.skin.SkinManager;
import com.maning.gankmm.ui.base.BaseActivity;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;

import net.youmi.android.normal.common.ErrorCode;
import net.youmi.android.normal.spot.SplashViewSettings;
import net.youmi.android.normal.spot.SpotListener;
import net.youmi.android.normal.spot.SpotManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;

public class SplashAdActivity extends BaseActivity {

    @Bind(R.id.shade_bg)
    TextView shadeBg;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_ad);
        ButterKnife.bind(this);

        mContext = this;

        //夜间模式
        int currentSkinType = SkinManager.getCurrentSkinType(this);
        if (currentSkinType == SkinManager.THEME_NIGHT) {
            shadeBg.setVisibility(View.VISIBLE);
        }

        //开屏广告
        setupSplashAd();

        checkAdSettings();

    }

    /**
     * 检查广告配置
     */
    private void checkAdSettings() {
        boolean result = SpotManager.getInstance(this).checkSpotAdConfig();
        KLog.e("youmi配置 :" + result);
    }


    /**
     * 设置开屏广告
     */
    private void setupSplashAd() {
        // 创建开屏容器
        final RelativeLayout splashLayout = (RelativeLayout) findViewById(R.id.rl_splash);
        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.ABOVE, R.id.view_divider);

        // 对开屏进行设置
        SplashViewSettings splashViewSettings = new SplashViewSettings();
        // 设置是否展示失败自动跳转，默认自动跳转
        splashViewSettings.setAutoJumpToTargetWhenShowFailed(true);
        // 设置跳转的窗口类
        splashViewSettings.setTargetClass(MainActivity.class);
        // 设置开屏的容器
        splashViewSettings.setSplashViewContainer(splashLayout);

        // 展示开屏广告
        SpotManager.getInstance(mContext)
                .showSplash(mContext, splashViewSettings, new SpotListener() {

                    @Override
                    public void onShowSuccess() {
                        KLog.e("Youmi开屏展示成功");
                    }

                    @Override
                    public void onShowFailed(int errorCode) {
                        KLog.e("Youmi开屏展示失败");
                        switch (errorCode) {
                            case ErrorCode.NON_NETWORK:
                                KLog.e("Youmi网络异常");
                                break;
                            case ErrorCode.NON_AD:
                                KLog.e("Youmi暂无开屏广告");
                                break;
                            case ErrorCode.RESOURCE_NOT_READY:
                                KLog.e("Youmi开屏资源还没准备好");
                                break;
                            case ErrorCode.SHOW_INTERVAL_LIMITED:
                                KLog.e("Youmi开屏展示间隔限制");
                                break;
                            case ErrorCode.WIDGET_NOT_IN_VISIBILITY_STATE:
                                KLog.e("Youmi开屏控件处在不可见状态");
                                break;
                            default:
                                KLog.e("YoumierrorCode: %d", errorCode);
                                break;
                        }
                    }

                    @Override
                    public void onSpotClosed() {
                        KLog.e("Youmi开屏被关闭");
                    }

                    @Override
                    public void onSpotClicked(boolean isWebPage) {
                        KLog.e("Youmi开屏被点击");
                        KLog.e("Youmi是否是网页广告？%s", isWebPage ? "是" : "不是");
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 开屏展示界面的 onDestroy() 回调方法中调用
        SpotManager.getInstance(this).onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}
