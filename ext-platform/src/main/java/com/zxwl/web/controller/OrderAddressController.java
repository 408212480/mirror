package com.zxwl.web.controller;

import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.OrderAddress;
import com.zxwl.web.service.UserInfoService;
import org.springframework.web.bind.annotation.RestController;
import com.zxwl.web.service.OrderAddressService;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;


/**
 * 店铺信息控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/orderaddress")
@AccessLogger("店铺信息")
@Authorize(module = "orderaddress")
public class OrderAddressController extends GenericController<OrderAddress, String> {

    @Resource
    private OrderAddressService orderAddressService;
    @Resource
    private UserInfoService userInfoService;

    @Override
    public OrderAddressService getService() {
        return this.orderAddressService;
    }
}
