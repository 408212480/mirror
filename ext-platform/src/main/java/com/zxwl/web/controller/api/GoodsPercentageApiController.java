package com.zxwl.web.controller.api;

import com.zxwl.web.bean.GoodsPercentage;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.service.GoodsPercentageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.zxwl.web.core.message.ResponseMessage.ok;

/**
 * 商品百分比控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/api/GoodsPercentage")
@AccessLogger("商品百分比")
//@Authorize(module = "GoodsPercentage")
public class GoodsPercentageApiController extends GenericController<GoodsPercentage, String> {

    @Resource
    private  GoodsPercentageService goodsPercentageService;

    @Override
    public  GoodsPercentageService getService() {
        return this.goodsPercentageService;
    }

    @RequestMapping(value = "/getGoodsPricePercentage", method = RequestMethod.GET)
    @AccessLogger("获取单个视频公开奖励")
//    @Authorize(module = "R")
    public ResponseMessage getGoodsPricePercentage(@RequestParam String videoId) {
        Object data = goodsPercentageService.getGoodsPricePercentage(videoId);
        return ok(data);
    }
}
