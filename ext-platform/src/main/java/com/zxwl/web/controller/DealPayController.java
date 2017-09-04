package com.zxwl.web.controller;

import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.DealPay;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.message.ResponseMessage;
import org.springframework.web.bind.annotation.RestController;
import com.zxwl.web.service.DealPayService;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 交易记录控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/DealPay")
@AccessLogger("交易记录")
@Authorize(module = "DealPay")
public class DealPayController extends GenericController<DealPay, String> {

    @Resource
    private  DealPayService dealPayService;

    @Override
    public  DealPayService getService() {
        return this.dealPayService;
    }
}
