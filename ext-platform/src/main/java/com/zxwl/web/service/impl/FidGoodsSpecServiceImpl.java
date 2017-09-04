package com.zxwl.web.service.impl;

import com.zxwl.web.bean.common.DeleteParam;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.FidGoodsSpec;
import com.zxwl.web.core.exception.BusinessException;
import com.zxwl.web.core.exception.NotFoundException;
import com.zxwl.web.dao.FidGoodsSpecMapper;
import com.zxwl.web.service.QueryService;
import com.zxwl.web.service.UpdateService;
import com.zxwl.web.service.impl.AbstractServiceImpl;
import com.zxwl.web.service.FidGoodsSpecService;
import org.hsweb.ezorm.core.dsl.Delete;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 店铺信息 服务类实现
 * Created by generator
 */
@Service("fidGoodsSpecService")
public class FidGoodsSpecServiceImpl extends AbstractServiceImpl<FidGoodsSpec, String> implements FidGoodsSpecService {

    @Resource
    protected FidGoodsSpecMapper fidGoodsSpecMapper;

    @Override
    protected FidGoodsSpecMapper getMapper() {
        return this.fidGoodsSpecMapper;
    }


    @Override
    public void delFidSpec(String id) {
        FidGoodsSpec fidGoodsSpec = QueryService.createQuery(fidGoodsSpecMapper).where(FidGoodsSpec.Property.specId, id).single();
        if (fidGoodsSpec != null) createUpdate().where(FidGoodsSpec.Property.specId, id).set(FidGoodsSpec.Property.specId,"").exec();
        else throw new NotFoundException("该服装还未绑定射频");
    }

    @Override
    public String getFidId(String id) {
        FidGoodsSpec fidGoodsSpec = QueryService.createQuery(fidGoodsSpecMapper).where(FidGoodsSpec.Property.specId, id).single();
        if (fidGoodsSpec != null)
            return fidGoodsSpec.getFidId();
        else
            return null;
    }


    @Override
    public int update(FidGoodsSpec fidGoodsSpec) {
        FidGoodsSpec fidGoodsSpecs1 = QueryService.createQuery(fidGoodsSpecMapper)
                .where(FidGoodsSpec.Property.fidId, fidGoodsSpec.getFidId())
                .single();
        if (fidGoodsSpecs1.getSpecId() != null) throw new BusinessException("该射频已绑定服装，不可重复绑定");
        else
            UpdateService.createUpdate(fidGoodsSpecMapper).set(FidGoodsSpec.Property.fidId, fidGoodsSpec.getFidId()).where(FidGoodsSpec.Property.specId, fidGoodsSpec.getSpecId()).exec();
        return 0;
    }
}
