package com.zxwl.web.bean;

import com.zxwl.web.bean.po.GenericPo;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品订单信息
 * Created by generator Jul 24, 2017 12:57:40 AM
 */
public class GoodsOrderInfo extends GenericPo<String> {
    //商品ID
    private String goodsId;
    //商品名称
    private String goodsName;
    //数量
    private int acount;
    // 退款申请审核状态：1 待审核 2 通过 3 不通过
    private int refundStatus;
    //价格
    private java.math.BigDecimal price;
    //订单ID
    private String orderId;
    //商品规格
    private String goodsSpec;
    //快递单号
    private String orderExpressNo;
    //快递公司
    private String orderExpressCompany;
    //联系人姓名
    private String linkName;
    //联系电话
    private String linkTel;
    //联系地址
    private String linkAddress;
    //创建时间
    private java.util.Date gmtCreate;
    //上一次修改时间
    private java.util.Date gmtModify;
    //退款申请时间
    private java.util.Date gmtRefund;
    //发货时间
    private java.util.Date gmtDelivery;
    //上一次修改人
    private String lastChangeUser;


    //冗余订单表、商品表、规格表、商店表等部分字段用于查询返回字段
    //提交时间
    private java.util.Date submitTime;
    //订单状态
    private int orderStatus;
    //尺码
    private String size;
    //颜色
    private String color;
    //用户名
    private String username;
    //店铺名称
    private String shopName;
    //总价
    private java.math.BigDecimal totalPrice;
    //
    private String md5;
    //
    private String payType;

    private int pageIndex;

    private int pageSize;

    //退款申请审核状态
    public static  final int refund_status_init=1;//处理中
    public static  final int refund_status_pass=2;//通过
    public static  final int refund_status_failed=3;//不通过

    /**
     * 获取 商品ID
     *
     * @return String 商品ID
     */
    public String getGoodsId() {
        return this.goodsId;
    }

    /**
     * 设置 商品ID
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取 商品名称
     *
     * @return String 商品名称
     */
    public String getGoodsName() {
        return this.goodsName;
    }

    /**
     * 设置 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取 数量
     *
     * @return int 数量
     */
    public int getAcount() {
        return this.acount;
    }

    /**
     * 设置 数量
     */
    public void setAcount(int acount) {
        this.acount = acount;
    }

    /**
     * 获取 价格
     *
     * @return int 价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取 订单ID
     *
     * @return String 订单ID
     */
    public String getOrderId() {
        return this.orderId;
    }

    /**
     * 设置 订单ID
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取 快递单号
     *
     * @return String 快递单号
     */
    public String getOrderExpressNo() {
        return orderExpressNo;
    }

    /**
     * 设置 快递单号
     */
    public void setOrderExpressNo(String orderExpressNo) {
        this.orderExpressNo = orderExpressNo;
    }

    /**
     * 获取 快递公司
     *
     * @return String 快递公司
     */
    public String getOrderExpressCompany() {
        return orderExpressCompany;
    }

    /**
     * 设置 快递公司
     */
    public void setOrderExpressCompany(String orderExpressCompany) {
        this.orderExpressCompany = orderExpressCompany;
    }
    /**
     * 获取 联系人姓名
     *
     * @return String 联系人姓名
     */
    public String getLinkName() {
        return linkName;
    }
    /**
     * 设置 联系人姓名
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
        return linkTel;
    }
    /**
     * 设置 联系电话
     */
    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }
    /**
     * 获取 联系地址
     *
     * @return String 联系地址
     */
    public String getLinkAddress() {
        return linkAddress;
    }
    /**
     * 设置 联系地址
     */
    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }

    /**
     * 获取 创建时间
     *
     * @return java.util.Date
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
     * 获取 上一次修改时间
     *
     * @return java.util.Date
     */
    public java.util.Date getGmtModify() {
        return this.gmtModify;
    }

    /**
     * 设置 上一次修改时间
     */
    public void setGmtModify(java.util.Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    /**
     * 获取 上一次修改人
     *
     * @return String
     */
    public String getLastChangeUser() {
        return this.lastChangeUser;
    }

    /**
     * 设置 上一次修改人
     */
    public void setLastChangeUser(String lastChangeUser) {
        this.lastChangeUser = lastChangeUser;
    }

    /**
     * 获取 商品规格
     *
     * @return String
     */
    public String getGoodsSpec() {
        return goodsSpec;
    }

    /**
     * 设置 商品规格
     */
    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    /**
     * 获取 发货时间
     *
     * @return Date
     */
    public Date getGmtDelivery() {
        return gmtDelivery;
    }
    /**
     * 设置 发货时间
     */
    public void setGmtDelivery(Date gmtDelivery) {
        this.gmtDelivery = gmtDelivery;
    }

    public int getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(int refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Date getGmtRefund() {
        return gmtRefund;
    }

    public void setGmtRefund(Date gmtRefund) {
        this.gmtRefund = gmtRefund;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
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

    public interface Property extends GenericPo.Property {
        //商品ID
        String goodsId = "goodsId";
        //商品名称
        String goodsName = "goodsName";
        //数量
        String acount = "acount";
        //价格
        String price = "price";
        //订单ID
        String orderId = "orderId";
        //快递单号
        String orderExpressNo = "orderExpressNo";
        //快递公司
        String orderExpressCompany = "orderExpressCompany";
        //联系人地址
        String linkName = "linkName";
        //联系电话
        String linkTel = "linkTel";
        //联系地址
        String linkAddress = "linkAddress";
        //创建时间
        String gmtCreate = "gmtCreate";
        //上一次修改时间
        String gmtModify = "gmtModify";
        //上一次修改人
        String gmtDelivery = "gmtDelivery";
        //上一次修改人
        String lastChangeUser = "lastChangeUser";
        //商品规格
        String goodsSpec = "goodsSpec";
        //提交时间
        String submitTime = "submitTime";
        //退款申请时间
        String gmtRefund="gmtRefund";
        //退款申请审核状态
        String refundStatus="refundStatus";
    }
}