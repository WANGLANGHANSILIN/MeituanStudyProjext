package com.wanglang.bottomnavigation.entitys;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by wangl on 2016/12/8 0008.
 */

public class CategoryEntity {

    private int id;
    @SerializedName("name")
    private String mName;
    /**
     * iconUrl : http://p0.meituan.net/jungle/d0a390639afbd9d49c345d3996af0d5c1787.png
     * showType : food
     * list : [{"name":"全部","showType":"food","type":"poi"}]
     */

    @SerializedName("iconUrl")
    private String iconUrl;
    @SerializedName("showType")
    private String showType;
    @SerializedName("list")
    private ArrayList<ListBean> list;


    public CategoryEntity(String name) {
        mName = name;
    }

    public CategoryEntity() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public ArrayList<ListBean> getList() {
        return list;
    }

    public void setList(ArrayList<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * name : 全部
         * showType : food
         * type : poi
         */

        @SerializedName("name")
        private String name;
        @SerializedName("showType")
        private String showType;
        @SerializedName("type")
        private String type;


        private boolean isExpand;

        public ListBean(String name) {
            this.name = name;
        }

        public ListBean() {
        }


        public boolean isExpand() {
            return isExpand;
        }

        public void setExpand(boolean expand) {
            isExpand = expand;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShowType() {
            return showType;
        }

        public void setShowType(String showType) {
            this.showType = showType;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
