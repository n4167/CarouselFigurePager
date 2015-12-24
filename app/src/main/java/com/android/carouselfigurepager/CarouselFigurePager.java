package com.android.carouselfigurepager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    private List<String> titleList = new ArrayList<>();
    private List<String> urlList = new ArrayList<>();
    private Adapter adapter;
    private int currPos = 0;
    private List<View> dotList = new ArrayList<>();
    private LinearLayout ll_dots;
    //    private RunnableTask runnableTask;
    private TextView textView;
    private OnClickListener onClickListener;


    public CarouselFigurePager(Context context, OnClickListener onClickListener) {
        super(context);
        this.onClickListener = onClickListener;
//        runnableTask = new RunnableTask();
        this.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currPos = position;
                textView.setText(titleList.get(position));
                for (int i = 0; i < imgList.size(); i++) {
                    if (i == position) {
                        dotList.get(i).setBackgroundResource(R.mipmap.dot_focus);
                    } else {
                        dotList.get(i).setBackgroundResource(R.mipmap.dot_normal);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public CarouselFigurePager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(final List<String> imgList, List<String> urlList, final List<String> titleList, LinearLayout relativeLayout, final TextView textView) {
        this.imgList = imgList;
        this.urlList = urlList;
        this.titleList = titleList;
        this.ll_dots = relativeLayout;
        this.textView = textView;
        initDots();
        initTitles();
        start();
    }

    public void start() {
        if (adapter == null) {
            adapter = new Adapter();
            this.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        handler.postDelayed(runnable, 2500);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            currPos = (currPos + 1) % imgList.size();
            handler.obtainMessage().sendToTarget();
        }
    };

//    private class RunnableTask implements Runnable {
//
//        @Override
//        public void run() {
//            if (imgList.size() > 0) {
//                currPos = (currPos + 1) % imgList.size();
//                handler.obtainMessage().sendToTarget();
//            }
//        }
//    }

    private void initDots() {
        ll_dots.removeAllViews();
        dotList.clear();
        if (ll_dots != null) {
            ll_dots.removeAllViews();
            dotList.clear();
            for (int i = 0; i < imgList.size(); i++) {
                View view = new View(getContext());
                view.setBackgroundResource(R.mipmap.dot_normal);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
                params.setMargins(5, 0, 5, 0);
                ll_dots.addView(view, params);
                dotList.add(view);
            }
            dotList.get(0).setBackgroundResource(R.mipmap.dot_focus);
        }
    }

    private void initTitles() {
        if (null != titleList && titleList.size() > 0) {
            for (int i = 0; i < titleList.size(); i++) {
                textView.setText(titleList.get(i));
            }
        }
        textView.setText(titleList.get(0));
    }

    public interface OnClickListener {
        public void onClick(String url);
    }

    @Override
    protected void onDetachedFromWindow() {
        handler.removeCallbacksAndMessages(null);
        super.onDetachedFromWindow();
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
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView imageView = new ImageView(getContext());
            x.image().bind(imageView, imgList.get(position));
            container.addView(imageView);
            imageView.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            handler.removeCallbacksAndMessages(null);
                            break;
                        case MotionEvent.ACTION_UP:
                            start();
                            onClickListener.onClick(urlList.get(position));
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            start();
                            break;
                    }
                    return true;
                }
            });
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
