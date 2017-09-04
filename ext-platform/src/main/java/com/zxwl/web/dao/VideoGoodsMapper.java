package com.zxwl.web.dao;

import com.zxwl.web.dao.GenericMapper;
import com.zxwl.web.bean.VideoGoods;

/**
* MyBatis 视频商品关联表 数据映射接口
* Created by generator 
*/
public interface VideoGoodsMapper extends GenericMapper<VideoGoods,String> {

    public VideoGoods selectByVideoId(String id);

}
