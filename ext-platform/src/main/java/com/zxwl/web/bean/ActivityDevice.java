package com.zxwl.web.bean;

import com.zxwl.web.bean.po.GenericPo;

import java.util.List;

/**
 * 设备活动
 * Created by generator Aug 14, 2017 7:11:09 AM
 */
public class ActivityDevice extends GenericPo<String> {
    //活动id
    private String activityid;
//    //设备id
//    private String deviceid;

    private String creatorId;

    private String userId;

    private List<String> userIds;

    /**
     * 获取 活动id
     *
     * @return String 活动id
     */
    public String getActivityid() {
        return this.activityid;
    }

    /**
     * 设置 活动id
     */
    public void setActivityid(String activityid) {
        this.activityid = activityid;
    }
//
//    /**
//     * 获取 设备id
//     *
//     * @return String 设备id
//     */
//    public String getDeviceid() {
//        return this.deviceid;
//    }
//
//    /**
//     * 设置 设备id
//     */
//    public void setDeviceid(String deviceid) {
//        this.deviceid = deviceid;
//    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public interface Property extends GenericPo.Property {
        //活动id
        String activityid = "activityid";
        //设备id
        String deviceid = "deviceid";
        //创建者id
        String creatorId = "creatorId";
        //接收用户id
        String userId = "userId";

    }
}