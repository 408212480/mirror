package com.zxwl.web.bean;

import com.zxwl.web.bean.po.GenericPo;

/**
 * 视频点赞记录表
 * Created by generator Aug 18, 2017 2:30:19 AM
 */
public class VideoUpvoteRecord extends GenericPo<String> {
    //点赞用户id
    private String userId;

    //点赞用户id
    private String videoId;
    //创建时间
    private java.util.Date createTime;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    /**
     * 获取 点赞用户id
     *
     * @return String 点赞用户id
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * 设置 点赞用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取 创建时间
     *
     * @return java.util.Date 创建时间
     */
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置 创建时间
     */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public interface Property extends GenericPo.Property {
        //点赞用户id
        String userId = "userId";
        //视频id
        String videoId = "videoId";
        //创建时间
        String createTime = "createTime";
    }
}