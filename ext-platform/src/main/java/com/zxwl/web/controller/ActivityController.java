package com.zxwl.web.controller;

import com.zxwl.web.bean.ActivityDevice;
import com.zxwl.web.bean.UserInfo;
import com.zxwl.web.bean.po.GenericPo;
import com.zxwl.web.bean.po.user.User;
import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.Activity;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.core.utils.WebUtil;
import com.zxwl.web.service.ActivityDeviceService;
import com.zxwl.web.service.UserInfoService;
import com.zxwl.web.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.zxwl.web.service.ActivityService;

import javax.annotation.Resource;

import java.util.List;

import static com.zxwl.web.core.message.ResponseMessage.ok;

/**
 * 店铺信息控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/Activity")
@AccessLogger("店铺信息")
@Authorize(module = "activity")
public class ActivityController extends GenericController<Activity, String> {

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


    /**
     * 查询列表,并返回查询结果
     *
     * @param param 查询参数 {@link QueryParam}
     * @return 查询结果, 如果参数指定了分页(默认指定)将返回格式如:{total:数据总数,data:[{}]}的数据.
     * 否则返回格式[{}]
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @AccessLogger("查询列表")
    @Authorize(action = "R")
    public ResponseMessage extendList(QueryParam param) {
        // 获取条件查询
        Object data;
        if (!param.isPaging())//不分页
            data = getService().select(param);
        else
            data = getService().selectPager(param);
        return ok(data)
                .include(getPOType(), param.getIncludes())
                .exclude(getPOType(), param.getExcludes())
                .onlyData();
    }

    @RequestMapping(value = "/push/{id}", method = RequestMethod.PUT)
    @AccessLogger("推送活动")
    @Authorize(action = "U")
    public ResponseMessage push(@PathVariable("id") String id) {
        Activity activity = new Activity();
        activity.setStatus(Activity.status_pushed);
        return super.update(id, activity);
    }

    @RequestMapping(value = "/disabled/{id}", method = RequestMethod.PUT)
    @AccessLogger("禁用活动")
    @Authorize(action = "U")
    public ResponseMessage disabled(@PathVariable("id") String id) {
        Activity activity = new Activity();
        activity.setStatus(Activity.status_disabled);
        return super.update(id, activity);
    }


    @RequestMapping(value = "/release", method = RequestMethod.POST)
    @AccessLogger("发布活动")
    @Authorize(action = "C")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseMessage add(@RequestBody ActivityDevice object) {
        List<String> userIds = object.getUserIds();
        UserInfo user=userInfoService.selectByUserId(WebUtil.getLoginUser().getId());
        if (user.getActivityCount()==0){
            return ResponseMessage.error("本月发布活动次数用尽,发布失败");
        }
        try {
            for (String userId : userIds) {
                object.setCreatorId(user.getId());
                object.setUserId(userId);
                object.setId(GenericPo.createUID());
                activityDeviceService.insert(object);
            }
        } catch (Exception e) {
            return ResponseMessage.error("发布失败");
        }
        user.setActivityCount((user.getActivityCount()-1));
        userInfoService.update(user);
        return ok();
    }
}
