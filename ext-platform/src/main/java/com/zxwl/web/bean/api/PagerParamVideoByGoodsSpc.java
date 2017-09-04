package com.zxwl.web.bean.api;


/**
 * Created by xianghugui on 03/08/2017.
 */
public class PagerParamVideoByGoodsSpc {
    private String userId;
    //分页条件
    private String size;

    private String class_code;

    private String color;

    private int pageIndex = 0;
    private int pageSize = 25;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
        return "PagerParamVideoByGoodsSpc{" +
                "userId='" + userId + '\'' +
                ", size='" + size + '\'' +
                ", class_code='" + class_code + '\'' +
                ", color='" + color + '\'' +
                ", pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                '}';
    }
}
