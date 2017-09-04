package com.zxwl.web.controller.api;

import com.zxwl.web.bean.AppVersion;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.service.AppVersionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 版本信息控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/api/appversion")
@AccessLogger("版本信息")
//@Authorize(module = "appversion")
public class AppVersionApiController extends GenericController<AppVersion, String> {

    @Resource
    private  AppVersionService appVersionService;

    @Override
    public  AppVersionService getService() {
        return this.appVersionService;
    }
    @RequestMapping(value = "/checkVersion/{type}",method = RequestMethod.GET)
    @AccessLogger("获取当前版本信息")
    public ResponseMessage checkVersion(@PathVariable("type") int type){
        AppVersion appVersion=getService().checkVersion(type);
        if (appVersion!=null)
            return ResponseMessage.ok(appVersion);
        return ResponseMessage.error("data not found");
    }
}
