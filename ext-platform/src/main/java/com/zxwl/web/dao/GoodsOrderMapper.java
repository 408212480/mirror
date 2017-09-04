package com.zxwl.web.dao;

import com.zxwl.web.bean.GoodsOrder;
import com.zxwl.web.bean.GoodsOrderInfo;

import java.util.List;

/**
* MyBatis 商品订单 数据映射接口
* Created by generator 
*/
public interface GoodsOrderMapper extends GenericMapper<GoodsOrder,String> {

    List<GoodsOrder> selectByUserId(String userId);
}
