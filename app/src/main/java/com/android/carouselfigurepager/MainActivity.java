package com.android.carouselfigurepager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends Activity {

    @ViewInject(R.id.lv_main)
    private ListView lv_main;

    private TextView tv_carousel_figure_title;

    private RelativeLayout rl_carousel_figure_imgs;
    private LinearLayout ll_carousel_figure_dots;
    private Adapter adapter;
    private List<String> imgs = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private List<String> urls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        View view = View.inflate(getApplicationContext(), R.layout.view_carousel_figure, null);
        rl_carousel_figure_imgs = (RelativeLayout) view.findViewById(R.id.rl_carousel_figure_imgs);
        ll_carousel_figure_dots = (LinearLayout) view.findViewById(R.id.ll_carousel_figure_dots);
        tv_carousel_figure_title = (TextView) view.findViewById(R.id.tv_carousel_figure_title);
        lv_main.addHeaderView(view);
        if (adapter == null) {
            adapter = new Adapter();
            lv_main.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

        imgs.add("http://img.kutoo8.com//upload/image/78018037/201305280911_960x540.jpg");
        imgs.add("http://www.xiaoxiongbizhi.com/wallpapers/__85/1/9/19r0an0jm.jpg");
        imgs.add("http://www.xiaoxiongbizhi.com/wallpapers/__85/y/w/ywzwdjof0.jpg");
        imgs.add("http://image.xcar.com.cn/attachments/a/day_130709/2013070923_3b9f49e9b25948120c73Wr8Gwv8sodLh.jpg");

        titles.add("aaaaaaaaaaa");
        titles.add("bbbbbbbbbbb");
        titles.add("ccccccccccc");
        titles.add("ddddddddddd");

        urls.add("111111111111111111111");
        urls.add("222222222222222222222");
        urls.add("333333333333333333333");
        urls.add("444444444444444444444");

        CarouselFigurePager carouselFigurePager = new CarouselFigurePager(getApplicationContext(), new CarouselFigurePager.OnClickListener() {
            @Override
            public void onClick(String url) {
                LogUtil.e("url:" + url);
            }
        });
        rl_carousel_figure_imgs.addView(carouselFigurePager);
        carouselFigurePager.init(imgs, urls, titles, ll_carousel_figure_dots, tv_carousel_figure_title);
    }


    private class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(getApplicationContext());
            textView.setText("xxx");
            return textView;
        }
    }
}
