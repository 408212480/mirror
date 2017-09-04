package com.zxwl.web.controller;

import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.VideoUpvoteRecord;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.message.ResponseMessage;
import org.springframework.web.bind.annotation.RestController;
import com.zxwl.web.service.VideoUpvoteRecordService;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 视频点赞记录表控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/VideoUpvoteRecord")
@AccessLogger("视频点赞记录表")
@Authorize(module = "VideoUpvoteRecord")
public class VideoUpvoteRecordController extends GenericController<VideoUpvoteRecord, String> {

    @Resource
    private  VideoUpvoteRecordService videoUpvoteRecordService;

    @Override
    public  VideoUpvoteRecordService getService() {
        return this.videoUpvoteRecordService;
    }
}
