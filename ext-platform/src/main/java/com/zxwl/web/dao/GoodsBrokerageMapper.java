package com.zxwl.web.dao;

import com.zxwl.web.bean.GoodsBrokerage;
import com.zxwl.web.bean.Shop;
import com.zxwl.web.bean.api.PagerParamApi;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.service.GoodsBrokerageService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* MyBatis 导购收益 数据映射接口
* Created by generator 
*/
public interface GoodsBrokerageMapper extends GenericMapper<GoodsBrokerage,String> {
 /**
  * 总排名
  * @return
  */
   List<Map> getRank();

  Map getOwnerRank(String userId);

  List<Map> selectByUserId(PagerParamApi pagerParam);

    List<Map> dataTotal(String userId);

    List<GoodsBrokerage> selectByUserIdpage(QueryParam param,String userId);
}
