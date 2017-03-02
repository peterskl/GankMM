package com.maning.gankmm.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.maning.gankmm.R;
import com.maning.gankmm.skin.SkinManager;
import com.maning.gankmm.ui.base.BaseActivity;
import com.maning.gankmm.utils.MyToast;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;

import net.youmi.android.normal.common.ErrorCode;
import net.youmi.android.normal.spot.SpotListener;
import net.youmi.android.normal.spot.SpotManager;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 广告页面
 */
public class AdActivity extends BaseActivity {

    private static final String TAG = "AdActivity";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        ButterKnife.bind(this);
        mContext = this;

        initMyToolBar();

        // 设置插屏广告
        setupSpotAd();
    }


    private void initMyToolBar() {
        int currentSkinType = SkinManager.getCurrentSkinType(mContext);
        if (SkinManager.THEME_DAY == currentSkinType) {
            initToolBar(toolbar, "广告", R.drawable.icon_arrow_back);
        } else {
            initToolBar(toolbar, "广告", R.drawable.icon_arrow_back_night);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 设置插屏广告
     */
    private void setupSpotAd() {
        // 设置插屏图片类型，默认竖图
        //		// 横图
        //		SpotManager.getInstance(mContext).setImageType(SpotManager
        // .IMAGE_TYPE_HORIZONTAL);
        // 竖图
        SpotManager.getInstance(mContext).setImageType(SpotManager.IMAGE_TYPE_VERTICAL);

        // 设置动画类型，默认高级动画
        //		// 无动画
        //		SpotManager.getInstance(mContext).setAnimationType(SpotManager
        // .ANIMATION_TYPE_NONE);
        //		// 简单动画
        //		SpotManager.getInstance(mContext).setAnimationType(SpotManager
        // .ANIMATION_TYPE_SIMPLE);
        // 高级动画
        SpotManager.getInstance(mContext)
                .setAnimationType(SpotManager.ANIMATION_TYPE_ADVANCED);


        // 展示插屏广告
        SpotManager.getInstance(mContext).showSpot(mContext, new SpotListener() {

            @Override
            public void onShowSuccess() {
                KLog.e("Youmi插屏展示成功");
            }

            @Override
            public void onShowFailed(int errorCode) {
                KLog.e("Youmi插屏展示失败");
                switch (errorCode) {
                    case ErrorCode.NON_NETWORK:
                        showShortToast("网络异常");
                        break;
                    case ErrorCode.NON_AD:
                        showShortToast("暂无插屏广告");
                        break;
                    case ErrorCode.RESOURCE_NOT_READY:
                        showShortToast("插屏资源还没准备好");
                        break;
                    case ErrorCode.SHOW_INTERVAL_LIMITED:
                        showShortToast("请勿频繁展示");
                        break;
                    case ErrorCode.WIDGET_NOT_IN_VISIBILITY_STATE:
                        showShortToast("请设置插屏为可见状态");
                        break;
                    default:
                        showShortToast("请稍后再试");
                        break;
                }
            }

            @Override
            public void onSpotClosed() {
                KLog.e("Youmi插屏被关闭");
            }

            @Override
            public void onSpotClicked(boolean isWebPage) {
                KLog.e("Youmi插屏被点击");
                KLog.e("Youmi是否是网页广告？%s", isWebPage ? "是" : "不是");
            }
        });
    }

    private void showShortToast(String msg) {
        MyToast.showShortToast(msg);
    }


    @Override
    public void onBackPressed() {
        // 点击后退关闭插屏广告
        if (SpotManager.getInstance(mContext).isSpotShowing()) {
            SpotManager.getInstance(mContext).hideSpot();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG);
        MobclickAgent.onResume(this);       //统计时长
    }


    @Override
    protected void onPause() {
        super.onPause();
        // 插屏广告
        SpotManager.getInstance(mContext).onPause();
        MobclickAgent.onPageStart(TAG);
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 插屏广告
        SpotManager.getInstance(mContext).onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 插屏广告
        SpotManager.getInstance(mContext).onDestroy();
    }
}
