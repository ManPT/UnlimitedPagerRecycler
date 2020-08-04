# RecyclerView实现无限滑动的ViewPager

##  用法
1. 布局直接使用LoopRecyclerView
```
<com.mr.pager.recycler.LoopRecyclerView
    android:id="@+id/loopRecyclerView"
    android:layout_width="0dp"
    android:layout_height="500dp"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    />

```
2. 适配器要继承LoopRecyclerView.LoopAdapter

```
public class LoopAdapter extends LoopRecyclerView.LoopAdapter<LoopAdapter.MyViewHolder,String> {


     @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_home_brand, parent, false);
            return new MyViewHolder(inflate);
        }

     @Override
        public void onBindLoopViewHolder(MyViewHolder holder, final int position) {
            String item = datas.get(position);
            // 此处进行数据的绑定
        }



     public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            public MyViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.iv_icon);
            }
        }
}
```

3. 和RecyclerView用法一样，进行数据绑定
```
        list.add("https://oss.xilingbm.com/brand/2020-04/2020042206593378127.png");
        list.add("https://oss.xilingbm.com/country/2020-06/20200602093121493PH.jpg");
        list.add("https://oss.xilingbm.com/country/2020-06/20200602093109018S5.jpg");
        recyclerView = findViewById(R.id.loopRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));
        loopAdapter = new LoopAdapter(this);
        recyclerView.setAdapter(loopAdapter);
        loopAdapter.setNewData(list);
```

4. 由于无限滑动的实现方案是通过设置item数量为Integer最大值，所以填充完数据后，需要将位置设置到中间
```
recyclerView.scrollToPosition(loopAdapter.getItemRawCount() * 10000);
```
5. 设置滑动监听
```
  recyclerView.setOnPagerListener(new LoopRecyclerView.OnPagerListener() {
            @Override
            public void onPageSelector(int position) {
                Log.d("tag","position = " + position);
            }
        });
```





















