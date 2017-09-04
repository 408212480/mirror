package com.zxwl.web.dao;

import com.zxwl.web.bean.ShopDevice;
import com.zxwl.web.dao.GenericMapper;
import com.zxwl.web.bean.DeviceFidGoodsSpec;
import org.json.JSONArray;

import java.util.List;

/**
* MyBatis 射频商品规格关联表 数据映射接口
* Created by generator 
*/
public interface DeviceFidGoodsSpecMapper extends GenericMapper<DeviceFidGoodsSpec,String> {

    public List<ShopDevice> selectByShopId(String shopId);

    public  int BulkInsert(DeviceFidGoodsSpec deviceFidGoodsSpec);

   public  Integer deleteAll(String shopId);

}
