package com.zxwl.web.service;

import com.zxwl.web.bean.DeviceFidGoodsSpec;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;

import java.util.List;
/**
 * 射频商品规格关联表 服务类接口
 * Created by generator
 */
public interface DeviceFidGoodsSpecService extends GenericService<DeviceFidGoodsSpec, String> {

    //Bulk Insert data
    public  int BulkInsert(String[] deviceIds);

    public List<DeviceFidGoodsSpec> selectByShopId(QueryParam param, String shopId);

    public PagerResult<DeviceFidGoodsSpec> selectByShopIdPager(QueryParam param, String shopId);

    Integer deleteAll(String shopId);
}
