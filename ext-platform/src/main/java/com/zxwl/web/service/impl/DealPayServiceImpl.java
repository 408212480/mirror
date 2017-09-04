package com.zxwl.web.service.impl;

import com.zxwl.web.bean.GoodsBrokerage;
import com.zxwl.web.bean.api.PagerParamApi;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.DealPay;
import com.zxwl.web.dao.DealPayMapper;
import com.zxwl.web.service.impl.AbstractServiceImpl;
import com.zxwl.web.service.DealPayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 交易记录 服务类实现
 * Created by generator
 */
@Service("dealPayService")
public class DealPayServiceImpl extends AbstractServiceImpl<DealPay, String> implements DealPayService {

    @Resource
    protected DealPayMapper dealPayMapper;

    @Override
    protected DealPayMapper getMapper() {
        return this.dealPayMapper;
    }
  
    @Override
    public String insert(DealPay data) {
        return super.insert(data);
    }
  
    @Override
    public int update(DealPay data) {
        return super.update(data);
    }
  
    @Override
    public int update(List<DealPay> data) {
        return super.update(data);
    }

    @Override
    public PagerResult<Map> getConsume(PagerParamApi pagerParamApi, String userId) {
        PagerResult<Map> pagerResult = new PagerResult<>();
        List<Map> hashMapList = getMapper().totalConsume(userId);
        int total = hashMapList.size();
        pagerResult.setTotal(total);
        if (total == 0) {
            pagerResult.setData(new ArrayList<>());
        } else {
            //根据实际记录数量重新指定分页参数
            pagerResult.setData(getMapper().getConsume(pagerParamApi));
        }
        return pagerResult;
    }

    @Override
    public PagerResult<Map> getRecharge(PagerParamApi pagerParamApi,String userId) {
        PagerResult<Map> pagerResult = new PagerResult<>();
        List<Map> hashMapList = getMapper().totalRecharge(userId);
        int total = hashMapList.size();
        pagerResult.setTotal(total);
        if (total == 0) {
            pagerResult.setData(new ArrayList<>());
        } else {
            //根据实际记录数量重新指定分页参数
            pagerResult.setData(getMapper().getRecharge(pagerParamApi));
        }
        return pagerResult;
    }
}
