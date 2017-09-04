package com.zxwl.web.service;

import com.zxwl.web.bean.OrderAddress;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.service.GenericService;

import java.util.List;

/**
 * 店铺信息 服务类接口
 * Created by generator
 */
public interface OrderAddressService extends GenericService<OrderAddress, String> {
List<OrderAddress> getAddress(String userId);
    OrderAddress getDefaultAddress(String userId);
    List<OrderAddress> getAddressPager(QueryParam param);
}
