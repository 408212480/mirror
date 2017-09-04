package com.zxwl.web.dao;

import com.zxwl.web.bean.ResourceAssociation;

import java.util.List;
import java.util.Map;

/**
* MyBatis 视频存储 数据映射接口
* Created by generator 
*/
public interface ResourceAssociationMapper extends GenericMapper<ResourceAssociation,String> {

    String getGoodsImgs( String goodsId);

}
