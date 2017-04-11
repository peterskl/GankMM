package com.maning.gankmm.http;

import com.maning.gankmm.bean.AppUpdateInfo;
import com.maning.gankmm.bean.CalendarInfoEntity;
import com.maning.gankmm.bean.CitysEntity;
import com.maning.gankmm.bean.DayEntity;
import com.maning.gankmm.bean.GankEntity;
import com.maning.gankmm.bean.HttpResult;
import com.maning.gankmm.bean.MobBaseEntity;
import com.maning.gankmm.bean.RandomEntity;
import com.maning.gankmm.bean.SearchBean;
import com.maning.gankmm.bean.WeatherBeseEntity;
import com.maning.gankmm.constant.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * 接口调用的工具类
 */
public interface APIService {

    //这里填写全部路径就会覆盖掉Build得BaseUrl
    @Headers("Cache-Control: public, max-age=3600")
    @GET(Constants.URL_HistoryDate)
    Call<HttpResult<List<String>>> getGankHistoryDate();

    //http://gank.io/api/data/Android/10/1
    @Headers("Cache-Control: public, max-age=120")
    @GET("data/{type}/{count}/{pageIndex}")
    Call<HttpResult<List<GankEntity>>> getCommonDateNew(@Path("type") String type,
                                                        @Path("count") int count,
                                                        @Path("pageIndex") int pageIndex
    );

    //http://gank.io/api/day/2015/08/06 --- 每日数据
    @Headers("Cache-Control: public, max-age=300")
    @GET("day/{year}/{month}/{day}")
    Call<DayEntity> getOneDayData(@Path("year") String year,
                                  @Path("month") String month,
                                  @Path("day") String day
    );

    //http://gank.io/api/random/data/Android/5 --- 随机数据
    @Headers("Cache-Control: public, max-age=300")
    @GET("random/data/{type}/{count}")
    Call<RandomEntity> getRandomDatas(@Path("type") String type,
                                      @Path("count") int count
    );

    //获取fir.im中的GankMM的最新版本
    @Headers("Cache-Control: public, max-age=3600")
    @GET(Constants.URL_AppUpdateInfo)
    Call<AppUpdateInfo> getTheLastAppInfo();


    //搜索
    //http://gank.io/api/search/query/listview/category/Android/count/10/page/1
    @Headers("Cache-Control: public, max-age=120")
    @GET("search/query/{keyword}/category/{type}/count/{count}/page/{pageIndex}")
    Call<HttpResult<List<SearchBean>>> getSearchData(@Path("keyword") String keyword,
                                                     @Path("type") String type,
                                                     @Path("count") int count,
                                                     @Path("pageIndex") int pageIndex
    );

    //获取天气信息
    @Headers("Cache-Control: public, max-age=300")
    @GET(Constants.URL_Mob + "v1/weather/query")
    Call<WeatherBeseEntity> getCityWeather(@Query("key") String appkey,
                                           @Query("city") String city,
                                           @Query("province") String province
    );

    //城市列表查询接口
    //http://apicloud.mob.com/v1/weather/citys?key=appkey
    @Headers("Cache-Control: public, max-age=300")
    @GET(Constants.URL_Mob + "v1/weather/citys")
    Call<CitysEntity> getCitys(@Query("key") String appkey
    );

    //万年历查询
    //http://apicloud.mob.com/appstore/calendar/day?key=appkey&date=2015-05-01
    @Headers("Cache-Control: public, max-age=300")
    @GET(Constants.URL_Mob + "appstore/calendar/day")
    Call<MobBaseEntity<CalendarInfoEntity>> getCalendarInfo(@Query("key") String appkey,
                                                            @Query("date") String date
    );


}
