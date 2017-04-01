package com.maning.gankmm.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maning.gankmm.R;
import com.maning.gankmm.skin.SkinManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by maning on 16/5/17.
 */
public class RecycleCitysAdapter extends RecyclerView.Adapter<RecycleCitysAdapter.MyViewHolder> {

    private Context context;
    private List<String> mDatas;
    private LayoutInflater layoutInflater;
    private int currentPosition = -1;
    private int currentSkinType;

    public RecycleCitysAdapter(Context context, List<String> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        layoutInflater = LayoutInflater.from(this.context);
        currentSkinType = SkinManager.getCurrentSkinType(this.context);
    }

    public void updateDatas(List<String> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = layoutInflater.inflate(R.layout.item_time, parent, false);

        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int position) {

        viewHolder.tvTime.setText(mDatas.get(position));

        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentPosition = position;
                    mOnItemClickLitener.onItemClick(viewHolder.itemView, position);
                    notifyDataSetChanged();
                }
            });
        }

        if (currentSkinType == SkinManager.THEME_DAY) {
            if (currentPosition == position) {
                viewHolder.tvTime.setTextColor(context.getResources().getColor(R.color.main_color));
            } else {
                viewHolder.tvTime.setTextColor(context.getResources().getColor(R.color.black_text2_color));
            }
        } else {
            if (currentPosition == position) {
                viewHolder.tvTime.setTextColor(context.getResources().getColor(R.color.gank_text1_color_night));
            } else {
                viewHolder.tvTime.setTextColor(context.getResources().getColor(R.color.gank_text2_color_night));
            }
        }

    }

    public String getPositionValue(int position) {
        return mDatas.get(position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
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
