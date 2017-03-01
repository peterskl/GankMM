package com.maning.gankmm.ui.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.maning.gankmm.bean.SearchBean;
import com.maning.gankmm.http.GankApi;
import com.maning.gankmm.http.MyCallBack;
import com.maning.gankmm.ui.iView.ISearchView;
import com.maning.gankmm.ui.presenter.ISearchPresenter;

import java.util.List;

/**
 * Created by maning on 2017/3/1.
 */

public class SearchPresenterImpl extends BasePresenterImpl<ISearchView> implements ISearchPresenter {

    private Context context;

    private MyCallBack httpCallBack = new MyCallBack() {
        @Override
        public void onSuccessList(int what, List results) {
            if (mView == null) {
                return;
            }
            if (what == 0x001) {
                mView.hideBaseProgressDialog();
                List<SearchBean> gankSearchList = results;
                if (gankSearchList != null && gankSearchList.size() > 0) {
                    mView.setSearchList(gankSearchList);
                }
            }
        }

        @Override
        public void onSuccess(int what, Object result) {

        }

        @Override
        public void onFail(int what, String result) {
            if (mView == null) {
                return;
            }
            mView.hideBaseProgressDialog();
            if (!TextUtils.isEmpty(result)) {
                mView.showToast(result);
            }
        }
    };

    public SearchPresenterImpl(Context context, ISearchView iSearchView) {
        this.context = context;
        attachView(iSearchView);
    }

    @Override
    public void searchDatas(String keyWords) {
        if (mView == null) {
            return;
        }

        //开始搜索
        if (TextUtils.isEmpty(keyWords)) {
            mView.showToast("不能为空");
            return;
        }

        //获取数据
        mView.showBaseProgressDialog("搜索中...");
        GankApi.getSearchData(keyWords, "all", 20, 1, 0x001, httpCallBack);

    }

    @Override
    public void loadMoreDatas() {

    }

    @Override
    public void itemClick(int position) {

    }
}
