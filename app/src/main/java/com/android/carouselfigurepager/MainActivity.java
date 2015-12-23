package com.android.carouselfigurepager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends Activity {

    @ViewInject(R.id.lv_main)
    private ListView lv_main;

    private RelativeLayout rl_carousel_figure_imgs;

    private Adapter adapter;
    private List<String> imgs = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        View view = View.inflate(getApplicationContext(), R.layout.view_carousel_figure, null);
        rl_carousel_figure_imgs = (RelativeLayout) view.findViewById(R.id.rl_carousel_figure_imgs);
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
        imgs.add("http://pic3.nipic.com/20090708/2484872_105002096_2.jpg");
        imgs.add("http://pic19.nipic.com/20120301/7447430_190817489000_2.jpg");
        imgs.add("http://image.xcar.com.cn/attachments/a/day_130709/2013070923_3b9f49e9b25948120c73Wr8Gwv8sodLh.jpg");
        CarouselFigurePager carouselFigurePager = new CarouselFigurePager(getApplicationContext(), imgs);
        carouselFigurePager.start();
        rl_carousel_figure_imgs.addView(carouselFigurePager);


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
