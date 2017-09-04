package com.zxwl.web.service;

import com.zxwl.web.bean.ShopDecoration;

import java.util.List;
import java.util.Map;

/**
 * 店铺装修表 服务类接口
 * Created by generator
 */
public interface ShopDecorationService extends GenericService<ShopDecoration, String> {

    ShopDecoration selectByShopID(String id);

    //查询店铺信息(客户端)
    Map selectByShopId(String shopId);

    List<ShopDecoration> selectByImg1(String img1);

    List<ShopDecoration> selectByImg2(String img2);

    List<ShopDecoration> selectByImg3(String img3);

    int deleteByShopID(String id);

    ShopDecoration shopView(String id);

}
