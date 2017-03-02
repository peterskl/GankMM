package com.maning.gankmm.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.maning.gankmm.R;
import com.maning.gankmm.skin.SkinManager;
import com.maning.gankmm.ui.base.BaseActivity;
import com.maning.gankmm.utils.MyToast;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import net.youmi.android.normal.banner.BannerManager;
import net.youmi.android.normal.banner.BannerViewListener;
import net.youmi.android.normal.common.ErrorCode;
import net.youmi.android.normal.spot.SpotListener;
import net.youmi.android.normal.spot.SpotManager;
import net.youmi.android.normal.video.VideoAdListener;
import net.youmi.android.normal.video.VideoAdManager;
import net.youmi.android.normal.video.VideoAdSettings;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 广告页面
 */
public class AdActivity extends BaseActivity {

    private static final String TAG = "AdActivity";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.btn_ad_01)
    Button btnAd01;
    @Bind(R.id.btn_ad_02)
    Button btnAd02;
    @Bind(R.id.btn_ad_03)
    Button btnAd03;
    @Bind(R.id.ll_main_banner)
    LinearLayout llMainBanner;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        ButterKnife.bind(this);
        mContext = this;

        initMyToolBar();

        //请求权限
        requestSomePermission();

        //预加载
        preloadData();

        //广告条
        setupBannerAd();

        // 设置插屏广告
        setupSpotAd();

    }

    @OnClick(R.id.btn_ad_01)
    void btn_ad_01() {
        // 设置插屏广告
        setupSpotAd();
    }

    @OnClick(R.id.btn_ad_02)
    void btn_ad_02() {
        // 设置视频广告
        setupSlideableSpotAd();
    }

    @OnClick(R.id.btn_ad_03)
    void btn_ad_03() {
        // 设置视频广告
        setupVideoAd();
    }


    /**
     * 预加载数据
     */
    private void preloadData() {
        // 设置服务器回调 userId，一定要在请求广告之前设置，否则无效
        VideoAdManager.getInstance(mContext).setUserId("f990efa85f85257b");
        // 请求视频广告
        VideoAdManager.getInstance(mContext).requestVideoAd(mContext);
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

    /**
     * 设置轮播插屏广告
     */
    private void setupSlideableSpotAd() {
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

        // 展示轮播插屏广告
        SpotManager.getInstance(mContext)
                .showSlideableSpot(mContext, new SpotListener() {

                    @Override
                    public void onShowSuccess() {
                        KLog.e("轮播插屏展示成功");
                    }

                    @Override
                    public void onShowFailed(int errorCode) {
                        KLog.e("轮播插屏展示失败");
                        switch (errorCode) {
                            case ErrorCode.NON_NETWORK:
                                showShortToast("网络异常");
                                break;
                            case ErrorCode.NON_AD:
                                showShortToast("暂无轮播插屏广告");
                                break;
                            case ErrorCode.RESOURCE_NOT_READY:
                                showShortToast("轮播插屏资源还没准备好");
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
                        KLog.e("轮播插屏被关闭");
                    }

                    @Override
                    public void onSpotClicked(boolean isWebPage) {
                        KLog.e("轮播插屏被点击");
                        KLog.e("是否是网页广告？%s", isWebPage ? "是" : "不是");
                    }
                });

    }

    private void showShortToast(String msg) {
        MyToast.showShortToast(msg);
    }


    @Override
    public void onBackPressed() {
        // 点击后退关闭轮播插屏广告
        if (SpotManager.getInstance(mContext).isSlideableSpotShowing()) {
            SpotManager.getInstance(mContext).hideSlideableSpot();
            return;
        }
        // 点击后退关闭插屏广告
        if (SpotManager.getInstance(mContext).isSpotShowing()) {
            SpotManager.getInstance(mContext).hideSpot();
            return;
        }
        super.onBackPressed();

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
        // 展示广告条窗口的 onDestroy() 回调方法中调用
        BannerManager.getInstance(mContext).onDestroy();
    }


    /**
     * 设置视频广告
     */
    private void setupVideoAd() {
        // 设置视频广告
        final VideoAdSettings videoAdSettings = new VideoAdSettings();
        videoAdSettings.setInterruptTips("退出视频播放将无法获得奖励" + "\n确定要退出吗？");

        // 展示视频广告
        VideoAdManager.getInstance(mContext)
                .showVideoAd(mContext, videoAdSettings, new VideoAdListener() {
                    @Override
                    public void onPlayStarted() {
                        KLog.e("开始播放视频");
                    }

                    @Override
                    public void onPlayInterrupted() {
                        showShortToast("播放视频被中断");
                    }

                    @Override
                    public void onPlayFailed(int errorCode) {
                        KLog.e("视频播放失败");
                        switch (errorCode) {
                            case ErrorCode.NON_NETWORK:
                                KLog.e("网络异常");
                                break;
                            case ErrorCode.NON_AD:
                                KLog.e("视频暂无广告");
                                break;
                            case ErrorCode.RESOURCE_NOT_READY:
                                KLog.e("视频资源还没准备好");
                                break;
                            case ErrorCode.SHOW_INTERVAL_LIMITED:
                                KLog.e("视频展示间隔限制");
                                break;
                            case ErrorCode.WIDGET_NOT_IN_VISIBILITY_STATE:
                                KLog.e("视频控件处在不可见状态");
                                break;
                            default:
                                KLog.e("请稍后再试");
                                break;
                        }
                    }

                    @Override
                    public void onPlayCompleted() {
                        showShortToast("视频播放成功");
                    }
                });

    }

    /**
     * 设置广告条广告
     */
    private void setupBannerAd() {
        //		/**
        //		 * 普通布局
        //		 */
        //		// 获取广告条
        //		View bannerView = BannerManager.getInstance(mContext)
        //				.getBannerView(mContext, new BannerViewListener() {
        //					@Override
        //					public void onRequestSuccess() {
        //						logInfo("请求广告条成功");
        //					}
        //
        //					@Override
        //					public void onSwitchBanner() {
        //						logDebug("广告条切换");
        //					}
        //
        //					@Override
        //					public void onRequestFailed() {
        //						logError("请求广告条失败");
        //					}
        //				});
        //		// 实例化广告条容器
        //		LinearLayout bannerLayout = (LinearLayout) findViewById(R.id.ll_banner);
        //		// 添加广告条到容器中
        //		bannerLayout.addView(bannerView);

        /**
         * 悬浮布局
         */
        // 实例化LayoutParams
        FrameLayout.LayoutParams layoutParams =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置广告条的悬浮位置，这里示例为右下角
        layoutParams.gravity = Gravity.BOTTOM | Gravity.RIGHT;
        // 获取广告条
        final View bannerView = BannerManager.getInstance(mContext)
                .getBannerView(mContext, new BannerViewListener() {

                    @Override
                    public void onRequestSuccess() {
                        KLog.e("请求广告条成功");

                    }

                    @Override
                    public void onSwitchBanner() {
                        KLog.e("广告条切换");
                    }

                    @Override
                    public void onRequestFailed() {
                        KLog.e("请求广告条失败");
                    }
                });
        // 添加广告条到窗口中
        ((Activity) mContext).addContentView(bannerView, layoutParams);
    }


    private void requestSomePermission() {

        // 先判断是否有权限。
        if (!AndPermission.hasPermission(AdActivity.this, Manifest.permission.READ_PHONE_STATE)) {
            // 申请权限。
            AndPermission.with(AdActivity.this)
                    .requestCode(100)
                    .permission(Manifest.permission.READ_PHONE_STATE)
                    .send();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 只需要调用这一句，其它的交给AndPermission吧，最后一个参数是PermissionListener。
        AndPermission.onRequestPermissionsResult(requestCode, permissions, grantResults, listener);
    }

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            //广告条
            setupBannerAd();
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
            if (AndPermission.hasAlwaysDeniedPermission(AdActivity.this, deniedPermissions)) {
                // 第二种：用自定义的提示语。
                AndPermission.defaultSettingDialog(AdActivity.this, 300)
                        .setTitle("权限申请失败")
                        .setMessage("我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！")
                        .setPositiveButton("好，去设置")
                        .show();
            }
        }
    };

}
