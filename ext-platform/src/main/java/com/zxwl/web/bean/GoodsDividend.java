package com.zxwl.web.bean;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/7/31.
 */
public class GoodsDividend{

    //导购id
    private String id;
    //服装编码
    private String goodsId;
    //商品名称
    private String title;
    //所属店铺名称
    private String shopName;
    //分佣比例
    private float percentage;
    //分佣总金额
    private BigDecimal dividend;
    //购买总次数
    private int buyedCount;
    //分佣人数
    private  int dividendCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public BigDecimal getDividend() {
        return dividend;
    }

    public void setDividend(BigDecimal dividend) {
        this.dividend = dividend;
    }

    public int getBuyedCount() {
        return buyedCount;
    }

    public void setBuyedCount(int buyedCount) {
        this.buyedCount = buyedCount;
    }

    public int getDividendCount() {
        return dividendCount;
    }

    public void setDividendCount(int dividendCount) {
        this.dividendCount = dividendCount;
    }
}
