package com.zxwl.web.dao;

import com.zxwl.web.dao.GenericMapper;
import com.zxwl.web.bean.AppVersion;

/**
* MyBatis 版本信息 数据映射接口
* Created by generator 
*/
public interface AppVersionMapper extends GenericMapper<AppVersion,String> {
    AppVersion checkVersion(int type);
}
