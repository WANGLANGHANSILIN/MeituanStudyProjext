package com.wanglang.bottomnavigation;

import android.app.Application;
import android.content.Context;

/**
 * Created by wangl on 2016/11/30 0030.
 */

public class MyApplication extends Application {

    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }
}
