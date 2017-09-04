package com.zxwl.web.service.impl;

import com.zxwl.web.bean.VideoPost;
import com.zxwl.web.dao.VideoPostMapper;
import com.zxwl.web.service.VideoPostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 视频发布 服务类实现
 * Created by generator
 */
@Service("videoPostService")
public class VideoPostServiceImpl extends AbstractServiceImpl<VideoPost, String> implements VideoPostService {

    @Resource
    protected VideoPostMapper videoPostMapper;

    @Override
    protected VideoPostMapper getMapper() {
        return this.videoPostMapper;
    }
  
    @Override
    public String insert(VideoPost data) {
        return super.insert(data);
    }
  
    @Override
    public int update(VideoPost data) {
        return super.update(data);
    }
  
    @Override
    public int update(List<VideoPost> data) {
        return super.update(data);
    }

    @Override
    public BigDecimal getAllLike(String userId) {
        Map data = videoPostMapper.getAllLike(userId);
        if (data == null) return BigDecimal.valueOf(0);
        BigDecimal  allLike = BigDecimal.valueOf(data.get("allsum") == null ? 0 : Double.valueOf(String.valueOf(data.get("allsum"))));
        return allLike;
    }
}
