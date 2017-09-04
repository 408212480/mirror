package com.zxwl.web.service;

import com.zxwl.web.bean.Shop;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;

import java.util.ArrayList;
import java.util.List;

/**
 * 店铺信息 服务类接口
 * Created by generator
 */
public interface ShopService extends GenericService<Shop, String> {

     ArrayList<Shop> selectShopInArea(String areaIds);

     PagerResult<Shop> selectShopInAreaPager(QueryParam param, String areaId);

     Shop selectShopInfoById(String id);


}
