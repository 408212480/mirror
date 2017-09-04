package com.zxwl.web.service.impl;

import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.AppVersion;
import com.zxwl.web.dao.AppVersionMapper;
import com.zxwl.web.service.impl.AbstractServiceImpl;
import com.zxwl.web.service.AppVersionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 版本信息 服务类实现
 * Created by generator
 */
@Service("appVersionService")
public class AppVersionServiceImpl extends AbstractServiceImpl<AppVersion, String> implements AppVersionService {

    @Resource
    protected AppVersionMapper appVersionMapper;

    @Override
    protected AppVersionMapper getMapper() {
        return this.appVersionMapper;
    }
  
    @Override
    public String insert(AppVersion data) {
        return super.insert(data);
    }

    @Override
    public AppVersion checkVersion(int type) {
        return getMapper().checkVersion(type);
    }
}
