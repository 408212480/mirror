package com.zxwl.web.service.impl;

import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.ActivityDevice;
import com.zxwl.web.dao.ActivityDeviceMapper;
import com.zxwl.web.service.impl.AbstractServiceImpl;
import com.zxwl.web.service.ActivityDeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 设备活动 服务类实现
 * Created by generator
 */
@Service("activityDeviceService")
public class ActivityDeviceServiceImpl extends AbstractServiceImpl<ActivityDevice, String> implements ActivityDeviceService {

    @Resource
    protected ActivityDeviceMapper activityDeviceMapper;

    @Override
    protected ActivityDeviceMapper getMapper() {
        return this.activityDeviceMapper;
    }
  
    @Override
    public String insert(ActivityDevice data) {
        return super.insert(data);
    }
  

}
