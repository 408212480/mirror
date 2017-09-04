package com.zxwl.web.service.impl;

import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.VideoUpvoteRecord;
import com.zxwl.web.dao.VideoUpvoteRecordMapper;
import com.zxwl.web.service.impl.AbstractServiceImpl;
import com.zxwl.web.service.VideoUpvoteRecordService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 视频点赞记录表 服务类实现
 * Created by generator
 */
@Service("videoUpvoteRecordService")
public class VideoUpvoteRecordServiceImpl extends AbstractServiceImpl<VideoUpvoteRecord, String> implements VideoUpvoteRecordService {

    @Resource
    protected VideoUpvoteRecordMapper videoUpvoteRecordMapper;

    @Override
    protected VideoUpvoteRecordMapper getMapper() {
        return this.videoUpvoteRecordMapper;
    }
  
    @Override
    public String insert(VideoUpvoteRecord data) {
        return super.insert(data);
    }
  
    @Override
    public int update(VideoUpvoteRecord data) {
        return super.update(data);
    }
  
    @Override
    public int update(List<VideoUpvoteRecord> data) {
        return super.update(data);
    }

    @Override
    public VideoUpvoteRecord getVideoUpvoteByVideoId( String videoId, String userId) {
        return getMapper().selectUpvoteByVideoUserId(videoId,userId);
    }

    @Override
    public void deleteUpvoteByVideoUserId(String videoId, String userId) {
         getMapper().deleteUpvoteByVideoUserId(videoId,userId);
    }


}
