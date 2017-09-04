package com.zxwl.web.controller;

import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.VideoGoods;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.message.ResponseMessage;
import org.springframework.web.bind.annotation.RestController;
import com.zxwl.web.service.VideoGoodsService;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 视频商品关联表控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/videoGoods")
@AccessLogger("视频商品关联表")
@Authorize
public class VideoGoodsController extends GenericController<VideoGoods, String> {

    @Resource
    private  VideoGoodsService videoGoodsService;

    @Override
    public  VideoGoodsService getService() {
        return this.videoGoodsService;
    }
}
