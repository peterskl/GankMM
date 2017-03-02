package com.maning.gankmm.ui.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.maning.gankmm.R;
import com.socks.library.KLog;

import net.youmi.android.normal.common.ErrorCode;
import net.youmi.android.normal.spot.SplashViewSettings;
import net.youmi.android.normal.spot.SpotListener;
import net.youmi.android.normal.spot.SpotManager;
import net.youmi.android.normal.video.VideoAdManager;

public class SplashAdActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_ad);

        mContext = this;

        //开屏广告
        setupSplashAd();

        checkAdSettings();

    }

    /**
     * 检查广告配置
     */
    private void checkAdSettings() {
        boolean result = SpotManager.getInstance(this).checkSpotAdConfig();
        KLog.e("youmi配置 :"+result);
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
}
