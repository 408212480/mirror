package com.zxwl.web.dao;

import com.zxwl.web.dao.GenericMapper;
import com.zxwl.web.bean.ShopDevice;

import java.util.List;

/**
* MyBatis 店铺设备关联表 数据映射接口
* Created by generator 
*/
public interface ShopDeviceMapper extends GenericMapper<ShopDevice,String> {

    public List<ShopDevice> selectByShopId(String shopId);

    public int deleteByShopId(String shopId);

}
