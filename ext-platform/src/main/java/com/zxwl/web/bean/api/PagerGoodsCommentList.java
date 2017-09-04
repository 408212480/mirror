package com.zxwl.web.bean.api;

/**
 * Created by xianghugui on 07/08/2017.
 */
public class PagerGoodsCommentList {

    private  String goodsId;
    private int pageIndex = 0;
    private int pageSize = 25;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PagerGoodsCommentList{" +
                "goodsId='" + goodsId + '\'' +
                ", pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                '}';
    }
}
