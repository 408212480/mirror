package com.zxwl.web.bean;

import com.zxwl.web.bean.po.GenericPo;

import java.util.Date;
import java.util.List;

/**
 * 店铺设备关联表
 * Created by generator Jul 25, 2017 8:46:28 AM
 */
public class ShopDevice extends GenericPo<String> {
    //店铺id
    private String shopId;
    //设备编码
    private String code;

    //匹配时间
    private Date gmtCreate;

    //匹配时间
    private String distTime;

    public String getDistTime() {
        return distTime;
    }

    public void setDistTime(String distTime) {
        this.distTime = distTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    //设备id
    private String deviceId;

    /**
     * 获取 店铺id
     *
     * @return String 店铺id
     */
    public String getShopId() {
        return this.shopId;
    }

    /**
     * 设置 店铺id
     */
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取 设备id
     *
     * @return String 设备id
     */
    public String getDeviceId() {
        return this.deviceId;
    }

    /**
     * 设置 设备id
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public interface Property extends GenericPo.Property {
        //店铺id
        String shopId = "shopId";
        //设备id
        String deviceId = "deviceId";
    }
}