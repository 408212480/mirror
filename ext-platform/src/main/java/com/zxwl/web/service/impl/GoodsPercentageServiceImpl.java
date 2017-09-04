package com.zxwl.web.service.impl;

import com.zxwl.web.bean.*;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.dao.*;
import com.zxwl.web.service.GoodsPercentageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 商品百分比 服务类实现
 * Created by generator
 */
@Service("goodsPercentageService")
public class GoodsPercentageServiceImpl extends AbstractServiceImpl<GoodsPercentage, String> implements GoodsPercentageService {

    @Resource
    protected GoodsPercentageMapper goodsPercentageMapper;

    @Resource
    protected GoodsDividendMapper goodsDividendMapper;

    @Override
    protected GoodsPercentageMapper getMapper() {
        return this.goodsPercentageMapper;
    }
  
    @Override
    public String insert(GoodsPercentage data) {
        return super.insert(data);
    }
  
    @Override
    public int update(GoodsPercentage data) {
        return super.update(data);
    }
  
    @Override
    public int update(List<GoodsPercentage> data) {
        return super.update(data);
    }

    @Override
    public BigDecimal getGoodsPricePercentage(String videoId) {

        Map data = getMapper().getGoodsPricePercentage(videoId);
        if (data == null) return BigDecimal.valueOf(0.0);
        BigDecimal p = BigDecimal.valueOf(0.001);
        BigDecimal price = BigDecimal.valueOf(data.get("price") == null ? 0.0 : Double.valueOf(String.valueOf(data.get("price"))));
        BigDecimal percentage = BigDecimal.valueOf(data.get("percentage") == null ? 0.0 : Double.valueOf(String.valueOf(data.get("percentage"))));
        BigDecimal total = percentage.multiply(price).multiply(p);

        return total;
    }

    //获取分佣商品信息列表
    @Override
    public List<GoodsDividend> selectGoodsDividend(QueryParam param) {
        List<GoodsDividend> goodsDividends;
        if(param.isPaging()){
            goodsDividends = goodsDividendMapper.selectGoodsDividendPage(param);
        }
        goodsDividends = goodsDividendMapper.selectGoodsDividend(param);
        List<GoodsDividend> goodsOrderInfos = goodsDividendMapper.selectGoodsOrderInfo(param);
        for(GoodsDividend gd: goodsDividends){
            for(GoodsDividend gi:goodsOrderInfos){
                if(gd.getId() != null && gd.getId().equals(gi.getId())){
                    gd.setDividend(gi.getDividend());
                    gd.setDividendCount(gi.getDividendCount());
                    gd.setBuyedCount(gi.getBuyedCount());
                    break;
                }
            }
        }
        return goodsDividends;
    }

    @Override
    public PagerResult<GoodsDividend> selectGoodsDividendPage(QueryParam param) {
        PagerResult<GoodsDividend> pagerResult = new PagerResult<>();
        param.setPaging(false);
        int total = selectGoodsDividend(param).size();
        pagerResult.setTotal(total);
        if (total == 0) {
            pagerResult.setData(new ArrayList<>());
        } else {
            //根据实际记录数量重新指定分页参数
            param.setPaging(true);
            pagerResult.setData(selectGoodsDividend(param));
        }
        return pagerResult;
    }

    @Override
    public List<UserDividend> selectUserDividend(QueryParam param) {
        List<UserDividend> userDividendList;
        if(param.isPaging()){
            userDividendList = goodsDividendMapper.selectUserDividendPage(param);
        }
        userDividendList = goodsDividendMapper.selectUserDividend(param);
        return userDividendList;
    }

    @Override
    public PagerResult<UserDividend> selectUserDividendPage(QueryParam param) {
        PagerResult<UserDividend> pagerResult = new PagerResult<>();
        param.setPaging(false);
        List<UserDividend> userDividendList = goodsDividendMapper.selectUserDividend(param);
        int total = userDividendList.size();
        pagerResult.setTotal(total);
        if (total == 0) {
            pagerResult.setData(new ArrayList<>());
        } else {
            //根据实际记录数量重新指定分页参数
            param.setPaging(true);
            pagerResult.setData(selectUserDividend(param));
        }
        return pagerResult;
    }


    @Override
    public List<GoodsShop> selectGoodsShop() {
        return goodsDividendMapper.selectGoodsShop();
    }
}
