package com.zxwl.web.dao;

import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.dao.GenericMapper;
import com.zxwl.web.bean.GoodsOrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * MyBatis 商品订单信息 数据映射接口
 * Created by generator
 */
public interface GoodsOrderInfoMapper extends GenericMapper<GoodsOrderInfo, String> {
    List<GoodsOrderInfo>  getOrderInfo(QueryParam param);
    List<GoodsOrderInfo> getOrderInfoPager(QueryParam param);
    List<GoodsOrderInfo> allOrderList(@Param("userId") String userId,@Param("pageIndex")int pageIndex,@Param("pageSize")int pageSize);
    List<GoodsOrderInfo> orderList(@Param("orderStatus") int orderStatus,@Param("userId") String userId,@Param("pageIndex")int pageIndex,@Param("pageSize")int pageSize);
    GoodsOrderInfo detail(@Param("orderId") String orderId);
    String goodsImg(GoodsOrderInfo goodsOrderInfo);
    //商品已购数量
    int buySum ( String goodsId);
}
