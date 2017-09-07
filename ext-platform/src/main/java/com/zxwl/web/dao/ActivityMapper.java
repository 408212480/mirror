package com.zxwl.web.dao;

import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.dao.GenericMapper;
import com.zxwl.web.bean.Activity;

import java.util.List;

/**
* MyBatis 店铺信息 数据映射接口
* Created by generator 
*/
public interface ActivityMapper extends GenericMapper<Activity,String> {
    List<Activity> selectByUserId(String userId);
    List<Activity> selectByUserIdPager(QueryParam param);
    int userCount(QueryParam param);
}
