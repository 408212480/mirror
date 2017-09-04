package com.zxwl.web.bean;

import com.zxwl.web.bean.po.GenericPo;

/**
 * 商品评价表
 * Created by generator Aug 7, 2017 8:47:16 AM
 */
public class GoodsComment extends GenericPo<String> {
    //
    private int commentLevel;
    //
    private String comment;
    //
    private String goodsId;
    //
    private String userId;

    private String orderId;
    //
    private java.util.Date gmtCreate;
    //
    private java.util.Date gmtModify;
    //
    private String lastChangeUser;
    //recordId
    private String recordId;

    private String[] cImgUrl;

    private String goodsspcInfo;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String[] getcImgUrl() {
        return cImgUrl;
    }

    public void setcImgUrl(String[] cImgUrl) {
        this.cImgUrl = cImgUrl;
    }

    /**
     * 获取
     *
     * @return int
     */
    public int getCommentLevel() {
        return this.commentLevel;
    }

    /**
     * 设置
     */
    public void setCommentLevel(int commentLevel) {
        this.commentLevel = commentLevel;
    }

    /**
     * 获取
     *
     * @return String
     */
    public String getComment() {
        return this.comment;
    }

    /**
     * 设置
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 获取
     *
     * @return String
     */
    public String getGoodsId() {
        return this.goodsId;
    }

    /**
     * 设置
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取
     *
     * @return String
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * 设置
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取
     *
     * @return java.util.Date
     */
    public java.util.Date getGmtCreate() {
        return this.gmtCreate;
    }

    /**
     * 设置
     */
    public void setGmtCreate(java.util.Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取
     *
     * @return java.util.Date
     */
    public java.util.Date getGmtModify() {
        return this.gmtModify;
    }

    /**
     * 设置
     */
    public void setGmtModify(java.util.Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    /**
     * 获取
     *
     * @return String
     */
    public String getLastChangeUser() {
        return this.lastChangeUser;
    }

    /**
     * 设置
     */
    public void setLastChangeUser(String lastChangeUser) {
        this.lastChangeUser = lastChangeUser;
    }

    /**
     * 获取 recordId
     *
     * @return String recordId
     */
    public String getRecordId() {
        return this.recordId;
    }

    /**
     * 设置 recordId
     */
    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    /**
     * 获取 goodsspcInfo
     *
     * @return String goodsspcInfo
     */
    public String getGoodsspcInfo() {
        return this.goodsspcInfo;
    }

    /**
     * 设置 goodsspcInfo
     */
    public void setGoodsspcInfo(String goodsspcInfo) {
        this.goodsspcInfo = goodsspcInfo;
    }

    public interface Property extends GenericPo.Property {
        //
        String commentLevel = "commentLevel";
        //
        String comment = "comment";
        //
        String goodsId = "goodsId";
        //
        String userId = "userId";
        //
        String gmtCreate = "gmtCreate";
        //
        String gmtModify = "gmtModify";
        //
        String lastChangeUser = "lastChangeUser";

        String cImgUrl = "cImgUrl";
        //recordId
        String recordId = "recordId";

        String orderdId = "orderdId";

        //goodsspcInfo
        String goodsspcInfo = "goodsspcInfo";
    }
}