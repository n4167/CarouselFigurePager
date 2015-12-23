package com.android.carouselfigurepager;

import android.app.Application;

import org.xutils.x;

/**
 * Created by weidai on 2015/12/23.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
