package com.zxwl.web.service.impl;

import com.zxwl.web.bean.Device;
import com.zxwl.web.bean.DeviceFid;
import com.zxwl.web.bean.DeviceFidGoodsSpec;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.dao.DeviceFidMapper;
import com.zxwl.web.service.DeviceFidService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 射频管理 服务类实现
 * Created by generator
 */
@Service("deviceFidService")
public class DeviceFidServiceImpl extends AbstractServiceImpl<DeviceFid, String> implements DeviceFidService {

    @Resource
    protected DeviceFidMapper deviceFidMapper;

    @Override
    protected DeviceFidMapper getMapper() {
        return this.deviceFidMapper;
    }

    @Override
    public List<DeviceFid> getShopFid(String id) {
        return getMapper().getShopFid(id);
    }

    @Override
    public List<DeviceFid> selectUndist(QueryParam param) {
        return getMapper().selectNotInShopDeviceFid();
    }

    @Override
    public PagerResult<DeviceFid> selectUndistPager(QueryParam param) {
        PagerResult<DeviceFid> pagerResult = new PagerResult<>();
        param.setPaging(false);
        List<DeviceFid> deviceFids = deviceFidMapper.selectNotInShopDeviceFid();
        int total = deviceFids.size();
        pagerResult.setTotal(total);
        if (total == 0) {
            pagerResult.setData(new ArrayList<>());
        } else {
            //根据实际记录数量重新指定分页参数
            param.setPaging(true);
            pagerResult.setData(deviceFids);
        }
        return pagerResult;
    }
}
