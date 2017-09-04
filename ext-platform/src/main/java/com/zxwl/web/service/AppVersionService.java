package com.zxwl.web.service;

import com.zxwl.web.bean.AppVersion;
import com.zxwl.web.service.GenericService;

/**
 * 版本信息 服务类接口
 * Created by generator
 */
public interface AppVersionService extends GenericService<AppVersion, String> {
AppVersion checkVersion(int type);
}
