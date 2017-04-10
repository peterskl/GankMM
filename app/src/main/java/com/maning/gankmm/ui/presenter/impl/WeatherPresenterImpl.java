package com.maning.gankmm.ui.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.maning.gankmm.bean.WeatherBeseEntity;
import com.maning.gankmm.http.GankApi;
import com.maning.gankmm.http.MyCallBack;
import com.maning.gankmm.ui.iView.IWeatherView;
import com.maning.gankmm.ui.presenter.IWeatherPresenter;

import java.util.List;

/**
 * Created by maning on 2017/4/10.
 */

public class WeatherPresenterImpl extends BasePresenterImpl<IWeatherView> implements IWeatherPresenter {

    private Context context;

    private MyCallBack httpCallBack = new MyCallBack() {
        @Override
        public void onSuccessList(int what, List results) {
            mView.overRefresh();
            if (mView == null) {
                return;
            }
            if (results == null) {
                return;
            }
            switch (what) {
                case 0x003:
                    List<WeatherBeseEntity.WeatherBean> weathers = results;
                    if (weathers.size() > 0) {
                        WeatherBeseEntity.WeatherBean resultBean = weathers.get(0);
                        if (resultBean != null) {
                            mView.initWeatherInfo(resultBean);
                        }
                    }
                    break;
            }
        }

        @Override
        public void onSuccess(int what, Object result) {
        }

        @Override
        public void onFail(int what, String result) {
            mView.overRefresh();
            if (!TextUtils.isEmpty(result)) {
                mView.showToast(result);
            }
        }
    };

    public WeatherPresenterImpl(Context context, IWeatherView iWeatherView) {
        this.context = context;
        attachView(iWeatherView);
    }

    @Override
    public void getCityWeather(String provinceName, String cityName) {
        GankApi.getCityWeather(cityName, provinceName, 0x003, httpCallBack);
    }
}
