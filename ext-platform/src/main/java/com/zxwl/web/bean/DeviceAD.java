package com.zxwl.web.bean;

import com.zxwl.web.bean.po.GenericPo;

/**
 * 设备广告
 * Created by generator Aug 7, 2017 6:43:57 AM
 */
public class DeviceAD extends GenericPo<String> {
    //发布用户id
    private String userId;
    //用户名
    private String userName;
    //上传时间
    private java.util.Date uploadTime;
    //生效时间
    private java.util.Date effectiveTime;
    //
    private String deviceId;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    //
    private java.util.Date endTime;


    private String resourceId;
    //广告类型：图片/视频
    private String resourceType;
    //广告类型：0/1
    private int resourceTypeInt;
    //资源文件的md5编码
    private String md5;

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getResourceTypeInt() {
        return resourceTypeInt;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public void setResourceTypeInt(int resourceTypeInt) {
        this.resourceTypeInt = resourceTypeInt;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * 获取 发布用户id
     *
     * @return String 发布用户id
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * 设置 发布用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取 上传时间
     *
     * @return java.util.Date 上传时间
     */
    public java.util.Date getUploadTime() {
        return this.uploadTime;
    }

    /**
     * 设置 上传时间
     */
    public void setUploadTime(java.util.Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    /**
     * 获取 生效时间
     *
     * @return java.util.Date 生效时间
     */
    public java.util.Date getEffectiveTime() {
        return this.effectiveTime;
    }

    /**
     * 设置 生效时间
     */
    public void setEffectiveTime(java.util.Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    /**
     * 获取
     *
     * @return String
     */
    public String getDeviceId() {
        return this.deviceId;
    }

    /**
     * 设置
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 获取
     *
     * @return java.util.Date
     */
    public java.util.Date getEndTime() {
        return this.endTime;
    }

    /**
     * 设置
     */
    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }


    public interface Property extends GenericPo.Property {
        //发布用户id
        String userId = "userId";
        //上传时间
        String uploadTime = "uploadTime";
        //生效时间
        String effectiveTime = "effectiveTime";
        //
        String deviceId = "deviceId";
        //
        String endTime = "endTime";

        String resourceId = "resourceId";

    }
}