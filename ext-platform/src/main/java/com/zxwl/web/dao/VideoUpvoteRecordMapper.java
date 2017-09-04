package com.zxwl.web.dao;

import com.zxwl.web.bean.VideoUpvoteRecord;
import org.apache.ibatis.annotations.Param;

/**
* MyBatis 视频点赞记录表 数据映射接口
* Created by generator 
*/
public interface VideoUpvoteRecordMapper extends GenericMapper<VideoUpvoteRecord,String> {
//查询用户点赞记录
    VideoUpvoteRecord selectUpvoteByVideoUserId (@Param("videoId") String videoId, @Param("userId") String userId);
    void   deleteUpvoteByVideoUserId(@Param("videoId") String videoId,@Param("userId") String userId);

}
