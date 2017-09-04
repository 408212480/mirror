package com.zxwl.web.controller;

import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.ActivityDevice;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.message.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.zxwl.web.service.ActivityDeviceService;

import javax.annotation.Resource;

import java.util.List;

import static com.zxwl.web.core.message.ResponseMessage.created;
import static com.zxwl.web.core.message.ResponseMessage.ok;

/**
 * 设备活动控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/ActivityDevice")
@AccessLogger("设备活动")
@Authorize(module = "ActivityDevice")
public class ActivityDeviceController extends GenericController<ActivityDevice, String> {

    @Resource
    private  ActivityDeviceService activityDeviceService;

    @Override
    public  ActivityDeviceService getService() {
        return this.activityDeviceService;
    }


}
