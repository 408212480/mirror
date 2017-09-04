package com.zxwl.web.service.impl;

import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.VideoGoodsFit;
import com.zxwl.web.dao.VideoGoodsFitMapper;
import com.zxwl.web.service.impl.AbstractServiceImpl;
import com.zxwl.web.service.VideoGoodsFitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 视频商品符合表 服务类实现
 * Created by generator
 */
@Service("videoGoodsFitService")
public class VideoGoodsFitServiceImpl extends AbstractServiceImpl<VideoGoodsFit, String> implements VideoGoodsFitService {

    @Resource
    protected VideoGoodsFitMapper videoGoodsFitMapper;

    @Override
    protected VideoGoodsFitMapper getMapper() {
        return this.videoGoodsFitMapper;
    }
  
    @Override
    public String insert(VideoGoodsFit data) {
        return super.insert(data);
    }
  
    @Override
    public int update(VideoGoodsFit data) {
        return super.update(data);
    }
  
    @Override
    public int update(List<VideoGoodsFit> data) {
        return super.update(data);
    }
}
