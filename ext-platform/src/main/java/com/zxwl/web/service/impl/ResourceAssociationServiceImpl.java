package com.zxwl.web.service.impl;

import com.zxwl.web.bean.ResourceAssociation;
import com.zxwl.web.dao.ResourceAssociationMapper;
import com.zxwl.web.service.ResourceAssociationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 视频存储 服务类实现
 * Created by generator
 */
@Service("resourceAssociationService")
public class ResourceAssociationServiceImpl extends AbstractServiceImpl<ResourceAssociation, String> implements ResourceAssociationService {

    @Resource
    protected ResourceAssociationMapper resourceAssociationMapper;

    @Override
    protected ResourceAssociationMapper getMapper() {
        return this.resourceAssociationMapper;
    }
  
    @Override
    public String insert(ResourceAssociation data) {
        return super.insert(data);
    }
  
    @Override
    public int update(ResourceAssociation data) {
        return super.update(data);
    }
  
    @Override
    public int update(List<ResourceAssociation> data) {
        return super.update(data);
    }

    @Override
    public String getGoodsImgs(String goodsId) {
        return getMapper().getGoodsImgs(goodsId);
    }
}
