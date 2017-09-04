package com.zxwl.web.dao;

import com.zxwl.web.bean.DeviceUseInfs;
import com.zxwl.web.bean.api.PagerParamApi;
import com.zxwl.web.bean.common.QueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* MyBatis 设备使用记录表 数据映射接口
* Created by generator 
*/
public interface DeviceUseInfsMapper extends GenericMapper<DeviceUseInfs,String> {
    List<DeviceUseInfs> getDeviceInfs(String deviceId);


    // 通过设备ID查询设备使用记录
    public List<Map> selectDeviceUseHistoryList(PagerParamApi pagerParamApi);

    int totalDeviceUseHistoryList( String userId);


}
