package com.zxwl.web.service.impl;

import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.Activity;
import com.zxwl.web.dao.ActivityMapper;
import com.zxwl.web.service.impl.AbstractServiceImpl;
import com.zxwl.web.service.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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


    @Override
    public List<Activity> selectByUserId(String userId) {
        return getMapper().selectByUserId(userId);
    }

    @Override
    public PagerResult<Activity> selectByUserIdPager(QueryParam param) {

        PagerResult<Activity> pagerResult = new PagerResult<>();
        param.setPaging(false);
        int total = activityMapper.userCount(param);
        pagerResult.setTotal(total);
        if (total == 0) {
            pagerResult.setData(new ArrayList<>());
        } else {
            //根据实际记录数量重新指定分页参数
            param.rePaging(total);
            pagerResult.setData(activityMapper.selectByUserIdPager(param));
        }

        return pagerResult;
    }
}
