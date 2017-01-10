package com.wanglang.bottomnavigation.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wangl on 2016/12/13 0013.
 */

public class SpUtils {

    private static SharedPreferences.Editor sEdit;
    public static ArrayList<String> sStrings1 = new ArrayList<>();

    public static SharedPreferences.Editor getSharedPreFerences(Context context, String name){
        if (sEdit == null) {
            sEdit = context.getSharedPreferences(name, Context.MODE_PRIVATE).edit();
        }
        return sEdit;
    }
    public static void saveMessage(Context context, String name, String key, String value){
        SharedPreferences.Editor sharedPreFerences = getSharedPreFerences(context,name);

        if (sStrings1.size() >= 6){
            for (int i = 0; i < sStrings1.size(); i++) {
                if (i > 6)
                    sStrings1.remove(i);
            }
        }
        sStrings1.add(0,value);

        Set<String> sStrings = new HashSet<>();
        sStrings.addAll(sStrings1);

        sharedPreFerences.putStringSet(key,sStrings);
        sharedPreFerences.commit();
    }

    private static void saveMessage(Context context, String name, String key, ArrayList<String> values){
        for (String value : values) {
            saveMessage(context,name,key,value);
        }
    }


    public static Set<String> getMessage(Context context, String name, String key){
        return context.getSharedPreferences(name,Context.MODE_PRIVATE).getStringSet(key,null);
    }

    public static void saveSigleMessage(Context context, String name, String key, String value){
        SharedPreferences.Editor sharedPreFerences = getSharedPreFerences(context,name);
        sharedPreFerences.putString(key,value).commit();
    }

    public static String getSigleMessage(Context context, String name, String key){
        return context.getSharedPreferences(name,Context.MODE_PRIVATE).getString(key,null);
    }
}
