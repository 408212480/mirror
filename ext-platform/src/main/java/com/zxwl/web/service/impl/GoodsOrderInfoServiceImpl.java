package com.zxwl.web.service.impl;

import com.zxwl.pay.common.util.str.StringUtils;
import com.zxwl.web.bean.GoodsOrder;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.GoodsOrderInfo;
import com.zxwl.web.bean.po.GenericPo;
import com.zxwl.web.dao.GoodsOrderInfoMapper;
import com.zxwl.web.dao.GoodsOrderMapper;
import com.zxwl.web.service.UpdateService;
import com.zxwl.web.service.impl.AbstractServiceImpl;
import com.zxwl.web.service.GoodsOrderInfoService;
import org.h2.command.dml.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 商品订单信息 服务类实现
 * Created by generator
 */
@Service("goodsOrderInfoService")
public class GoodsOrderInfoServiceImpl extends AbstractServiceImpl<GoodsOrderInfo, String> implements GoodsOrderInfoService {

    @Resource
    protected GoodsOrderInfoMapper goodsOrderInfoMapper;
    @Resource
    protected GoodsOrderMapper goodsOrderMapper;

    @Override
    protected GoodsOrderInfoMapper getMapper() {
        return this.goodsOrderInfoMapper;
    }

    @Override
    public String insert(GoodsOrderInfo data) {
        return super.insert(data);
    }

    @Override
    public int update(GoodsOrderInfo data) {
        return super.update(data);
    }

    @Override
    public int update(List<GoodsOrderInfo> data) {
        return super.update(data);
    }


    @Override
    public List<GoodsOrderInfo> getOrderInfo(QueryParam param) {
        return getMapper().getOrderInfo(param);
    }

    @Override
    public List<GoodsOrderInfo> getOrderInfoPager(QueryParam param) {
        return getMapper().getOrderInfoPager(param);
    }

    @Override
    public void sendOrder(GoodsOrderInfo goodsOrderInfo) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!(StringUtils.isBlank(goodsOrderInfo.getOrderExpressCompany()) && StringUtils.isBlank(goodsOrderInfo.getOrderExpressNo())))
            UpdateService.createUpdate(getMapper())
                    .set(GoodsOrderInfo.Property.orderExpressNo, goodsOrderInfo.getOrderExpressNo())
                    .set(GoodsOrderInfo.Property.orderExpressCompany, goodsOrderInfo.getOrderExpressCompany())
                    .set(GoodsOrderInfo.Property.gmtDelivery, new Date())
                    .where(GoodsOrderInfo.Property.orderId, goodsOrderInfo.getOrderId()).exec();
        UpdateService.createUpdate(getMapper())
                .set(GoodsOrderInfo.Property.gmtDelivery, new Date())
                .where(GoodsOrderInfo.Property.orderId, goodsOrderInfo.getOrderId()).exec();
        UpdateService.createUpdate(goodsOrderMapper)
                .set(GoodsOrder.Property.orderStatus, GoodsOrder.order_send)
                .set(GoodsOrder.Property.gmtModify, f.format(new Date()))
                .where(GoodsOrder.Property.id, goodsOrderInfo.getOrderId()).exec();
    }

    @Override
    public void updateByOrderId(GoodsOrderInfo orderInfo) {
        createUpdate().fromBean(orderInfo).where(GoodsOrderInfo.Property.orderId).exec();
    }

    @Override
    public int buySum(String goodsId) {
        return getMapper().buySum(goodsId);
    }
}
