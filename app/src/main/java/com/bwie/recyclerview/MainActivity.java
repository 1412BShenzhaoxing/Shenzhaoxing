package com.bwie.recyclerview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView rv;
    private List<String> list;
    private MyAdapter adapter;
    private int pos = 1;
    private int posDel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        initView();

        Log.e("Myszx","first");
        Log.d("MYADX","DAF");
        Log.d("szx","lover");

    }

    private void initData() {

        list = new ArrayList<String>();
        for(int i=0;i<100;i++){

           list.add("item"+i);
        }
    }


    private void initView() {

        rv = (RecyclerView) findViewById(R.id.recyclerview);


//        GridLayoutManager layoutmanager = new GridLayoutManager(this,3);
        StaggeredGridLayoutManager layoutmanager =new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL);
//        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
//        layoutmanager.setOrientation(OrientationHelper.VERTICAL);
        rv.setLayoutManager(layoutmanager);

        rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        rv.setItemAnimator(new DefaultItemAnimator());

        adapter = new MyAdapter(this,list);
        rv.setAdapter(adapter);

        adapter.setOnItemClickLitener(new MyAdapter.OnItemClickLitener()
        {

            @Override
            public void onItemClick(View view, int position)
            {
                Toast.makeText(MainActivity.this, position + " click",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position)
            {
                Toast.makeText(MainActivity.this, position + " long click",
                        Toast.LENGTH_SHORT).show();

            }
        });



    }

    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.add) {
            //添加布局管理器
//            LinearLayoutManager layoutManager=
//                    new LinearLayoutManager(this);
//            layoutManager.setOrientation(OrientationHelper.VERTICAL);
//            mRecyclerView.setLayoutManager(layoutManager);

            list.add(pos, "item add " + pos);
            adapter.notifyItemInserted(pos);
            adapter.notifyItemRangeChanged(pos, list.size());
            pos++;
        } else if (v.getId() == R.id.delete) {
//            GridLayoutManager layoutManager =new GridLayoutManager(this,2);
//            mRecyclerView.setLayoutManager(layoutManager);

            list.remove(posDel);
            adapter.notifyItemRemoved(posDel);
            adapter.notifyItemChanged(posDel, list.size());


        }
    }
}
