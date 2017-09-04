package com.zxwl.web.dao;

import com.zxwl.web.bean.Device;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;

import java.util.List;

/**
* MyBatis 设备管理 数据映射接口
* Created by generator 
*/
public interface DeviceMapper extends GenericMapper<Device,String> {

    public List<Device> selectNotInShopDevice();

    public List<Device> selectNotInShopDevicePager(QueryParam param);


}
