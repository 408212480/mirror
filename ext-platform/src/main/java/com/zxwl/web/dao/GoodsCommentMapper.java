package com.zxwl.web.dao;

import com.zxwl.web.bean.api.PagerGoodsCommentList;
import com.zxwl.web.bean.api.PagerParamApi;
import com.zxwl.web.dao.GenericMapper;
import com.zxwl.web.bean.GoodsComment;

import java.util.List;
import java.util.Map;

/**
* MyBatis 商品评价表 数据映射接口
* Created by generator 
*/
public interface GoodsCommentMapper extends GenericMapper<GoodsComment,String> {

    //商品评论列表
    List<Map> commentList(PagerGoodsCommentList pagerGoodsCommentList);
    //商品评论条数
    int totalComment(String goodsId);
    //商品评论列表
    List<Map> userCommentList(PagerGoodsCommentList pagerGoodsCommentList);
    //用户商品评论条数
    int totalUserComment(String userId);


}
