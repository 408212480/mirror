package com.zxwl.web.controller.api;

import com.zxwl.web.bean.DeviceFid;
import com.zxwl.web.bean.GoodsInfo;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.core.utils.RandomUtil;
import com.zxwl.web.core.utils.WebUtil;
import com.zxwl.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 造数据用的接口
 *
 * Project: zxwl-framework
 * Author: Sendya <18x@loacg.com>
 * Date: 2017/9/1 9:39
 */
@RequestMapping("/api/creator")
@RestController
@Authorize
public class TestApiController {

    @Autowired
    private DeviceFidService fidService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private GoodsInfoService goodsInfoService;

    @Autowired
    private ShopGoodsService shopGoodsService;


    @RequestMapping(value = "/shop_goods_line", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseMessage createShopGoods() {

        return ResponseMessage.created("");
    }

    /**
     * 创建 射频表数据
     * @return
     */
    @RequestMapping(value = "/device_fid", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseMessage createDeviceFid() {

        DeviceFid deviceFid = new DeviceFid();
        deviceFid.setDevNum(new StringBuilder("fid_").append(System.nanoTime()).toString());
        deviceFid.setDevCode(new StringBuilder("cid_").append(RandomUtil.randomChar(6).toLowerCase()).toString());
        deviceFid.setGmtCreate(new Date());
        deviceFid.setGmtModify(new Date());
        deviceFid.setLastChangeUser(WebUtil.getLoginUser().getId());
        String id = fidService.insert(deviceFid);

        return ResponseMessage.created(deviceFid);
    }
}
