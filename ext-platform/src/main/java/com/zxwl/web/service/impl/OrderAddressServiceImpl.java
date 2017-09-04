package com.zxwl.web.service.impl;

import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.OrderAddress;
import com.zxwl.web.dao.OrderAddressMapper;
import com.zxwl.web.service.QueryService;
import com.zxwl.web.service.impl.AbstractServiceImpl;
import com.zxwl.web.service.OrderAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 店铺信息 服务类实现
 * Created by generator
 */
@Service("orderAddressService")
public class OrderAddressServiceImpl extends AbstractServiceImpl<OrderAddress, String> implements OrderAddressService {

    @Resource
    protected OrderAddressMapper orderAddressMapper;

    @Override
    protected OrderAddressMapper getMapper() {
        return this.orderAddressMapper;
    }


    @Override
    public List<OrderAddress> getAddress(String userId) {
        return  getMapper().getAddress(userId);
    }

    @Override
    public OrderAddress getDefaultAddress(String userId) {
        return getMapper().getDefaultAddress(userId);
    }

    @Override
    public List<OrderAddress> getAddressPager(QueryParam param) {
        return getMapper().getAddressPager(param.getParam().get("userId").toString(),param.getPageIndex(),param.getPageSize());
    }
}
