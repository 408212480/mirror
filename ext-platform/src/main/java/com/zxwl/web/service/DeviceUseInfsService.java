package com.zxwl.web.service;

import com.zxwl.web.bean.DeviceFidGoodsSpec;
import com.zxwl.web.bean.DeviceUseInfs;
import com.zxwl.web.bean.api.PagerParamApi;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 设备使用记录表 服务类接口
 * Created by generator
 */
public interface DeviceUseInfsService extends GenericService<DeviceUseInfs, String> {

    List<DeviceUseInfs> getDeviceInfs( String deviceId);

    public List<DeviceUseInfs> selectByDeviceId(QueryParam param, String deviceId);

    //设备使用历史记录
    PagerResult<Map> deviceHistoryList(PagerParamApi pagerParamApi, String userId);

    public PagerResult<DeviceUseInfs> selectByDeviceIdPager(QueryParam param, String deviceId);

}
