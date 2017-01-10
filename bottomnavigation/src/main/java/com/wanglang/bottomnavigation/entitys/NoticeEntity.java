package com.wanglang.bottomnavigation.entitys;

/**
 * Created by wangl on 2016/11/10 0010.
 */

public class NoticeEntity {
    private int mIconId;//商品图像id
    private String mNoticeName;//商品名称
    private String mNoticeAddress;//商品位置
    private String mNoticePrice;//商品价格
    private int mNoticeRange;//相隔距离
    private int getNoticeSold;//已售数量

    public NoticeEntity(int iconId, String noticeName, String noticeAddress, String noticePrice, int noticeRange, int getNoticeSold) {
        mIconId = iconId;
        mNoticeName = noticeName;
        mNoticeAddress = noticeAddress;
        mNoticePrice = noticePrice;
        mNoticeRange = noticeRange;
        this.getNoticeSold = getNoticeSold;
    }

    public int getIconId() {
        return mIconId;
    }

    public void setIconId(int iconId) {
        mIconId = iconId;
    }

    public String getNoticeName() {
        return mNoticeName;
    }

    public void setNoticeName(String noticeName) {
        mNoticeName = noticeName;
    }

    public String getNoticeAddress() {
        return mNoticeAddress;
    }

    public void setNoticeAddress(String noticeAddress) {
        mNoticeAddress = noticeAddress;
    }

    public String getNoticePrice() {
        return mNoticePrice;
    }

    public void setNoticePrice(String noticePrice) {
        mNoticePrice = noticePrice;
    }

    public int getNoticeRange() {
        return mNoticeRange;
    }

    public void setNoticeRange(int noticeRange) {
        mNoticeRange = noticeRange;
    }

    public int getGetNoticeSold() {
        return getNoticeSold;
    }

    public void setGetNoticeSold(int getNoticeSold) {
        this.getNoticeSold = getNoticeSold;
    }
}
