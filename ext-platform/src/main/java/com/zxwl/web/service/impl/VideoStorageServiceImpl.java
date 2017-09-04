package com.zxwl.web.service.impl;

import com.zxwl.web.bean.VideoStorage;
import com.zxwl.web.dao.VideoStorageMapper;
import com.zxwl.web.service.VideoStorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 视频存储 服务类实现
 * Created by generator
 */
@Service("videoStorageService")
public class VideoStorageServiceImpl extends AbstractServiceImpl<VideoStorage, String> implements VideoStorageService {

    @Resource
    protected VideoStorageMapper videoStorageMapper;

    @Override
    protected VideoStorageMapper getMapper() {
        return this.videoStorageMapper;
    }
  
    @Override
    public String insert(VideoStorage data) {
        return super.insert(data);
    }
  
    @Override
    public int update(VideoStorage data) {
        return super.update(data);
    }
  
    @Override
    public int update(List<VideoStorage> data) {
        return super.update(data);
    }
}
