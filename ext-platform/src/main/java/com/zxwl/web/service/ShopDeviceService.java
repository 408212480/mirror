package com.zxwl.web.service;

import com.zxwl.web.bean.Device;
import com.zxwl.web.bean.ShopDevice;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.service.GenericService;

import java.util.List;

/**
 * 店铺设备关联表 服务类接口
 * Created by generator
 */
public interface ShopDeviceService extends GenericService<ShopDevice, String> {

    public List<ShopDevice> selectByShopId(QueryParam param, String shopId);

    public PagerResult<ShopDevice> selectByShopIdPager(QueryParam param, String shopId);

    public List<Device> selectUndist(QueryParam param);

    public PagerResult<Device> selectUndistPager(QueryParam param);

    public int deleteShopInArea(String areaId);
}
