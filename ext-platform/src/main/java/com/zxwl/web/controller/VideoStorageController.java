package com.zxwl.web.controller;

import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.VideoStorage;
import org.springframework.web.bind.annotation.RestController;
import com.zxwl.web.service.VideoStorageService;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 视频存储控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/VideoStorage")
@AccessLogger("视频存储")
@Authorize(module = "VideoStorage")
public class VideoStorageController extends GenericController<VideoStorage, String> {

    @Resource
    private  VideoStorageService videoStorageService;

    @Override
    public  VideoStorageService getService() {
        return this.videoStorageService;
    }
}
