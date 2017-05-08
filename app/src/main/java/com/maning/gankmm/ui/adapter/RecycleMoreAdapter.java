package com.maning.gankmm.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maning.gankmm.R;
import com.maning.gankmm.skin.SkinManager;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 更多功能的Adapter
 */
public class RecycleMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<String> mDatas;
    private LayoutInflater layoutInflater;

    public RecycleMoreAdapter(Context context, List<String> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = layoutInflater.inflate(R.layout.item_more_tools, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) viewHolder;

            myViewHolder.tvTitleMore.setText(mDatas.get(position));

            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
            myViewHolder.recyclerViewItem.setLayoutManager(gridLayoutManager);
            myViewHolder.recyclerViewItem.setItemAnimator(new DefaultItemAnimator());

            ArrayList<String> mDatasItem = new ArrayList<>();
            if (position == 0) {
                mDatasItem.add("手机号码归属地");
                mDatasItem.add("邮编查询");
                mDatasItem.add("菜谱查询");
                mDatasItem.add("身份证查询");
                mDatasItem.add("IP地址");
                mDatasItem.add("中国彩票开奖结果");
                mDatasItem.add("微信精选");
            } else if (position == 1) {
                mDatasItem.add("银行卡信息");
                mDatasItem.add("黄金数据");
                mDatasItem.add("货币汇率");
                mDatasItem.add("白银数据");
                mDatasItem.add("国内现货交易所贵金属");
                mDatasItem.add("全球股指查询");
            } else if (position == 2) {
                mDatasItem.add("周公解梦");
                mDatasItem.add("婚姻匹配");
                mDatasItem.add("手机号码查吉凶");
                mDatasItem.add("八字算命");
                mDatasItem.add("老黄历");
                mDatasItem.add("电影票房");
                mDatasItem.add("火车票查询");
                mDatasItem.add("航班信息查询");
                mDatasItem.add("足球五大联赛");
            } else if (position == 3) {
                mDatasItem.add("健康知识");
                mDatasItem.add("历史上的今天");
                mDatasItem.add("成语大全");
                mDatasItem.add("新华字典");
                mDatasItem.add("全国省市今日油价");
                mDatasItem.add("汽车信息查询");
                mDatasItem.add("驾考题库");
            }
            RecycleMoreItemAdapter recycleMoreItemAdapter = new RecycleMoreItemAdapter(context, mDatasItem);
            myViewHolder.recyclerViewItem.setAdapter(recycleMoreItemAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_title_more)
        TextView tvTitleMore;
        @Bind(R.id.recyclerViewItem)
        RecyclerView recyclerViewItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
