package com.zxwl.web.service;

import com.zxwl.web.bean.GoodsOrder;
import com.zxwl.web.bean.GoodsOrderInfo;
import com.zxwl.web.bean.common.QueryParam;

import java.util.List;
import java.util.Map;

/**
 * 商品订单 服务类接口
 * Created by generator
 */
public interface GoodsOrderService extends GenericService<GoodsOrder, String> {
    String insert(Map<String,Object> params);
    //客户端查看用户订单情况
    List<GoodsOrder> selectByUserId(String userId);
    List<GoodsOrderInfo> allOrderList( QueryParam queryParam);
    List<GoodsOrderInfo> orderList(String orderStatus, QueryParam queryParam);
    GoodsOrderInfo detail(String orderId);
    String goodsImg(GoodsOrderInfo goodsOrderInfo);
}
