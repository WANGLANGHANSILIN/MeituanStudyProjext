package com.wanglang.bottomnavigation.utils;

import android.widget.Toast;

import com.wanglang.bottomnavigation.MyApplication;

/**
 * Created by wangl on 2016/11/30 0030.
 */

public class TT {
    private static Toast mToast;

    public static void showText(String Tag,String msg){
        if (mToast == null){
            mToast = Toast.makeText(MyApplication.sContext,msg,Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }

    public static void showText(String msg){
        showText("",msg);
    }
}
