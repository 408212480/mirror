package com.zxwl.web.controller;

import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.GoodsComment;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.message.ResponseMessage;
import org.springframework.web.bind.annotation.RestController;
import com.zxwl.web.service.GoodsCommentService;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 商品评价表控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/GoodsComment")
@AccessLogger("商品评价表")
@Authorize(module = "GoodsComment")
public class GoodsCommentController extends GenericController<GoodsComment, String> {

    @Resource
    private  GoodsCommentService goodsCommentService;

    @Override
    public  GoodsCommentService getService() {
        return this.goodsCommentService;
    }
}
