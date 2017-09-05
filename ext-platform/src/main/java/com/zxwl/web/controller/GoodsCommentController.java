package com.zxwl.web.controller;

import com.zxwl.web.bean.api.PagerGoodsCommentList;
import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.GoodsComment;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.message.ResponseMessage;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.zxwl.web.service.GoodsCommentService;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static com.zxwl.web.core.message.ResponseMessage.ok;

/**
 * 商品评价表控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/GoodsComment")
@AccessLogger("商品评价表")
@Authorize
public class GoodsCommentController extends GenericController<GoodsComment, String> {

    @Resource
    private  GoodsCommentService goodsCommentService;

    @Override
    public  GoodsCommentService getService() {
        return this.goodsCommentService;
    }

    @RequestMapping(value = "/goods/{goodsId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @AccessLogger("查询列表")
    @Authorize
    public ResponseMessage goodsComment(@PathVariable("goodsId") String goodsId, QueryParam param, HttpServletRequest request) {

        PagerGoodsCommentList pagerGoodsCommentList = new PagerGoodsCommentList();
        pagerGoodsCommentList.setGoodsId(goodsId);
        pagerGoodsCommentList.setPageIndex(param.getPageIndex());
        pagerGoodsCommentList.setPageSize(param.getPageSize());
        return ok(getService().getPagerGoodsCommentList(pagerGoodsCommentList, goodsId, request));
    }
}
