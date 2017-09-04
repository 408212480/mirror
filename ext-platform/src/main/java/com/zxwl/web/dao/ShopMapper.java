package com.zxwl.web.dao;

import com.zxwl.web.bean.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
* MyBatis 店铺信息 数据映射接口
* Created by generator 
*/
public interface ShopMapper extends GenericMapper<Shop,String> {

    public List<Shop> selectByLogo(String logo);

    public List<Shop> selectByBusinessUrl(String businessUrl);

    public List<Shop> selectByAreaId(String areaId);

    public ArrayList<Shop> selectShopInfoByAreaId(List<String> areaIds);

    public ArrayList<Shop> selectShopInfoByAreaIdPager(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize, List<String> list);

    public Shop selectShopInfoById(String id);
}
