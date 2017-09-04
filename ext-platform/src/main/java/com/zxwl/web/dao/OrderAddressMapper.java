package com.zxwl.web.dao;

import com.zxwl.web.dao.GenericMapper;
import com.zxwl.web.bean.OrderAddress;

import java.util.List;

/**
* MyBatis 店铺信息 数据映射接口
* Created by generator 
*/
public interface OrderAddressMapper extends GenericMapper<OrderAddress,String> {
    List<OrderAddress> getAddress(String userId);
    OrderAddress getDefaultAddress(String userId);
    List<OrderAddress> getAddressPager(String userId,int pagerIndex,int pageSize);
}
