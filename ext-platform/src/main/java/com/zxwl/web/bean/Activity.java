package com.zxwl.web.bean;

import com.zxwl.web.bean.po.GenericPo;

import java.util.Date;

/**
 * 店铺信息
 * Created by generator Aug 14, 2017 7:09:06 AM
 */
public class Activity extends GenericPo<String> {
    //活动标题
    private String title;
    //活动内容
    private String content;
    //开始时间
    private java.util.Date begintime;
    //结束时间
    private java.util.Date endtime;
    //推送时间
    private java.util.Date pushtime;
    //0 无状态（当前状态可推送）  1 已推送  2 禁用
    private int status;

    public static final int status_init = 0;
    public static final int status_pushed = 1;
    public static final int status_disabled = 2;

//    扩展字段
    private  String viewUrl;//h5页面地址
    /**
     * 获取 活动标题
     *
     * @return String 活动标题
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 设置 活动标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取 活动内容
     *
     * @return String 活动内容
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 设置 活动内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取 开始时间
     *
     * @return java.util.Date 开始时间
     */
    public java.util.Date getBegintime() {
        return this.begintime;
    }

    /**
     * 设置 开始时间
     */
    public void setBegintime(java.util.Date begintime) {
        this.begintime = begintime;
    }

    /**
     * 获取 结束时间
     *
     * @return java.util.Date 结束时间
     */
    public java.util.Date getEndtime() {
        return this.endtime;
    }

    /**
     * 设置 结束时间
     */
    public void setEndtime(java.util.Date endtime) {
        this.endtime = endtime;
    }

    public Date getPushtime() {
        return pushtime;
    }

    public void setPushtime(Date pushtime) {
        this.pushtime = pushtime;
    }

    /**
     * 获取 0 无状态（当前状态可推送）  1 已推送  2 禁用
     *
     * @return int 0 无状态（当前状态可推送）  1 已推送  2 禁用
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * 设置 0 无状态（当前状态可推送）  1 已推送  2 禁用
     */
    public void setStatus(int status) {
        this.status = status;
    }

    public String getViewUrl() {
        return viewUrl;
    }

    public void setViewUrl(String viewUrl) {
        this.viewUrl = viewUrl;
    }

    public interface Property extends GenericPo.Property {
        //活动标题
        String title = "title";
        //活动内容
        String content = "content";
        //开始时间
        String begintime = "begintime";
        //结束时间
        String endtime = "endtime";
        //0 无状态（当前状态可推送）  1 已推送  2 禁用
        String status = "status";
    }
}