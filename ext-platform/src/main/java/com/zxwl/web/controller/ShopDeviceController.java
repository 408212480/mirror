package com.zxwl.web.controller;

import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.ShopDevice;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.service.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.zxwl.web.service.ShopDeviceService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;

import static com.zxwl.web.core.message.ResponseMessage.created;
import static com.zxwl.web.core.message.ResponseMessage.ok;

/**
 * 店铺设备关联表控制器
 * Created by wuei
 */
@RestController
@RequestMapping(value = "/devicedist")
@AccessLogger("店铺设备关联表")
@Authorize(module = "devicedist")
public class ShopDeviceController extends GenericController<ShopDevice, String> {

    @Resource
    private ShopDeviceService shopDeviceService;

    @Override
    public ShopDeviceService getService() {
        return this.shopDeviceService;
    }

    /**
     * 查询区域内设备列表,并返回查询结果
     *
     * @param param 查询参数 {@link QueryParam}
     *              shopId 选中的区域id。由于区域树中有的节点是区域，有的节点上店铺，因此参数shopId可能是店铺id，也可能是区域id
     * @return 查询结果, 如果参数指定了分页(默认指定)将返回格式如:{total:数据总数,data:[{}]}的数据.
     * 否则返回格式[{}]
     */
    @RequestMapping(value = "/shopdevice/{shopId}", method = RequestMethod.GET)
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
     * 查询未分配的设备列表
     *
     * @param param 查询参数 {@link QueryParam}
     * @return 查询结果, 如果参数指定了分页(默认指定)将返回格式如:{total:数据总数,data:[{}]}的数据.
     * 否则返回格式[{}]
     */
    @RequestMapping(value = "/undist", method = RequestMethod.GET)
    @AccessLogger("查询列表")
    @Authorize(action = "R")
    public ResponseMessage list(QueryParam param) {
        Object data;
        // 获取条件查询
        if (!param.isPaging()) {
            //不分页
            data = getService().selectUndist(param);
        } else
            data = getService().selectUndistPager(param);
        return ok(data)
                .include(getPOType(), param.getIncludes())
                .exclude(getPOType(), param.getExcludes())
                .onlyData();
    }


    /**
     * 重置选中区域内的设备,使设备与店铺解绑
     *
     * @param
     * @return 返回解除绑定设备数量
     */
    @RequestMapping(value = "/reset/{id}", method = RequestMethod.DELETE)
    @AccessLogger("重置设备分配")
    @Authorize(action = "D")
    @Transactional
    public ResponseMessage reset(@PathVariable(value = "id")String id) {
        int i = getService().deleteShopInArea(id);
        return ok(i);
    }

    /**
     * 请求绑定设备与店铺
     *
     * @param deviceIds 待绑定的设备id数组和店铺id,数组最后一个元素为店铺id
     * @return 被添加数据的主键值之一
     * @throws javax.validation.ValidationException 验证数据格式错误
     */
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    @AccessLogger("新增")
    @Authorize(action = "C")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseMessage add(@RequestBody String[] deviceIds, HttpServletRequest request, HttpServletResponse response) {
        if (deviceIds != null && deviceIds.length >= 2) {
            int length = deviceIds.length;
            String shopId = deviceIds[length - 1];
            String result = "";
            for (int i = 0; i < length - 1; i++) {
                ShopDevice shopDevice = new ShopDevice();
                shopDevice.setDeviceId(deviceIds[i]);
                shopDevice.setShopId(shopId);
                shopDevice.setGmtCreate(new Date());
                result = this.getService().insert(shopDevice);
            }
            return created(result);
        }
        return null;
    }

}
