package com.zxwl.web.bean.api;


/**
 * Created by xianghugui on 03/08/2017.
 */
public  class PagerParamApi extends com.zxwl.web.bean.po.resource.PagerParamApi {

    private  String userId;
    private int pageIndex = 0;
    private int pageSize = 25;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        return "PagerParamApi{" +
                "userId='" + userId + '\'' +
                ", pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                '}';
    }
}
