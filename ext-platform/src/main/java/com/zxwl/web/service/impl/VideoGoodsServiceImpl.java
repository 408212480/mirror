package com.zxwl.web.service.impl;

import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.VideoGoods;
import com.zxwl.web.dao.VideoGoodsMapper;
import com.zxwl.web.service.impl.AbstractServiceImpl;
import com.zxwl.web.service.VideoGoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 视频商品关联表 服务类实现
 * Created by generator
 */
@Service("videoGoodsService")
public class VideoGoodsServiceImpl extends AbstractServiceImpl<VideoGoods, String> implements VideoGoodsService {

    @Resource
    protected VideoGoodsMapper videoGoodsMapper;

    @Override
    protected VideoGoodsMapper getMapper() {
        return this.videoGoodsMapper;
    }
  
    @Override
    public String insert(VideoGoods data) {
        return super.insert(data);
    }
  
    @Override
    public int update(VideoGoods data) {
        return super.update(data);
    }
  
    @Override
    public int update(List<VideoGoods> data) {
        return super.update(data);
    }
}
