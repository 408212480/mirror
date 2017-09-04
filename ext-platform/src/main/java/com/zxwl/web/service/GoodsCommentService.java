package com.zxwl.web.service;

import com.zxwl.web.bean.GoodsComment;
import com.zxwl.web.bean.api.PagerGoodsCommentList;
import com.zxwl.web.bean.api.PagerParamApi;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.service.GenericService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 商品评价表 服务类接口
 * Created by generator
 */
public interface GoodsCommentService extends GenericService<GoodsComment, String> {

    //商品评论列表
    PagerResult<Map> getPagerGoodsCommentList(PagerGoodsCommentList pagerGoodsCommentList, String goodsId, HttpServletRequest req);

    //商品评论总数
    int totalGoodsComment(String goodsId);

    //用户评论商品列表
    PagerResult<Map> getPagerUserGoodsCommentList(PagerGoodsCommentList pagerGoodsCommentList, String goodsId, HttpServletRequest req);

    //用户评论商品总数
    int totalUserGoodsComment(String userId);

    //根据订单获取用户评论
    GoodsComment selectByOrderId(String orderId);
}
