package com.zxwl.web.service.impl;

import com.zxwl.web.bean.GoodsSpecifications;
import com.zxwl.web.dao.GoodsSpecificationsMapper;
import com.zxwl.web.service.GoodsSpecificationsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品规格表 服务类实现
 * Created by generator
 */
@Service("goodsSpecificationsService")
public class GoodsSpecificationsServiceImpl extends AbstractServiceImpl<GoodsSpecifications, String> implements GoodsSpecificationsService {

    @Resource
    protected GoodsSpecificationsMapper goodsSpecificationsMapper;

    @Override
    protected GoodsSpecificationsMapper getMapper() {
        return this.goodsSpecificationsMapper;
    }
  
    @Override
    public String insert(GoodsSpecifications data) {
        return super.insert(data);
    }
  
    @Override
    public int update(GoodsSpecifications data) {
        return super.update(data);
    }
  
    @Override
    public int update(List<GoodsSpecifications> data) {
        return super.update(data);
    }
}
