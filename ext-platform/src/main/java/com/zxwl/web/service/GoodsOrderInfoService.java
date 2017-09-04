package com.zxwl.web.service;

import com.zxwl.web.bean.GoodsOrderInfo;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.service.GenericService;

import java.util.List;

/**
 * 商品订单信息 服务类接口
 * Created by generator
 */
public interface GoodsOrderInfoService extends GenericService<GoodsOrderInfo, String> {
    List<GoodsOrderInfo> getOrderInfo(QueryParam param);
    List<GoodsOrderInfo> getOrderInfoPager(QueryParam param);
    void sendOrder(GoodsOrderInfo goodsOrderInfo);
    void updateByOrderId(GoodsOrderInfo orderInfo);
//已购买数量
    int buySum ( String goodsId);
}
