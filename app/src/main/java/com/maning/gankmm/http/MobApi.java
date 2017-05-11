package com.maning.gankmm.http;

import com.maning.gankmm.R;
import com.maning.gankmm.app.MyApplication;
import com.maning.gankmm.bean.MobBaseEntity;
import com.maning.gankmm.bean.mob.MobBankCard;
import com.maning.gankmm.bean.mob.MobIdCardEntity;
import com.maning.gankmm.bean.mob.MobIpEntity;
import com.maning.gankmm.bean.mob.MobPhoneAddressEntity;
import com.maning.gankmm.bean.mob.MobPostCodeEntity;
import com.maning.gankmm.bean.mob.MobWxArticleListEntity;
import com.maning.gankmm.bean.mob.MobWxCategoryEntity;
import com.maning.gankmm.constant.Constants;
import com.socks.library.KLog;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by maning on 2017/5/9.
 * Mob SDK 相关的API
 */

public class MobApi {

    public final static String GET_DATA_FAIL = MyApplication.getIntstance().getString(R.string.gank_get_data_fail);
    public final static String NET_FAIL = MyApplication.getIntstance().getString(R.string.gank_net_fail);


    public static Call<MobBaseEntity<ArrayList<MobWxCategoryEntity>>> queryWxArticleCategory(final int what, final MyCallBack myCallBack) {
        Call<MobBaseEntity<ArrayList<MobWxCategoryEntity>>> wxArticleCategoryCall = BuildApi.getAPIService().queryWxArticleCategory(Constants.URL_APP_Key);
        wxArticleCategoryCall.enqueue(new Callback<MobBaseEntity<ArrayList<MobWxCategoryEntity>>>() {
            @Override
            public void onResponse(Call<MobBaseEntity<ArrayList<MobWxCategoryEntity>>> call, Response<MobBaseEntity<ArrayList<MobWxCategoryEntity>>> response) {
                if (response.isSuccessful()) {
                    MobBaseEntity<ArrayList<MobWxCategoryEntity>> body = response.body();
                    if (body != null) {
                        if (body.getMsg().equals("success")) {
                            KLog.i("queryWxArticleCategory---success：" + body.toString());
                            myCallBack.onSuccessList(what, body.getResult());
                        } else {
                            myCallBack.onFail(what, body.getMsg());
                        }
                    } else {
                        myCallBack.onFail(what, GET_DATA_FAIL);
                    }
                } else {
                    myCallBack.onFail(what, GET_DATA_FAIL);
                }
            }

            @Override
            public void onFailure(Call<MobBaseEntity<ArrayList<MobWxCategoryEntity>>> call, Throwable t) {
                KLog.e("queryWxArticleCategory-----onFailure：" + t.toString());
                //数据错误
                myCallBack.onFail(what, NET_FAIL);
            }
        });

        return wxArticleCategoryCall;
    }

    public static Call<MobBaseEntity<MobWxArticleListEntity>> queryWxArticleList(String cid, int pageIndex, int pageSize, final int what, final MyCallBack myCallBack) {

        Call<MobBaseEntity<MobWxArticleListEntity>> queryWxArticleListCall = BuildApi.getAPIService().queryWxArticleList(Constants.URL_APP_Key, cid, pageIndex, pageSize);
        queryWxArticleListCall.enqueue(new Callback<MobBaseEntity<MobWxArticleListEntity>>() {
            @Override
            public void onResponse(Call<MobBaseEntity<MobWxArticleListEntity>> call, Response<MobBaseEntity<MobWxArticleListEntity>> response) {
                if (response.isSuccessful()) {
                    MobBaseEntity<MobWxArticleListEntity> body = response.body();
                    if (body != null) {
                        if (body.getMsg().equals("success")) {
                            KLog.i("queryWxArticleList---success：" + body.toString());
                            myCallBack.onSuccess(what, body.getResult());
                        } else {
                            myCallBack.onFail(what, body.getMsg());
                        }
                    } else {
                        myCallBack.onFail(what, GET_DATA_FAIL);
                    }
                } else {
                    myCallBack.onFail(what, GET_DATA_FAIL);
                }
            }

            @Override
            public void onFailure(Call<MobBaseEntity<MobWxArticleListEntity>> call, Throwable t) {
                KLog.e("queryWxArticleList-----onFailure：" + t.toString());
                //数据错误
                myCallBack.onFail(what, NET_FAIL);
            }
        });

        return queryWxArticleListCall;

    }


    public static Call<MobBaseEntity<MobIpEntity>> queryIp(String ip, final int what, final MyCallBack myCallBack) {

        Call<MobBaseEntity<MobIpEntity>> call = BuildApi.getAPIService().queryIp(Constants.URL_APP_Key, ip);
        call.enqueue(new Callback<MobBaseEntity<MobIpEntity>>() {
            @Override
            public void onResponse(Call<MobBaseEntity<MobIpEntity>> call, Response<MobBaseEntity<MobIpEntity>> response) {
                if (response.isSuccessful()) {
                    MobBaseEntity<MobIpEntity> body = response.body();
                    if (body != null) {
                        if (body.getMsg().equals("success")) {
                            KLog.i("queryIp---success：" + body.toString());
                            myCallBack.onSuccess(what, body.getResult());
                        } else {
                            myCallBack.onFail(what, body.getMsg());
                        }
                    } else {
                        myCallBack.onFail(what, GET_DATA_FAIL);
                    }
                } else {
                    myCallBack.onFail(what, GET_DATA_FAIL);
                }
            }

            @Override
            public void onFailure(Call<MobBaseEntity<MobIpEntity>> call, Throwable t) {
                KLog.e("queryIp-----onFailure：" + t.toString());
                //数据错误
                myCallBack.onFail(what, NET_FAIL);
            }
        });

        return call;

    }


