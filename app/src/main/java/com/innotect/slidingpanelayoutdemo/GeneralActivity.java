package com.innotect.slidingpanelayoutdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneralActivity extends AppCompatActivity {

    private ListView listView;
    private WebView webView;
    private SlidingPaneLayout slidingPaneLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        slidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.slidingPaneLayout);
        slidingPaneLayout.setSliderFadeColor(Color.TRANSPARENT);
        slidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                listView.setAlpha(slideOffset);
            }

            @Override
            public void onPanelOpened(View panel) {

            }

            @Override
            public void onPanelClosed(View panel) {

            }
        });

        setupToolBar();
        setupListview();
        setupWebView();
    }

    private void setupToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (slidingPaneLayout.isOpen()) {
                    slidingPaneLayout.closePane();
                } else {
                    slidingPaneLayout.openPane();
                }
            }
        });
    }

    private void setupListview() {
        SimpleAdapter adapter = new SimpleAdapter(this, getData(),
                R.layout.adapter_general_item, new String[] { "title" },
                new int[] { R.id.title });

        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String brief = ((Map<String, String>)adapterView.getItemAtPosition(i)).get("brief");
                webView.loadUrl(brief);
                slidingPaneLayout.closePane();
            }
        });
    }

    private void setupWebView() {
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl("http://www.baidu.com");
    }

    private List<Map<String, String>> getData() {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("title", "百度");
        map.put("brief", "http://www.baidu.com");
        list.add(map);

        map = new HashMap<>();
        map.put("title", "sina");
        map.put("brief", "http://www.sina.com");
        list.add(map);

        map = new HashMap<>();
        map.put("title", "网易");
        map.put("brief", "http://www.163.com");
        list.add(map);
        return list;
    }
}
