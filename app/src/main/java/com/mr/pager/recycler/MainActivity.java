package com.mr.pager.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    LoopRecyclerView recyclerView;
    LoopAdapter loopAdapter;
    List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list.add("https://oss.xilingbm.com/brand/2020-04/2020042206593378127.png");
        list.add("https://oss.xilingbm.com/country/2020-06/20200602093121493PH.jpg");
        list.add("https://oss.xilingbm.com/country/2020-06/20200602093109018S5.jpg");
        recyclerView = findViewById(R.id.loopRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));
        loopAdapter = new LoopAdapter(this);
        recyclerView.setAdapter(loopAdapter);
        loopAdapter.setNewData(list);
        recyclerView.setOnPagerListener(new LoopRecyclerView.OnPagerListener() {
            @Override
            public void onPageSelector(int position) {
                Log.d("tag","position = " + position);
            }
        });
        recyclerView.scrollToPosition(loopAdapter.getItemRawCount() * 10000);//开始时的偏移量
    }
}
