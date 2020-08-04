package com.mr.pager.recycler;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class LoopRecyclerView extends RecyclerView {
    private static final String TAG = "loop_recyclerView";
    LoopSnapHelper loopSnapHelper;

    public LoopRecyclerView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LoopRecyclerView(Context context) {
        super(context);
        initView();
    }

    public LoopRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public void setOnPagerListener(final OnPagerListener onPagerListener) {
        loopSnapHelper.setOnPageListener(new LoopSnapHelper.OnPageListener() {
            @Override
            public void onPageSelector(int position) {
                if (getAdapter() != null){
                    position %= getAdapter().getItemRawCount();
                    if (position < 0) {
                        position = getAdapter().getItemCount() + position;
                    }

                    if (onPagerListener != null){
                        onPagerListener.onPageSelector(position);
                    }
                }

            }
        });


    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

    }

    @Override
    public LoopAdapter getAdapter() {
        return (LoopAdapter) super.getAdapter();
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (!(adapter instanceof LoopAdapter)) {
            throw new IllegalArgumentException("adapter must  instanceof LoopAdapter!");
        }
        super.setAdapter(adapter);
    }

    private void initView() {

         loopSnapHelper = new LoopSnapHelper();
        loopSnapHelper.attachToRecyclerView(this);
    }

    public interface OnPagerListener{
        void onPageSelector(int position);
    }


    public static abstract class LoopAdapter<T extends RecyclerView.ViewHolder,T2> extends RecyclerView.Adapter<T> {

       public List<T2> datas = new ArrayList<>();

        public void setNewData(List<T2> datas) {
            this.datas = datas;
            notifyDataSetChanged();
        }

        /**
         * 真实数据的大小
         */
        public int getItemRawCount() {
            return datas == null ? 0 : datas.size();
        }

        @Override
        final public int getItemViewType(int position) {
            return getLoopItemViewType(position % getItemRawCount());
        }

        protected int getLoopItemViewType(int position) {
            return 0;
        }

        @Override
        final public void onBindViewHolder(T holder, int position) {
            onBindLoopViewHolder(holder, position % getItemRawCount());
        }

        public abstract void onBindLoopViewHolder(T holder, int position);

        @Override
        final public int getItemCount() {
            int rawCount = getItemRawCount();
            if (rawCount > 0) {
                return Integer.MAX_VALUE;
            }
            return 0;
        }
    }
}
