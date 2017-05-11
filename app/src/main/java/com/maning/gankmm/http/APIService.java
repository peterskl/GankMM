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
import com.maning.gankmm.bean.mob.MobBankCard;
import com.maning.gankmm.bean.mob.MobIdCardEntity;
import com.maning.gankmm.bean.mob.MobIpEntity;
import com.maning.gankmm.bean.mob.MobOilPriceEntity;
import com.maning.gankmm.bean.mob.MobPhoneAddressEntity;
import com.maning.gankmm.bean.mob.MobPostCodeEntity;
import com.maning.gankmm.bean.mob.MobWxArticleListEntity;
import com.maning.gankmm.bean.mob.MobWxCategoryEntity;
import com.maning.gankmm.constant.Constants;

import java.util.ArrayList;
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

    //手机号码归属地查询
    //http://apicloud.mob.com/v1/mobile/address/query?phone=xxxx&key=1c9dccb9a2434
    @GET(Constants.URL_Mob + "v1/mobile/address/query")
    Call<MobBaseEntity<MobPhoneAddressEntity>> queryMobileAddress(@Query("key") String appkey,
                                                                  @Query("phone") String phone
    );

    //邮编查询
    //http://apicloud.mob.com/v1/postcode/query?code=102629&key=1c9dccb9a2434
    @GET(Constants.URL_Mob + "v1/postcode/query")
    Call<MobBaseEntity<MobPostCodeEntity>> queryPostCode(@Query("key") String appkey,
                                                         @Query("code") String code
    );

    //身份证查询
    //http://apicloud.mob.com/idcard/query?cardno=xxx&key=1c9dccb9a2434
    @GET(Constants.URL_Mob + "idcard/query")
    Call<MobBaseEntity<MobIdCardEntity>> queryIdcard(@Query("key") String appkey,
                                                     @Query("cardno") String cardno
    );

    //IP查询
    //http://apicloud.mob.com/ip/query?key=1c9dccb9a2434&ip=123.123.123.123
    @GET(Constants.URL_Mob + "ip/query")
    Call<MobBaseEntity<MobIpEntity>> queryIp(@Query("key") String appkey,
                                             @Query("ip") String ip
    );

    //微信精选分类查询
    //http://apicloud.mob.com/wx/article/category/query?key=123456
    @GET(Constants.URL_Mob + "wx/article/category/query")
    Call<MobBaseEntity<ArrayList<MobWxCategoryEntity>>> queryWxArticleCategory(@Query("key") String appkey
    );

    //微信精选列表查询
    //http://apicloud.mob.com/wx/article/search?key=123456&cid=1
    @GET(Constants.URL_Mob + "wx/article/search")
    Call<MobBaseEntity<MobWxArticleListEntity>> queryWxArticleList(@Query("key") String appkey,
                                                                   @Query("cid") String cid,
                                                                   @Query("page") int page,
                                                                   @Query("size") int size
    );

    /*金融基金*/

    //银行卡信息查询
    //http://apicloud.mob.com/appstore/bank/card/query?card=6228482898203884775&key=1c9dccb9a2434
    @GET(Constants.URL_Mob + "appstore/bank/card/query")
    Call<MobBaseEntity<MobBankCard>> queryBankCradInfo(@Query("key") String appkey,
                                                       @Query("card") String card
    );


    //全国今日油价查询
    //http://apicloud.mob.com/oil/price/province/query?key=appkey
    @GET(Constants.URL_Mob + "oil/price/province/query")
    Call<MobBaseEntity<MobOilPriceEntity>> queryOilPrice(@Query("key") String appkey
    );



}
