package com.zxwl.web.dao;

import com.zxwl.web.bean.Device;
import com.zxwl.web.bean.DeviceFid;
import com.zxwl.web.bean.common.QueryParam;

import java.util.List;

/**
* MyBatis 射频管理 数据映射接口
* Created by generator 
*/
public interface DeviceFidMapper extends GenericMapper<DeviceFid,String> {
    List<DeviceFid> getShopFid(String id);
    List<DeviceFid> haveFids(QueryParam queryParam);

    public List<DeviceFid> selectNotInShopDeviceFid();

}
