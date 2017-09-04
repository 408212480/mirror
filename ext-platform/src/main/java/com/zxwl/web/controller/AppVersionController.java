package com.zxwl.web.controller;

import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.AppVersion;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.message.ResponseMessage;
import org.springframework.web.bind.annotation.RestController;
import com.zxwl.web.service.AppVersionService;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 版本信息控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/appversion")
@AccessLogger("版本信息")
@Authorize(module = "appversion")
public class AppVersionController extends GenericController<AppVersion, String> {

    @Resource
    private  AppVersionService appVersionService;

    @Override
    public  AppVersionService getService() {
        return this.appVersionService;
    }
}
