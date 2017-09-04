package com.zxwl.web.bean.api;

import java.util.Arrays;

/**
 * Created by xianghugui on 09/08/2017.
 */

public class PeopleSearchGoodsVideo {
    //年龄查询区间最大，最小值
    private int minAge ;
    private int maxAge ;
    //身高查询区间最大，最小值
    private int minHeight;
    private int maxHeight;
    //体重查询区间最大，最小值
    private int minWeight ;
    private int maxWeight;
    //性别查询条件 (0 表示男， 1 表示女)
    private  int sex;

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

    @Override
    public String toString() {
        return "PeopleSearchGoodsVideo{" +
                "minAge=" + minAge +
                ", maxAge=" + maxAge +
                ", minHeight=" + minHeight +
                ", maxHeight=" + maxHeight +
                ", minWeight=" + minWeight +
                ", maxWeight=" + maxWeight +
                ", sex=" + sex +
                '}';
    }
}
