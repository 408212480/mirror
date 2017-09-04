package com.zxwl.web.controller;

import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.GoodsSpecifications;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.message.ResponseMessage;
import org.springframework.web.bind.annotation.RestController;
import com.zxwl.web.service.GoodsSpecificationsService;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 商品规格表控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/GoodsSpecifications")
@AccessLogger("商品规格表")
@Authorize(module = "GoodsSpecifications")
public class GoodsSpecificationsController extends GenericController<GoodsSpecifications, String> {

    @Resource
    private  GoodsSpecificationsService goodsSpecificationsService;

    @Override
    public  GoodsSpecificationsService getService() {
        return this.goodsSpecificationsService;
    }
}
