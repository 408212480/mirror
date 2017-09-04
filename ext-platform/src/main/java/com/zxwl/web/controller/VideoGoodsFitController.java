package com.zxwl.web.controller;

import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.VideoGoodsFit;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.message.ResponseMessage;
import org.springframework.web.bind.annotation.RestController;
import com.zxwl.web.service.VideoGoodsFitService;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 视频商品符合表控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/VideoGoodsFit")
@AccessLogger("视频商品符合表")
@Authorize(module = "VideoGoodsFit")
public class VideoGoodsFitController extends GenericController<VideoGoodsFit, String> {

    @Resource
    private  VideoGoodsFitService videoGoodsFitService;

    @Override
    public  VideoGoodsFitService getService() {
        return this.videoGoodsFitService;
    }
}
