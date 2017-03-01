package com.maning.gankmm.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maning.gankmm.R;
import com.maning.gankmm.bean.SearchBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by maning on 2017/3/1.
 */

public class RecycleSearchAdapter extends RecyclerView.Adapter<RecycleSearchAdapter.MyViewHolder> {

    private Context context;
    private List<SearchBean> mDatas;
    private LayoutInflater layoutInflater;

    public RecycleSearchAdapter(Context context, List<SearchBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        layoutInflater = LayoutInflater.from(this.context);
    }

    private RecycleSearchAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(RecycleSearchAdapter.OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public RecycleSearchAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = layoutInflater.inflate(R.layout.item_search, parent, false);

        return new RecycleSearchAdapter.MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final RecycleSearchAdapter.MyViewHolder viewHolder, final int position) {
        SearchBean searchBean = mDatas.get(position);

        viewHolder.tvTime.setText(searchBean.getDesc());

        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(viewHolder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setNewDatas(List<SearchBean> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_time)
        TextView tvTime;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

}
