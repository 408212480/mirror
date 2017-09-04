package com.zxwl.web.controller;

import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.ShopGoods;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.message.ResponseMessage;
import org.springframework.web.bind.annotation.RestController;
import com.zxwl.web.service.ShopGoodsService;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 店铺-商店关系表控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/ShopGoods")
@AccessLogger("店铺-商店关系表")
@Authorize(module = "ShopGoods")
public class ShopGoodsController extends GenericController<ShopGoods, String> {

    @Resource
    private  ShopGoodsService shopGoodsService;

    @Override
    public  ShopGoodsService getService() {
        return this.shopGoodsService;
    }
}
