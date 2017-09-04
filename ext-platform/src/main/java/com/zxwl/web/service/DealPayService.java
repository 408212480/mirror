package com.zxwl.web.service;

import com.zxwl.web.bean.DealPay;
import com.zxwl.web.bean.api.PagerParamApi;
import com.zxwl.web.bean.common.PagerResult;

import java.util.List;
import java.util.Map;

/**
 * 交易记录 服务类接口
 * Created by generator
 */
public interface DealPayService extends GenericService<DealPay, String> {

    //消费明细
    PagerResult<Map> getConsume(PagerParamApi pagerParamApi, String userId);
    //充值明细
    PagerResult<Map> getRecharge(PagerParamApi pagerParamApi,String userId);

}
