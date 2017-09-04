package com.zxwl.web.controller.api;

import com.zxwl.web.bean.UserAccount;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.service.UserAccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.zxwl.web.core.message.ResponseMessage.ok;

/**
 * 账户余额控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/api/UserAccount")
@AccessLogger("账户余额")
//@Authorize(module = "UserAccount")
public class UserAccountApiController extends GenericController<UserAccount, String> {

    @Resource
    private  UserAccountService userAccountService;

    @Override
    public  UserAccountService getService() {
        return this.userAccountService;
    }
    @RequestMapping(value = "/getAllMoney", method = RequestMethod.GET)
    @AccessLogger("获取个人余额")
//    @Authorize(module = "R")
    public ResponseMessage getGoodsPricePercentage(@RequestParam String userId) {
        Object data = userAccountService.getGAllMoney(userId);
        return ok(data);
    }
}
