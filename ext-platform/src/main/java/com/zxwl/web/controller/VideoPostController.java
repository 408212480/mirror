package com.zxwl.web.controller;

import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.VideoPost;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.message.ResponseMessage;
import org.springframework.web.bind.annotation.RestController;
import com.zxwl.web.service.VideoPostService;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 视频发布控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/VideoPost")
@AccessLogger("视频发布")
@Authorize(module = "VideoPost")
public class VideoPostController extends GenericController<VideoPost, String> {

    @Resource
    private  VideoPostService videoPostService;

    @Override
    public  VideoPostService getService() {
        return this.videoPostService;
    }
}
