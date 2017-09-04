package com.zxwl.web.bean;

import com.zxwl.web.bean.po.GenericPo;

/**
 * 店铺信息
 * Created by generator Jul 27, 2017 3:37:20 AM
 */
public class OrderAddress extends GenericPo<String> {
    //收货人
    private String linkName;
    //联系电话
    private String linkTel;
    //省
    private String linkProvince;
    //市
    private String linkCity;
    //区
    private String linkArea;
    //详细地址
    private String linkAddress;
    //用户Id
    private String userId;
    //是否是默认
    private boolean Default;
    //创建时间
    private java.util.Date gmtCreate;
    //修改时间
    private java.util.Date gmtModify;

    /**
     * 获取 收货人
     *
     * @return String 收货人
     */
    public String getLinkName() {
        return this.linkName;
    }

    /**
     * 设置 收货人
     */
    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    /**
     * 获取 联系电话
     *
     * @return String 联系电话
     */
    public String getLinkTel() {
        return this.linkTel;
    }

    /**
     * 设置 联系电话
     */
    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }

    /**
     * 获取 收货地址
     *
     * @return String 收货地址
     */
    public String getLinkAddress() {
        return this.linkAddress;
    }

    /**
     * 设置 收货地址
     */
    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }

    /**
     * 获取 用户Id
     *
     * @return String 用户Id
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * 设置 用户Id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isDefault() {
        return Default;
    }

    public void setDefault(boolean aDefault) {
        this.Default = aDefault;
    }

    /**
     * 获取 创建时间
     *
     * @return java.util.Date 创建时间
     */
    public java.util.Date getGmtCreate() {
        return this.gmtCreate;
    }

    /**
     * 设置 创建时间
     */
    public void setGmtCreate(java.util.Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取 修改时间
     *
     * @return java.util.Date 修改时间
     */
    public java.util.Date getGmtModify() {
        return this.gmtModify;
    }

    /**
     * 设置 修改时间
     */
    public void setGmtModify(java.util.Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getLinkProvince() {
        return linkProvince;
    }

    public void setLinkProvince(String linkProvince) {
        this.linkProvince = linkProvince;
    }

    public String getLinkCity() {
        return linkCity;
    }

    public void setLinkCity(String linkCity) {
        this.linkCity = linkCity;
    }

    public String getLinkArea() {
        return linkArea;
    }

    public void setLinkArea(String linkArea) {
        this.linkArea = linkArea;
    }

    public interface Property extends GenericPo.Property {
        //收货人
        String linkName = "linkName";
        //联系电话
        String linkTel = "linkTel";
        //省
         String linkProvince="linkProvince";
        //市
         String linkCity="linkCity";
        //区
         String linkArea="linkArea";
        //详细地址
        String linkAddress = "linkAddress";
        //用户Id
        String userId = "userId";
        //创建时间
        String gmtCreate = "gmtCreate";
        //修改时间
        String gmtModify = "gmtModify";
    }
}