package com.zxwl.web.dao;

import com.zxwl.web.bean.GoodsPercentage;
import com.zxwl.web.bean.common.QueryParam;

import java.util.List;
import java.util.Map;

/**
* MyBatis 商品百分比 数据映射接口
* Created by generator 
*/
public interface GoodsPercentageMapper extends GenericMapper<GoodsPercentage,String> {

//     Map<String, Object> getGoodsPricePercentage(String videoId);
     Map getGoodsPricePercentage(String videoId);
}
