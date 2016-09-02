package com.innotect.slidingpanelayoutdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        setupListview();
    }

    private void setupListview() {
        SimpleAdapter adapter = new SimpleAdapter(this, getData(),
                R.layout.adapter_main_item, new String[] { "title", "brief" },
                new int[] { R.id.title, R.id.brief });

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                if (0 == i) {
                    intent = new Intent(MainActivity.this, GeneralActivity.class);
                } else {
                    intent = new Intent(MainActivity.this, SlideBackActivity.class);
                }
                startActivity(intent);
            }
        });
    }

    private List<Map<String, String>> getData() {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("title", "Slidingpanelayout基本用法");
        map.put("brief", "左侧菜单,和drawLayout,slidingMenu功能差不多");
        list.add(map);

        map = new HashMap<>();
        map.put("title", "Slidingpanelayout滑动返回");
        map.put("brief", "手势滑动返回上个Activity");
        list.add(map);

        return list;
    }
}
