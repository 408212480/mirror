package com.zxwl.web.controller;

import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.DeviceFidGoodsSpec;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.service.DeviceFidService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.zxwl.web.service.DeviceFidGoodsSpecService;

import javax.annotation.Resource;

import static com.zxwl.web.core.message.ResponseMessage.ok;

/**
 * 射频商品规格关联表控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/radiofrequency-dist")
@AccessLogger("射频商品规格关联表")
@Authorize(module = "radiofrequency-dist")
public class DeviceFidGoodsSpecController extends GenericController<DeviceFidGoodsSpec, String> {

    @Resource
    private  DeviceFidGoodsSpecService deviceFidGoodsSpecService;

    @Resource
    public DeviceFidService deviceFidService;

    @Override
    public  DeviceFidGoodsSpecService getService() {
        return this.deviceFidGoodsSpecService;
    }

    @RequestMapping(value = "/BulkInsert", method = RequestMethod.POST)
    @AccessLogger("")
    @Authorize(action = "C")
    @ResponseBody
    public ResponseMessage BulkInsert(@RequestBody String[] deviceIds) {
        // 获取条件查询
        Object data = deviceFidGoodsSpecService.BulkInsert(deviceIds);
        return ResponseMessage.ok(data);
    }

    @RequestMapping(value = "/List", method = RequestMethod.GET)
    @AccessLogger("过滤查询未分配的射频")
    @Authorize(action = "R")
    public ResponseMessage List( QueryParam param) {
        // 获取条件查询
        Object data;
        if (!param.isPaging())//不分页
            data = deviceFidService.selectUndist(param);
        else
            data = deviceFidService.selectUndistPager(param);
        return ok(data)
                .include(getPOType(), param.getIncludes())
                .exclude(getPOType(), param.getExcludes())
                .onlyData();
    }

    @RequestMapping(value = "/shopdevicefid/{shopId}", method = RequestMethod.GET)
    @AccessLogger("查询列表")
    @Authorize(action = "R")
    public ResponseMessage list(@PathVariable("shopId") String shopId, QueryParam param) {
        Object data = new Object();
        // 获取条件查询
        if (shopId != null && !"".equals(shopId)) {
            if (!param.isPaging()) {
                //不分页
                data = getService().selectByShopId(param, shopId);
            } else
                data = getService().selectByShopIdPager(param, shopId);
        }
        return ok(data)
                .include(getPOType(), param.getIncludes())
                .exclude(getPOType(), param.getExcludes())
                .onlyData();
    }
    /**
     * 重置设备分配
     *
     * @param
     * @return 返回删除结果
     */
    @RequestMapping(value = "/reset/{shopId}", method = RequestMethod.DELETE)
    @AccessLogger("重置射频分配")
    @Authorize(action = "D")
    @Transactional
    public ResponseMessage reset(@PathVariable("shopId") String shopId) {
         getService().deleteAll(shopId);
        return ok();
    }
}
