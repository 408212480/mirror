package com.zxwl.web.service;

import com.zxwl.web.bean.Activity;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.service.GenericService;

import java.util.List;

/**
 * 店铺信息 服务类接口
 * Created by generator
 */
public interface ActivityService extends GenericService<Activity, String> {
    List<Activity> selectByUserId(String userId);

    PagerResult<Activity> selectByUserIdPager(QueryParam param);
}
