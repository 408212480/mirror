package com.zxwl.web.bean;

import com.zxwl.web.bean.po.GenericPo;

import java.math.BigDecimal;

/**
 * 商品订单
 * Created by generator Jul 24, 2017 12:55:12 AM
 */
public class GoodsOrder extends GenericPo<String> {
    //提交时间
    private java.util.Date submitTime;
    //订单状态
    private int orderStatus;
    //
    private String remarks;
    //店铺ID
    private String shopId;
    //用户ID
    private String userId;
    //支付类型
    private int payType;
    //总价
    private java.math.BigDecimal totalPrice;
    //
    private java.util.Date gmtCreate;
    //
    private java.util.Date gmtModify;
    //
    private String lastChangeUser;

    //订单状态标识
    public static final int order_add = 1;//未支付
    public static final int order_payed = 2;// 待发货
    public static final int order_send = 3;//待收货
    public static final int order_received = 4;//待评价
    public static final int order_cancled = 5;//退款
    public static final int order_closed = 99;//关闭
    /**
     * 获取 提交时间
     *
     * @return java.util.Date 提交时间
     */
    public java.util.Date getSubmitTime() {
        return this.submitTime;
    }

    /**
     * 设置 提交时间
     */
    public void setSubmitTime(java.util.Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * 获取 订单状态
     *
     * @return int 订单状态
     */
    public int getOrderStatus() {
        return this.orderStatus;
    }

    /**
     * 设置 订单状态
     */
    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取
     *
     * @return String
     */
    public String getRemarks() {
        return this.remarks;
    }

    /**
     * 设置
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取 店铺ID
     *
     * @return String 店铺ID
     */
    public String getShopId() {
        return this.shopId;
    }

    /**
     * 设置 店铺ID
     */
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取 用户ID
     *
     * @return String 用户ID
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * 设置 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取 支付类型
     *
     * @return int 支付类型
     */
    public int getPayType() {
        return this.payType;
    }

    /**
     * 设置 支付类型
     */
    public void setPayType(int payType) {
        this.payType = payType;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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



    public interface Property extends GenericPo.Property {
        //提交时间
        String submitTime = "submitTime";
        //订单状态
        String orderStatus = "orderStatus";
        //
        String remarks = "remarks";
        //店铺ID
        String shopId = "shopId";
        //用户ID
        String userId = "userId";
        //支付类型
        String payType = "payType";
        //总价
        String totalPrice = "totalPrice";
        //
        String gmtCreate = "gmtCreate";
        //
        String gmtModify = "gmtModify";
        //
        String lastChangeUser = "lastChangeUser";
    }
}