    public static Call<MobBaseEntity<MobIdCardEntity>> queryIDCard(String idcardNum, final int what, final MyCallBack myCallBack) {

        Call<MobBaseEntity<MobIdCardEntity>> call = BuildApi.getAPIService().queryIdcard(Constants.URL_APP_Key, idcardNum);
        call.enqueue(new Callback<MobBaseEntity<MobIdCardEntity>>() {
            @Override
            public void onResponse(Call<MobBaseEntity<MobIdCardEntity>> call, Response<MobBaseEntity<MobIdCardEntity>> response) {
                if (response.isSuccessful()) {
                    MobBaseEntity<MobIdCardEntity> body = response.body();
                    if (body != null) {
                        if (body.getMsg().equals("success")) {
                            KLog.i("queryIdcard---success：" + body.toString());
                            myCallBack.onSuccess(what, body.getResult());
                        } else {
                            myCallBack.onFail(what, body.getMsg());
                        }
                    } else {
                        myCallBack.onFail(what, GET_DATA_FAIL);
                    }
                } else {
                    myCallBack.onFail(what, GET_DATA_FAIL);
                }
            }

            @Override
            public void onFailure(Call<MobBaseEntity<MobIdCardEntity>> call, Throwable t) {
                KLog.e("queryIdcard-----onFailure：" + t.toString());
                //数据错误
                myCallBack.onFail(what, NET_FAIL);
            }
        });

        return call;

    }


    public static Call<MobBaseEntity<MobPostCodeEntity>> queryPostCode(String postCode, final int what, final MyCallBack myCallBack) {

        Call<MobBaseEntity<MobPostCodeEntity>> call = BuildApi.getAPIService().queryPostCode(Constants.URL_APP_Key, postCode);
        call.enqueue(new Callback<MobBaseEntity<MobPostCodeEntity>>() {
            @Override
            public void onResponse(Call<MobBaseEntity<MobPostCodeEntity>> call, Response<MobBaseEntity<MobPostCodeEntity>> response) {
                if (response.isSuccessful()) {
                    MobBaseEntity<MobPostCodeEntity> body = response.body();
                    if (body != null) {
                        if (body.getMsg().equals("success")) {
                            KLog.i("queryPostCode---success：" + body.toString());
                            myCallBack.onSuccess(what, body.getResult());
                        } else {
                            myCallBack.onFail(what, body.getMsg());
                        }
                    } else {
                        myCallBack.onFail(what, GET_DATA_FAIL);
                    }
                } else {
                    myCallBack.onFail(what, GET_DATA_FAIL);
                }
            }

            @Override
            public void onFailure(Call<MobBaseEntity<MobPostCodeEntity>> call, Throwable t) {
                KLog.e("queryPostCode-----onFailure：" + t.toString());
                //数据错误
                myCallBack.onFail(what, NET_FAIL);
            }
        });

        return call;

    }


    public static Call<MobBaseEntity<MobPhoneAddressEntity>> queryPhoneAddress(String phoneNum, final int what, final MyCallBack myCallBack) {

        Call<MobBaseEntity<MobPhoneAddressEntity>> call = BuildApi.getAPIService().queryMobileAddress(Constants.URL_APP_Key, phoneNum);
        call.enqueue(new Callback<MobBaseEntity<MobPhoneAddressEntity>>() {
            @Override
            public void onResponse(Call<MobBaseEntity<MobPhoneAddressEntity>> call, Response<MobBaseEntity<MobPhoneAddressEntity>> response) {
                if (response.isSuccessful()) {
                    MobBaseEntity<MobPhoneAddressEntity> body = response.body();
                    if (body != null) {
                        if (body.getMsg().equals("success")) {
                            KLog.i("queryPhoneAddress---success：" + body.toString());
                            myCallBack.onSuccess(what, body.getResult());
                        } else {
                            myCallBack.onFail(what, body.getMsg());
                        }
                    } else {
                        myCallBack.onFail(what, GET_DATA_FAIL);
                    }
                } else {
                    myCallBack.onFail(what, GET_DATA_FAIL);
                }
            }

            @Override
            public void onFailure(Call<MobBaseEntity<MobPhoneAddressEntity>> call, Throwable t) {
                KLog.e("queryPhoneAddress-----onFailure：" + t.toString());
                //数据错误
                myCallBack.onFail(what, NET_FAIL);
            }
        });

        return call;

    }


    public static Call<MobBaseEntity<MobBankCard>> queryBankCard(String content, final int what, final MyCallBack myCallBack) {

        Call<MobBaseEntity<MobBankCard>> call = BuildApi.getAPIService().queryBankCradInfo(Constants.URL_APP_Key, content);
        call.enqueue(new Callback<MobBaseEntity<MobBankCard>>() {
            @Override
            public void onResponse(Call<MobBaseEntity<MobBankCard>> call, Response<MobBaseEntity<MobBankCard>> response) {
                if (response.isSuccessful()) {
                    MobBaseEntity<MobBankCard> body = response.body();
                    if (body != null) {
                        if (body.getMsg().equals("success")) {
                            KLog.i("queryBankCard---success：" + body.toString());
                            myCallBack.onSuccess(what, body.getResult());
                        } else {
                            myCallBack.onFail(what, body.getMsg());
                        }
                    } else {
                        myCallBack.onFail(what, GET_DATA_FAIL);
                    }
                } else {
                    myCallBack.onFail(what, GET_DATA_FAIL);
                }
            }

            @Override
            public void onFailure(Call<MobBaseEntity<MobBankCard>> call, Throwable t) {
                KLog.e("queryBankCard-----onFailure：" + t.toString());
                //数据错误
                myCallBack.onFail(what, NET_FAIL);
            }
        });

        return call;

    }


}
