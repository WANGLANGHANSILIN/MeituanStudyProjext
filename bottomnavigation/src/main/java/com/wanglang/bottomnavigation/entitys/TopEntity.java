package com.wanglang.bottomnavigation.entitys;

/**
 * Created by wangl on 2016/11/11 0011.
 */

public class TopEntity {
    private String imgUrl;
    private String name;
    private String defaultUrl;

    public TopEntity(String imgUrl, String name) {
        this.imgUrl = imgUrl;
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultUrl() {
        return defaultUrl;
    }

    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }
}
