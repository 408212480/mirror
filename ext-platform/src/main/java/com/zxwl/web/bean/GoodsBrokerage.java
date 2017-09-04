package com.zxwl.web.bean;
import com.zxwl.web.bean.po.BasePo;

import java.math.BigDecimal;

/**
* 导购收益
* Created by generator Jul 24, 2017 12:44:57 AM
*/
public class GoodsBrokerage extends BasePo<String> {
  		//导购设置id
        private String percentageId;
        //user name
        private String userName;
        private String shopName;
        //分佣用户id
        private String userId;

        //user rank
        private int  rank;
        //user imgurl
        private String imgUrl;
  		//购买用户id
        private String buyerId;
  		//订单id
        private String orderId;
  		//获得佣金
        private java.math.BigDecimal dividend;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPercentageId() {
        return percentageId;
    }

    public void setPercentageId(String percentageId) {
        this.percentageId = percentageId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getDividend() {
        return dividend;
    }

    public void setDividend(BigDecimal dividend) {
        this.dividend = dividend;
    }

    public interface Property extends BasePo.Property{
                //导购设置id
                 String rakeId="percentageId";
                 String shopName="shopName";
                 String userName="userName";
                 String rank="rank";
                 String imgUrl="imgUrl";
                 String userId="userId";

                //购买用户id
                 String buyerId="buyerId";
                //订单id
                 String orderId="orderId";
                //获得佣金
                 String brokerage="dividend";
    	}
}