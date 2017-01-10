package com.wanglang.bottomnavigation.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wangl on 2016/12/14 0014.
 */

public class HanziToPinyin {

    //获取首字母
    public static String toFistLeter(String s){
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        String[] strings = PinyinHelper.toHanyuPinyinStringArray(s.charAt(0));
        for (int i = 0; i < strings.length; i++) {

        }
//        Log.i("toFistLeter","xixihaha:"+strings[0].charAt(0));
        return strings[0].toUpperCase().charAt(0)+"";
    }

    public static ArrayList<String> toFistLeter(ArrayList<String> stringArray){

        HashMap<String,ArrayList<String>> listHashMap= new HashMap<>();
        ArrayList<String> strings2 = new ArrayList<>();


        ArrayList<String> strings = new ArrayList<>();
        for (String s : stringArray) {
            String s1 = toFistLeter(s);
            strings.add(s1);
        }

        for (String string : strings) {
            ArrayList<String> strings1 = new ArrayList<>();
            for (String s : stringArray) {
                if (toFistLeter(s).equals(string)){
                    strings1.add(s);
                }
            }
            listHashMap.put(string,strings1);
        }

        for (String s : listHashMap.keySet()) {
            ArrayList<String> strings3 = listHashMap.get(s);
            strings2.addAll(strings3);
        }

        return strings2;
    }
}
