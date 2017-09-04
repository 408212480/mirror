package com.zxwl.web.bean;

import java.math.BigDecimal;
/**
 * 保存参与分佣用户信息
 * Created by wuei on 2017/8/1.
 *
 */
public class UserDividend {
    //t_user_info id
    private String id;

    //用户帐号
    private String userAccount;

    //昵称
    private String name;

    //被购买次数
    private int buyedCount;

    //获得分佣金额
    private BigDecimal dividend;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBuyedCount() {
        return buyedCount;
    }

    public void setBuyedCount(int buyedCount) {
        this.buyedCount = buyedCount;
    }

    public BigDecimal getDividend() {
        return dividend;
    }

    public void setDividend(BigDecimal dividend) {
        this.dividend = dividend;
    }
}
