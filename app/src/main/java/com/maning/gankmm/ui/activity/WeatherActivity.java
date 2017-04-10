package com.maning.gankmm.ui.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.maning.gankmm.R;
import com.maning.gankmm.app.MyApplication;
import com.maning.gankmm.bean.WeatherBeseEntity;
import com.maning.gankmm.ui.adapter.WeatherAdapter;
import com.maning.gankmm.ui.base.BaseActivity;
import com.maning.gankmm.utils.StatusBarUtil;
import com.socks.library.KLog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.blurry.Blurry;

/**
 * 天气界面
 */
public class WeatherActivity extends BaseActivity implements OnRefreshListener {

    public static final String intentKey_weatherBean = "intentKey_weatherBean";
    public static final String intentKey_bg_url = "intentKey_bg_url";
    private static final String TAG = "WeatherActivity";
    private static final float defaultAlpha = 0.0f;
    private static final float maxAlpha = 1.0f;

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.swipe_target)
    RecyclerView swipeTarget;
    @Bind(R.id.iv_bg)
    ImageView ivBg;
    @Bind(R.id.iv_bg2)
    ImageView ivBg2;
    @Bind(R.id.ll_content)
    LinearLayout llContent;
    @Bind(R.id.ll_bg_blur)
    LinearLayout llBgBlur;
    @Bind(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

    private WeatherBeseEntity.WeatherBean weatherEntity;
    private WeatherAdapter weatherAdapter;

    private String bgPicUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        StatusBarUtil.setTranslucentForImageView(this, 80, llContent);

        initIntent();

        initViews();

        initAdapter();

        initBackgroundPic();

    }

    private void initBackgroundPic() {

        if (!TextUtils.isEmpty(bgPicUrl)) {

            Glide.with(this).load(bgPicUrl).asBitmap().centerCrop().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    ivBg.setImageBitmap(resource);
                    ivBg2.setImageBitmap(resource);
                    Blurry.with(WeatherActivity.this)
                            .radius(25)
                            .sampling(3)
                            .async()
                            .capture(ivBg2)
                            .into(ivBg2);
                    llBgBlur.setAlpha(defaultAlpha);
                }
            });


        }

    }

    private void initViews() {
        if (weatherEntity != null) {
            tvTitle.setText(weatherEntity.getCity());
        }

        //初始化RecycleView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        swipeTarget.setLayoutManager(linearLayoutManager);
        swipeTarget.setItemAnimator(new DefaultItemAnimator());
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeTarget.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int totalDy = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //静止,没有滚动
//                public static final int SCROLL_STATE_IDLE = 0;

                //正在被外部拖拽,一般为用户正在用手指滚动
//                public static final int SCROLL_STATE_DRAGGING = 1;

                //自动滚动开始
//                public static final int SCROLL_STATE_SETTLING = 2;
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int height = recyclerView.getHeight();
                totalDy -= dy;
                float alpha = Math.abs(totalDy) * maxAlpha / 700;
                if (alpha > maxAlpha) {
                    alpha = maxAlpha;
                }
                if (alpha < defaultAlpha) {
                    alpha = defaultAlpha;
                }
                KLog.i(TAG, "dy:" + dy + ",totalDy:" + totalDy + ",height:" + height + ",alpha:" + alpha);
                llBgBlur.setAlpha(alpha);
            }
        });

    }

    private void initIntent() {
        weatherEntity = (WeatherBeseEntity.WeatherBean) getIntent().getSerializableExtra(intentKey_weatherBean);
        bgPicUrl = getIntent().getStringExtra(intentKey_bg_url);
    }

    @OnClick(R.id.rl_back)
    public void backBtn() {
        this.finish();
    }

    private void initAdapter() {
        weatherAdapter = new WeatherAdapter(this, weatherEntity);
        swipeTarget.setAdapter(weatherAdapter);
    }

    @Override
    public void onRefresh() {
        MyApplication.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(false);
            }
        }, 3000);
    }
}
