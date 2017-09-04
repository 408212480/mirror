package com.zxwl.web.controller;

import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.DeviceFid;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.core.session.HttpSessionManager;
import com.zxwl.web.core.utils.WebUtil;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.zxwl.web.service.DeviceFidService;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 射频管理控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/radiofrequency")
@AccessLogger("射频管理")
@Authorize(module = "radiofrequency")
public class DeviceFidController extends GenericController<DeviceFid, String> {

    @Resource
    private DeviceFidService deviceFidService;
    @Override
    public DeviceFidService getService() {
        return this.deviceFidService;
    }

    @RequestMapping(value = "/getShopFid", method = RequestMethod.GET)
    @AccessLogger("获取店铺拥有射频列表")
    @Authorize(action = "R")
    public ResponseMessage getShopFid() {
        String id = WebUtil.getLoginUser().getId();
        List<DeviceFid> deviceFids=getService().getShopFid(id);
        return ResponseMessage.ok(deviceFids);
    }
}
