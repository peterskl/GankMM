package com.maning.gankmm.ui.iView;

import com.maning.gankmm.bean.AppUpdateInfo;
import com.maning.gankmm.bean.WeatherEntity;

/**
 * Created by maning on 16/6/21.
 */
public interface IMainView {

    void showFeedBackDialog();

    void showAppUpdateDialog(AppUpdateInfo appUpdateInfo);

    void initWeatherInfo(WeatherEntity.ResultBean weatherEntity,String cityName);

}
