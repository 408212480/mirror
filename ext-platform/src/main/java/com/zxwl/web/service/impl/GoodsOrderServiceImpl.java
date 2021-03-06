package com.zxwl.web.service.impl;

import com.zxwl.pay.common.util.str.StringUtils;
import com.zxwl.web.bean.*;
import com.zxwl.web.bean.common.InsertParam;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.po.GenericPo;
import com.zxwl.web.core.utils.WebUtil;
import com.zxwl.web.dao.GoodsInfoMapper;
import com.zxwl.web.dao.GoodsOrderInfoMapper;
import com.zxwl.web.dao.GoodsOrderMapper;
import com.zxwl.web.dao.OrderAddressMapper;
import com.zxwl.web.service.GoodsInfoSpecService;
import com.zxwl.web.service.impl.AbstractServiceImpl;
import com.zxwl.web.service.GoodsOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 商品订单 服务类实现
 * Created by generator
 */
@Service("goodsOrderService")
public class GoodsOrderServiceImpl extends AbstractServiceImpl<GoodsOrder, String> implements GoodsOrderService {

    @Resource
    private GoodsOrderMapper goodsOrderMapper;
    @Resource
    private GoodsInfoMapper goodsInfoMapper;
    @Resource
    private OrderAddressMapper orderAddressMapper;
    @Resource
    private GoodsOrderInfoMapper goodsOrderInfoMapper;
    @Resource
    private GoodsInfoSpecService goodsInfoSpecService;
    @Override
    protected GoodsOrderMapper getMapper() {
        return this.goodsOrderMapper;
    }
  
    @Override
    public String insert(GoodsOrder data) {
        return super.insert(data);
    }
  
    @Override
    public int  update(GoodsOrder data) {
        return super.update(data);
    }
  
    @Override
    public int  update(List<GoodsOrder> data) {
        return super.update(data);
    }

    @Override
    @Transactional
    public String insert(Map<String, Object> params) {
        DecimalFormat df = new DecimalFormat("######0.00");
        String color=String.valueOf(params.get("color"));
        String size=String.valueOf(params.get("size"));
        String goodsId=String.valueOf(params.get("goodsId"));
        String acount=String.valueOf(params.get("acount"));
        String orderAddressId=String.valueOf(params.get("orderAddressId"));
        String shopId =String.valueOf(params.get("shopId"));
        //获取关联表扩展字段
        GoodsInfoSpec goodsInfoSpec=new GoodsInfoSpec();
        goodsInfoSpec.setColor(color);
        goodsInfoSpec.setSize(size);
        goodsInfoSpec.setGoodsId(goodsId);
        goodsInfoSpec= goodsInfoSpecService.selectOne(goodsInfoSpec);
        GoodsOrderInfo goodsOrderInfo=new GoodsOrderInfo();
        GoodsInfo goodsInfo=goodsInfoMapper.selectByPk(goodsId);
        OrderAddress orderAddress=orderAddressMapper.selectByPk(orderAddressId);
        if (goodsInfo==null)
            return "GoodsInfoNull";
        if(orderAddress==null)
            return "orderAddressNull";
        if(goodsInfoSpec==null)
            return "GoodsInfoSpecNull";
        if(StringUtils.isBlank(shopId))
            return "ShopIdNull";
        if (goodsInfoSpec.getQuality()-Integer.parseInt(acount)<0)
            return "500";
//        计算订单总价
        String totalPrice = df.format(goodsInfo.getPrice() * Double.parseDouble(acount));
        //创建订单
        GoodsOrder goodsOrder=new GoodsOrder();
        goodsOrder.setOrderStatus(GoodsOrder.order_add);
        goodsOrder.setUserId(WebUtil.getLoginUser().getId());
        goodsOrder.setShopId(shopId);
        goodsOrder.setTotalPrice(new BigDecimal(totalPrice));
        goodsOrder.setId(GenericPo.createUID());
        goodsOrder.setGmtCreate(new Date());
        goodsOrder.setGmtModify(new Date());
        getMapper().insert(InsertParam.build(goodsOrder));


        //创建订单项
        goodsOrderInfo.setOrderId(goodsOrder.getId());
        goodsOrderInfo.setGoodsId(goodsInfo.getId());
        goodsOrderInfo.setGoodsName(goodsInfo.getTitle());
        goodsOrderInfo.setPrice(BigDecimal.valueOf(goodsInfo.getPrice()));
        goodsOrderInfo.setGoodsSpec(goodsInfoSpec.getId());
        goodsOrderInfo.setAcount(Integer.parseInt(acount));
        goodsOrderInfo.setId(GenericPo.createUID());
        goodsOrderInfo.setLinkAddress(orderAddress.getLinkAddress());
        goodsOrderInfo.setLinkName(orderAddress.getLinkName());
        goodsOrderInfo.setLinkTel(orderAddress.getLinkTel());
        goodsOrderInfoMapper.insert(InsertParam.build(goodsOrderInfo));
        return goodsOrder.getId();
    }
    @Override
    public List<GoodsOrder> selectByUserId(String userId) {
        return getMapper().selectByUserId(userId);
    }

    @Override
    public List<GoodsOrderInfo> allOrderList(QueryParam queryParam) {
        return goodsOrderInfoMapper.allOrderList(WebUtil.getLoginUser().getId(),queryParam.getPageIndex(),queryParam.getPageSize() );
    }

    @Override
    public List<GoodsOrderInfo> orderList(String orderStatus,QueryParam queryParam) {
        return goodsOrderInfoMapper.orderList(Integer.parseInt(orderStatus),WebUtil.getLoginUser().getId(),queryParam.getPageIndex(),queryParam.getPageSize());
    }

    @Override
    public GoodsOrderInfo detail(String orderId) {
        return goodsOrderInfoMapper.detail(orderId);
    }

    @Override
    public String goodsImg(GoodsOrderInfo goodsOrderInfo) {
        return goodsOrderInfoMapper.goodsImg(goodsOrderInfo);
    }
}
