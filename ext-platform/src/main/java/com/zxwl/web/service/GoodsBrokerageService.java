package com.zxwl.web.service;

import com.zxwl.web.bean.Device;
import com.zxwl.web.bean.GoodsBrokerage;
import com.zxwl.web.bean.api.PagerParamApi;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;

import java.util.List;
import java.util.Map;

/**
 * 导购收益 服务类接口
 * Created by generator
 */
public interface GoodsBrokerageService extends GenericService<GoodsBrokerage, String> {
    //获取个人分佣列表
    PagerResult<Map> selectByUserId(PagerParamApi pagerParam, String userId);
//获取排名
List<Map>  getRank();
//获取个人排名 及 收益情况
     Map getOwnerRank(String userId);

}
