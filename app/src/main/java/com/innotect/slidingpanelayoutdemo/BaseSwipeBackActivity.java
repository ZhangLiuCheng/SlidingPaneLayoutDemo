package com.innotect.slidingpanelayoutdemo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.view.ViewGroup.LayoutParams;

import java.lang.reflect.Field;

/**
 * Created by sunyuqin on 16/9/1.
 */

public class BaseSwipeBackActivity extends AppCompatActivity implements SlidingPaneLayout.PanelSlideListener{

    private SlidingPaneLayout mSlidingPaneLayout;

    private FrameLayout mContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSlidingPaneLayout = getSlidingPaneLayout(this);
        mSlidingPaneLayout.setPanelSlideListener(this);
        mContainer = getContainerView(mSlidingPaneLayout);
    }

    @Override
    public void setContentView(int id) {
        setContentView(getLayoutInflater().inflate(id, null));
    }

    @Override
    public void setContentView(View v) {
        setContentView(v, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    public void setContentView(View v, ViewGroup.LayoutParams params) {
        super.setContentView(mSlidingPaneLayout, params);
        mContainer.removeAllViews();
        mContainer.addView(v, params);
    }

    // 创建SlidingPaneLayout
    private SlidingPaneLayout getSlidingPaneLayout(Context context) {
        SlidingPaneLayout slidingLayout = new SlidingPaneLayout(context);
        try {
            Field overHangSize = SlidingPaneLayout.class.getDeclaredField("mOverhangSize");
            overHangSize.setAccessible(true);
            overHangSize.set(slidingLayout, 0);
            slidingLayout.setSliderFadeColor(Color.TRANSPARENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return slidingLayout;
    }

    // 创建FrameLayout,setcontentView添加在该FrameLayout上面
    private FrameLayout getContainerView(SlidingPaneLayout slidingPaneLayout) {
        View leftView = new View(this);
        leftView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        slidingPaneLayout.addView(leftView);

        FrameLayout container = new FrameLayout(this);
        container.setBackgroundColor(Color.WHITE);
        container.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        slidingPaneLayout.addView(container);
        return container;
    }

    @Override
    public void onPanelSlide(View panel, float slideOffset) {

    }

    @Override
    public void onPanelOpened(View panel) {
        finish();
    }

    @Override
    public void onPanelClosed(View panel) {
    }
}
