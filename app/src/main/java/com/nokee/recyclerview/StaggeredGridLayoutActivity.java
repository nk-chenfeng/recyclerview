package com.nokee.recyclerview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class StaggeredGridLayoutActivity extends ActionBarActivity {

    private RecyclerView mReclyerView;
    private List<String> mDatas;
    private StaggeredAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();
        initViews();
        mAdapter = new StaggeredAdapter(this, mDatas);
        mReclyerView.setAdapter(mAdapter);

        // 设置RecyclerView的布局管理
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mReclyerView.setLayoutManager(staggeredGridLayoutManager);

        // 设置RecyclerView的Item的间隔线
        mReclyerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        mAdapter.setOnItemClickListener(new MySimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mAdapter.deleteData(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    private void initViews() {
        mReclyerView = (RecyclerView) findViewById(R.id.reclerview);
    }

    private void initDatas() {
        mDatas = new ArrayList<String>();

        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char)i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_gridview:
                mReclyerView.setLayoutManager(new GridLayoutManager(this, 3));
                break;
            case R.id.action_hor_gridview:
                mReclyerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case R.id.action_listview:
                mReclyerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.action_staggered:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
