package com.wanglang.bottomnavigation.entitys;

/**
 * Created by wangl on 2016/11/30 0030.
 */

public class PersonInfoEntity {
    private int mPimgId;//图片id
    private String mPiName;//item名称
    private int mPiCode;//跳转请求碼
    private String mPiPackageName;//包名
    private String mPiTextDec;//是否在item后面显示文字描述

    public int getPimgId() {
        return mPimgId;
    }

    public void setPimgId(int pimgId) {
        mPimgId = pimgId;
    }

    public String getPiName() {
        return mPiName;
    }

    public void setPiName(String piName) {
        mPiName = piName;
    }

    public int getPiCode() {
        return mPiCode;
    }

    public void setPiCode(int piCode) {
        mPiCode = piCode;
    }

    public String getPiTextDec() {
        return mPiTextDec;
    }

    public void setPiTextDec(String piTextDec) {
        mPiTextDec = piTextDec;
    }

    public String getPiPackageName() {
        return mPiPackageName;
    }

    public void setPiPackageName(String piPackageName) {
        mPiPackageName = piPackageName;
    }
}
