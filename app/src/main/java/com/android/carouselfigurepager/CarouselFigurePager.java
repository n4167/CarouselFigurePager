package com.android.carouselfigurepager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weidai on 2015/12/23.
 */
public class CarouselFigurePager extends LazyViewPager {

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            CarouselFigurePager.this.setCurrentItem(currPos);
            start();
        }
    };

    private List<String> imgList = new ArrayList<>();
    private Adapter adapter;
    private int currPos = 0;

    public CarouselFigurePager(Context context, List<String> imgList) {
        super(context);
        this.imgList = imgList;
    }

    public CarouselFigurePager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void start() {
        if (adapter == null) {
            adapter = new Adapter();
            this.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currPos = (currPos + 1) % imgList.size();
                handler.obtainMessage().sendToTarget();
//                LogUtil.e("currPos:" + currPos + "-------------------------");
            }
        }, 2500);
    }

    private class Adapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imgList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(getContext());
            x.image().bind(imageView, imgList.get(position));
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
