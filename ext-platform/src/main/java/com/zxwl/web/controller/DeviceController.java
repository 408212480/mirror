package com.zxwl.web.controller;

import com.zxwl.web.bean.DeviceUseInfs;
import com.zxwl.web.bean.Shop;
import com.zxwl.web.bean.api.PagerParamApi;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.core.exception.NotFoundException;
import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.Device;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.service.DeviceUseInfsService;
import org.springframework.web.bind.annotation.*;
import com.zxwl.web.service.DeviceService;

import javax.annotation.Resource;
import java.util.List;

import static com.zxwl.web.core.message.ResponseMessage.ok;

/**
 * 设备管理控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/device")
@AccessLogger("设备管理")
@Authorize(module = "device")
public class DeviceController extends GenericController<Device, String> {

    @Resource
    private  DeviceService deviceService;

    @Override
    public  DeviceService getService() {
        return this.deviceService;
    }

    @Resource
    private  DeviceUseInfsService deviceUseInfsService;

    @RequestMapping(value = "/List", method = RequestMethod.GET)
    @AccessLogger("使用记录")
    @Authorize(action = "R")
    public ResponseMessage List(@RequestParam(value="deviceId", required = false) String deviceId) {
        // 获取条件查询
        Object data = deviceUseInfsService.getDeviceInfs(deviceId);
        return ResponseMessage.ok(data);
    }

    @RequestMapping(value = "/usehistory/{deviceId}", method = RequestMethod.GET)
    @AccessLogger("查询列表")
//    @Authorize(action = "R")
    public ResponseMessage list(@PathVariable("deviceId") String deviceId, QueryParam param) {
        PagerParamApi pagerParamApi = new PagerParamApi();
        pagerParamApi.setPageIndex(param.getPageIndex());
        pagerParamApi.setPageSize(param.getPageSize());
        pagerParamApi.setUserId(deviceId);
        Object data = deviceUseInfsService.deviceHistoryList(pagerParamApi, deviceId);
        return ok(data)
                .include(getPOType(), param.getIncludes())
                .exclude(getPOType(), param.getExcludes())
                .onlyData();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @AccessLogger("查询明细")
    @Authorize(action = "R")
    public ResponseMessage info(@PathVariable("id") String id) {
        Device po = getService().selectByPk(id);
        if (po == null)
            throw new NotFoundException("data is not found!");
        return ok(po);
    }

}
