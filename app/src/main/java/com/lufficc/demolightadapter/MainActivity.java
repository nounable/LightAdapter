package com.lufficc.demolightadapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.lufficc.demolightadapter.model.TextModel;
import com.lufficc.demolightadapter.viewprovider.TextViewProvider;
import com.lufficc.lightadapter.LightAdapter;
import com.lufficc.lightadapter.OnDataClickListener;

public class MainActivity extends AppCompatActivity implements OnDataClickListener{
    SwipeRefreshLayout swipeRefreshLayout;

    RecyclerView recyclerView;

    LightAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        adapter.register(TextModel.class, new TextViewProvider());
        adapter.setOnDataClickListener(this);
        adapter.addData(new TextModel("Multi type item"));
        adapter.addData(new TextModel("Header and footer"));
        adapter.addData(new TextModel("load more footer"));
        adapter.addData(new TextModel("Grid load more footer"));
        adapter.addData(new TextModel("Staggered load more footer"));
    }

    private void init() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DefaultItemDecoration(ContextCompat.getColor(this, R.color.white),
                ContextCompat.getColor(this, R.color.divider),
                getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin)));
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter = new LightAdapter());
    }

    @Override
    public void onDataClick(int position, Object data) {
        Class activity;
        switch (position)
        {
            case 0:
                activity = MultiItemActivity.class;
                break;
            case 1:
                activity = HeaderFooterActivity.class;
                break;
            case 2:
                activity = LoadMoreActivity.class;
                break;
            case 3:
                activity = GridLoadMoreActivity.class;
                break;
            default:
                activity = SLoadMoreActivity.class;
            break;
        }
        startActivity(new Intent(this,activity));
    }

}
