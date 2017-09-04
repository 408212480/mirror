package com.zxwl.web.service.impl;

import com.zxwl.web.bean.GoodsBrokerage;
import com.zxwl.web.bean.api.PagerParamApi;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.dao.GoodsBrokerageMapper;
import com.zxwl.web.service.GoodsBrokerageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 导购收益 服务类实现
 * Created by generator
 */
@Service("goodsBrokerageService")
public class GoodsBrokerageServiceImpl extends AbstractServiceImpl<GoodsBrokerage, String> implements GoodsBrokerageService {

    @Resource
    protected GoodsBrokerageMapper goodsBrokerageMapper;

    @Override
    protected GoodsBrokerageMapper getMapper() {
        return this.goodsBrokerageMapper;
    }
  
    @Override
    public String insert(GoodsBrokerage data) {
        return super.insert(data);
    }
  
    @Override
    public int update(GoodsBrokerage data) {
        return super.update(data);
    }
  
    @Override
    public int update(List<GoodsBrokerage> data) {
        return super.update(data);
    }

    @Override
    public List<Map>  getRank() {
        return getMapper().getRank();
    }

    @Override
    public Map getOwnerRank(String userId) {
        Map data = getMapper().getOwnerRank(userId);
        if (data == null) return null;
        return data;
    }


    @Override
    public PagerResult<Map> selectByUserId(PagerParamApi pagerParam, String userId) {
        PagerResult<Map> pagerResult = new PagerResult<>();
        List<Map> hashMapList = getMapper().dataTotal(userId);
        int total = hashMapList.size();
        pagerResult.setTotal(total);
        if (total == 0) {
            pagerResult.setData(new ArrayList<>());
        } else {
            //根据实际记录数量重新指定分页参数
            pagerResult.setData(getMapper().selectByUserId(pagerParam));
        }
        return pagerResult;
    }


}
