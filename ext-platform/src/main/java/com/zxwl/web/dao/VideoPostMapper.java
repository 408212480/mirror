package com.zxwl.web.dao;

import com.zxwl.web.dao.GenericMapper;
import com.zxwl.web.bean.VideoPost;

import java.util.Map;

/**
* MyBatis 视频发布 数据映射接口
* Created by generator 
*/
public interface VideoPostMapper extends GenericMapper<VideoPost,String> {

    Map getAllLike(String userId);
}
