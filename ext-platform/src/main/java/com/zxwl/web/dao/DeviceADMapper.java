package com.zxwl.web.dao;

import com.zxwl.web.bean.Resource;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.DeviceAD;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* MyBatis 设备广告 数据映射接口
* Created by wuei
*/
public interface DeviceADMapper extends GenericMapper<DeviceAD,String> {

    public List<DeviceAD> selectDeviceAD(QueryParam param);

    public List<DeviceAD> selectDeviceADPage(QueryParam param);

    public List<DeviceAD> selectADList(QueryParam param);
    public List<DeviceAD> selectADListPage(QueryParam param);
    //选择已分配的广告
    public List<DeviceAD> selectADDist(ArrayList<String> id);

    public List<DeviceAD> selectADDistPage(@Param("pageIndex")int pageIndex, @Param("pageSize")int pageSize, List<String> list);

    public Resource selectResourceById(String id);

}
