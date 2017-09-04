package com.zxwl.web.service;

import com.zxwl.web.bean.VideoPost;
import com.zxwl.web.service.GenericService;

import java.math.BigDecimal;

/**
 * 视频发布 服务类接口
 * Created by generator
 */
public interface VideoPostService extends GenericService<VideoPost, String> {

    BigDecimal getAllLike(String userId);
}
