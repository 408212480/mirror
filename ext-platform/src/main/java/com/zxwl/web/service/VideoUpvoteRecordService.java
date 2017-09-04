package com.zxwl.web.service;

import com.zxwl.web.bean.VideoUpvoteRecord;

/**
 * 视频点赞记录表 服务类接口
 * Created by generator
 */
public interface VideoUpvoteRecordService extends GenericService<VideoUpvoteRecord, String> {
    //查询用户点赞记录
    VideoUpvoteRecord getVideoUpvoteByVideoId (String videoId, String userId);
    void   deleteUpvoteByVideoUserId(String videoId, String userId);
}
