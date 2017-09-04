package com.zxwl.web.dao;

import com.zxwl.web.bean.GoodsInfo;
import com.zxwl.web.bean.common.QueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* MyBatis 商品信息 数据映射接口
* Created by generator 
*/
public interface GoodsInfoMapper extends GenericMapper<GoodsInfo,String> {

    List<GoodsInfo> selectList(QueryParam param);

    GoodsInfo selectSingleInfo(@Param("uid") String uid);

    //根据视频id，获取视频店铺id
    String selectByVideoId(String id);

}
