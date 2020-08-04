package com.mr.pager.recycler;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class LoopAdapter extends LoopRecyclerView.LoopAdapter<LoopAdapter.MyViewHolder,String> {
    private Context mContext;

    public LoopAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public static void loadImage(Context context, ImageView imageView, String url) {
        Glide.with(context).load(url)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .fitCenter()
                .into(imageView);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_brand, parent, false);
        return new MyViewHolder(inflate);
    }


    @Override
    public void onBindLoopViewHolder(MyViewHolder holder, final int position) {
        String item = datas.get(position);
        Glide.with(mContext).load(item).into(holder.imageView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_icon);
        }
    }

}
