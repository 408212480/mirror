package com.zxwl.web.bean;

import com.zxwl.web.bean.po.GenericPo;

import java.util.Date;

/**
 * 用户信息
 * Created by generator Jul 18, 2017 8:34:42 AM
 */
public class UserInfo extends GenericPo<String> {
    //
    private String name;
    //
    private Integer age;
    //
    private String sex;

    private Integer status;

    private Double height;
    //
    private Double weight;
    //
    private String telephone;
    //
    private String avatarId;
    //
    private String userId;

    private Integer activityCount;

    private String paymentPassword;
    //用户默认地址id
    private String defaultAddress;
    //
    private java.util.Date gmtCreate;
    //
    private java.util.Date gmtModify;

    //扩展字段
    private String userAccount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getActivityCount() {
        return activityCount;
    }

    public void setActivityCount(Integer activityCount) {
        this.activityCount = activityCount;
    }

    public String getPaymentPassword() {
        return paymentPassword;
    }

    public void setPaymentPassword(String paymentPassword) {
        this.paymentPassword = paymentPassword;
    }

    public String getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(String defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public interface Property extends GenericPo.Property {
        //
        String name = "name";
        //
        String age = "age";
        //
        String high = "high";
        //
        String weight = "weight";
        //
        String telephone = "telephone";
        //
        String imgUrl = "imgUrl";
        //
        String userId = "userId";
        //
        String defaultAddress="defaultAddress";
        //
        String gmtCreate = "gmtCreate";
        //
        String gmtModify = "gmtModify";
        //
        String paymentPassword="paymentPassword";
        //
        String avatarId="avatarId";

        String activityCount = "activity_count";
    }
}