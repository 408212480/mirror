package com.zxwl.web.service;

import com.zxwl.web.bean.DeviceFid;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;

import java.util.List;

/**
 * 射频管理 服务类接口
 * Created by generator
 */
public interface DeviceFidService extends GenericService<DeviceFid, String> {
    List<DeviceFid> getShopFid(String id);

    public List<DeviceFid> selectUndist(QueryParam param);

    public PagerResult<DeviceFid> selectUndistPager(QueryParam param);
}
