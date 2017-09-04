package com.zxwl.web.dao;

import com.zxwl.web.bean.DealPay;
import com.zxwl.web.bean.api.PagerParamApi;

import java.util.List;
import java.util.Map;

/**
* MyBatis 交易记录 数据映射接口
* Created by generator 
*/
public interface DealPayMapper extends GenericMapper<DealPay,String> {
//消费明细
    List<Map> getConsume(PagerParamApi pagerParamApi);
//充值明细
    List<Map> getRecharge(PagerParamApi pagerParamApi);

    //消费明细
    List<Map> totalConsume(String userId);
    //充值明细
    List<Map> totalRecharge(String userId);
}
