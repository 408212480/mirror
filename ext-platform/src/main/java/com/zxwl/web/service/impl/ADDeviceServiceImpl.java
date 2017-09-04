package com.zxwl.web.service.impl;

import com.zxwl.web.bean.ADDevice;
import com.zxwl.web.bean.Area;
import com.zxwl.web.dao.ADDeviceMapper;
import com.zxwl.web.dao.AreaMapper;
import com.zxwl.web.service.ADDeviceService;
import com.zxwl.web.service.AreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 */
@Service("aDDeviceService")
public class ADDeviceServiceImpl extends AbstractServiceImpl<ADDevice, String> implements ADDeviceService {

    @Resource
    private ADDeviceMapper aDDeviceMapper;

    @Override
    protected ADDeviceMapper getMapper() {
        return this.aDDeviceMapper;
    }
}
