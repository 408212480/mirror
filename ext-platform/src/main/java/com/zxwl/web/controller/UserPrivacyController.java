package com.zxwl.web.controller;

import com.zxwl.web.bean.po.GenericPo;
import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.UserPrivacy;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.message.ResponseMessage;
import org.springframework.web.bind.annotation.*;
import com.zxwl.web.service.UserPrivacyService;

import javax.annotation.Resource;

import static com.zxwl.web.core.message.ResponseMessage.ok;

/**
 * 用户隐私设置控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/UserPrivacy")
@AccessLogger("用户隐私设置")
@Authorize(module = "UserPrivacy")
public class UserPrivacyController extends GenericController<UserPrivacy, String> {

    @Resource
    private  UserPrivacyService userPrivacyService;

    @Override
    public  UserPrivacyService getService() {
        return this.userPrivacyService;
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    @AccessLogger("修改")
//    @Authorize(action = "U")
//    public ResponseMessage update(@PathVariable("id") String id) {
//
//        return null;
//    }
}
