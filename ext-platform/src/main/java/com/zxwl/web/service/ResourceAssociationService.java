package com.zxwl.web.service;

import com.zxwl.web.bean.ResourceAssociation;

import java.util.List;
import java.util.Map;

/**
 * 视频存储 服务类接口
 * Created by generator
 */
public interface ResourceAssociationService extends GenericService<ResourceAssociation, String> {

    //获取商品图片
    String getGoodsImgs( String goodsId);
}
