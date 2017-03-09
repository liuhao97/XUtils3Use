package com.devliu.xutils3use.basic;

import android.app.Application;

import org.xutils.x;

/**
 * Created by liuhao
 * on 2017/3/8
 * use to :
 */

public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化
        x.Ext.init(this);
        // 是否输出debug日志, 开启debug会影响性能.
        //x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
