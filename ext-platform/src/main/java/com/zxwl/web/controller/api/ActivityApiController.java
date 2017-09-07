package com.zxwl.web.controller.api;

import com.zxwl.web.bean.Activity;
import com.zxwl.web.bean.ActivityDevice;
import com.zxwl.web.bean.UserInfo;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.po.GenericPo;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.core.utils.WebUtil;
import com.zxwl.web.service.ActivityDeviceService;
import com.zxwl.web.service.ActivityService;
import com.zxwl.web.service.UserInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.zxwl.web.core.message.ResponseMessage.ok;

/**
 * 店铺信息控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/api/Activity")
@AccessLogger("活动信息")
@Authorize
public class ActivityApiController extends GenericController<Activity, String> {

    @Resource
    private ActivityService activityService;
    @Resource
    private ActivityDeviceService activityDeviceService;
    @Resource
    private UserInfoService userInfoService;

    @Override
    public ActivityService getService() {
        return this.activityService;
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @AccessLogger("获取活动列表")
    public ResponseMessage list(QueryParam param){
        String userId=WebUtil.getLoginUser().getId();
        param.getParam().put("user_id",userId);

        Object data;
        if (!param.isPaging())//不分页
            data = getService().selectByUserId(userId);
        else
            data = getService().selectByUserIdPager(param);
        return ok(data)
                .include(getPOType(), param.getIncludes())
                .exclude(getPOType(), param.getExcludes());
    }
}
