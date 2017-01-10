package com.wanglang.bottomnavigation.entitys;

/**
 * Created by wangl on 2016/12/23 0023.
 */

public class CategrayTagEntity {
    private String tagName;
    private boolean iSeletor;

    public CategrayTagEntity(String tagName, boolean iSeletor) {
        this.tagName = tagName;
        this.iSeletor = iSeletor;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public boolean iSeletor() {
        return iSeletor;
    }

    public void setiSeletor(boolean iSeletor) {
        this.iSeletor = iSeletor;
    }
}
