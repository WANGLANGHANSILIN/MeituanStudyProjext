package com.wanglang.bottomnavigation.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by wangl on 2016/12/12 0012.
 */

public class ParseUtils {

    public static String parseData(Context context,String fileName) {
        try {
            InputStream stream = context.getAssets().open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line = "";
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null){
                builder.append(line);
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
