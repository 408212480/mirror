package com.zxwl.web.service;

import com.zxwl.web.bean.DeviceAD;
import com.zxwl.web.bean.Resource;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.service.GenericService;

import java.util.List;
import java.util.Map;

/**
 * 设备广告 服务类接口
 * Created by generator
 */
public interface DeviceADService extends GenericService<DeviceAD, String> {

    public List<DeviceAD> selectDeviceAD(QueryParam param);

    public PagerResult<DeviceAD> selectDeviceADPage(QueryParam param);

    public Resource selectResourceById(String id);

    public List<DeviceAD> selectADList(QueryParam param);

    public PagerResult<DeviceAD> selectADListPage(QueryParam param);

    //选择已分配的广告
    public List<DeviceAD> selectADDist(QueryParam param, String areaId);
    public PagerResult<DeviceAD> selectADDistPage(QueryParam param, String areaId);

    public int addADsToArea(String areaId, String[] adIds);

}
