package com.zxwl.web.service.impl;

import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.Activity;
import com.zxwl.web.dao.ActivityMapper;
import com.zxwl.web.service.impl.AbstractServiceImpl;
import com.zxwl.web.service.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 店铺信息 服务类实现
 * Created by generator
 */
@Service("activityService")
public class ActivityServiceImpl extends AbstractServiceImpl<Activity, String> implements ActivityService {

    @Resource
    protected ActivityMapper activityMapper;

    @Override
    protected ActivityMapper getMapper() {
        return this.activityMapper;
    }
  
    @Override
    public String insert(Activity data) {
        return super.insert(data);
    }
  

}
