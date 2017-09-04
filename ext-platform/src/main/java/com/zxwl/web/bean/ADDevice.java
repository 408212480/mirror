package com.zxwl.web.bean;

import com.zxwl.web.bean.po.GenericPo;

/**
 * Created by Administrator on 2017/8/10.
 */
public class ADDevice extends GenericPo<String> {
    private String adId;
    private String deviceId;
    private int status;

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
