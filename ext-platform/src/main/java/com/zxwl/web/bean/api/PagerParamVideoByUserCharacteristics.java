package com.zxwl.web.bean.api;


/**
 * Created by xianghugui on 03/08/2017.
 */
public  class PagerParamVideoByUserCharacteristics {
    private  String userId;
//年龄查询区间最大，最小值
    private int minAge = 0;
    private int maxAge = 10;
    //身高查询区间最大，最小值
    private int minHeight = 0;
    private int maxHeight = 10;
    //体重查询区间最大，最小值
    private int minWeight = 0;
    private int maxWeight = 10;
    //性别查询条件 (0 表示男， 1 表示女)
    private  int sex;
    //分页条件
    private int pageIndex = 0;
    private int pageSize = 25;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(int minWeight) {
        this.minWeight = minWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
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
        return "PagerParamVideoByUserCharacteristics{" +
                "userId='" + userId + '\'' +
                ", minAge=" + minAge +
                ", maxAge=" + maxAge +
                ", minHeight=" + minHeight +
                ", maxHeight=" + maxHeight +
                ", minWeight=" + minWeight +
                ", maxWeight=" + maxWeight +
                ", sex=" + sex +
                ", pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                '}';
    }
}
