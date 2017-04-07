package com.maning.gankmm.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maning.gankmm.R;
import com.maning.gankmm.bean.WeatherBeseEntity;
import com.maning.gankmm.ui.adapter.WeatherAdapter;
import com.maning.gankmm.ui.base.BaseActivity;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 天气界面
 */
public class WeatherActivity extends BaseActivity {

    @Bind(R.id.rl_back)
    RelativeLayout rlBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.swipe_target)
    RecyclerView swipeTarget;

    public static final String intentKey_weatherBean = "intentKey_weatherBean";
    private WeatherBeseEntity.WeatherBean weatherEntity;
    private WeatherAdapter weatherAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        initIntent();

        initViews();

        initAdapter();

    }

    private void initViews() {
        if (weatherEntity != null) {
            tvTitle.setText(weatherEntity.getCity());
        }

        //初始化RecycleView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        swipeTarget.setLayoutManager(linearLayoutManager);
        swipeTarget.setItemAnimator(new DefaultItemAnimator());

    }

    private void initIntent() {
        weatherEntity = (WeatherBeseEntity.WeatherBean) getIntent().getSerializableExtra(intentKey_weatherBean);
    }

    @OnClick(R.id.rl_back)
    public void backBtn() {
        this.finish();
    }

    private void initAdapter() {
        weatherAdapter = new WeatherAdapter(this, weatherEntity);
        swipeTarget.setAdapter(weatherAdapter);
    }

}
