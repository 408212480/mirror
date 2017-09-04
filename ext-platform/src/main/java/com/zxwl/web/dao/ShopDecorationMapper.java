package com.zxwl.web.dao;

import com.zxwl.web.bean.ShopDecoration;

import java.util.List;
import java.util.Map;

/**
* MyBatis 店铺装修表 数据映射接口
* Created by generator 
*/
public interface ShopDecorationMapper extends GenericMapper<ShopDecoration,String> {

     ShopDecoration selectByShopID(String id);
    //查询店铺信息(客户端)
     Map selectByShopId(String shopId);
     List<ShopDecoration> selectByImg1(String img1);
     List<ShopDecoration> selectByImg2(String img2);
     List<ShopDecoration> selectByImg3(String img3);

     int deleteByShopID(String id);

     ShopDecoration shopView(String id);
}
