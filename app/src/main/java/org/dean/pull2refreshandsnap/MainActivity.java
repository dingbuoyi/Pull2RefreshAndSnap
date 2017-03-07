package org.dean.pull2refreshandsnap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;


public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private List<Integer> mDataList;
    private PtrClassicFrameLayout mPtrFrame;
    private ScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPull2Refresh();
        initSnap();
    }

    private void initSnap() {
        rv = (RecyclerView) findViewById(R.id.rv);
        mDataList = new ArrayList<>();
        mDataList.add(R.mipmap.pic_1);
        mDataList.add(R.mipmap.pic_2);
        mDataList.add(R.mipmap.pic_3);
        mDataList.add(R.mipmap.pic_4);
        mDataList.add(R.mipmap.pic_5);
        mDataList.add(R.mipmap.pic_6);
        mDataList.add(R.mipmap.pic_1);
        mDataList.add(R.mipmap.pic_2);
        mDataList.add(R.mipmap.pic_3);
        mDataList.add(R.mipmap.pic_4);
        mDataList.add(R.mipmap.pic_5);
        mDataList.add(R.mipmap.pic_6);
        mDataList.add(R.mipmap.pic_1);
        mDataList.add(R.mipmap.pic_2);
        mDataList.add(R.mipmap.pic_3);
        mDataList.add(R.mipmap.pic_4);
        mDataList.add(R.mipmap.pic_5);
        mDataList.add(R.mipmap.pic_6);
        mDataList.add(R.mipmap.pic_1);
        mDataList.add(R.mipmap.pic_2);
        mDataList.add(R.mipmap.pic_3);
        mDataList.add(R.mipmap.pic_4);
        mDataList.add(R.mipmap.pic_5);
        mDataList.add(R.mipmap.pic_6);

        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv.setAdapter(new CardRvAdapter(this, mDataList));

        // LinearSnapHelper mLinearSnapHelper = new LinearSnapHelper();
        // once move one item
        LinearSnapHelper mLinearSnapHelper = new LinearSnapHelper() {
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
                View centerView = findSnapView(layoutManager);
                if (centerView == null)
                    return RecyclerView.NO_POSITION;

                int position = layoutManager.getPosition(centerView);
                int targetPosition = -1;
                if (layoutManager.canScrollHorizontally()) {
                    if (velocityX < 0) {
                        targetPosition = position - 1;
                    } else {
                        targetPosition = position + 1;
                    }
                }

                if (layoutManager.canScrollVertically()) {
                    if (velocityY < 0) {
                        targetPosition = position - 1;
                    } else {
                        targetPosition = position + 1;
                    }
                }

                final int firstItem = 0;
                final int lastItem = layoutManager.getItemCount() - 1;
                targetPosition = Math.min(lastItem, Math.max(targetPosition, firstItem));
                return targetPosition;
            }
        };
        mLinearSnapHelper.attachToRecyclerView(rv);

//        CustomSnapHelper mMySnapHelper = new CustomSnapHelper();
//        mMySnapHelper.attachToRecyclerView(rv);

    }

    private void initPull2Refresh() {
        mScrollView = (ScrollView) findViewById(R.id.rotate_header_scroll_view);
        mPtrFrame = (PtrClassicFrameLayout) findViewById(R.id.rotate_header_web_view_frame);
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mScrollView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrame.refreshComplete();
                    }
                }, 100);
            }
        });

        // the following are default settings
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
        // default is false
        mPtrFrame.setPullToRefresh(false);
        // default is true
        mPtrFrame.setKeepHeaderWhenRefresh(true);
        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrame.autoRefresh();
            }
        }, 100);
    }
}

