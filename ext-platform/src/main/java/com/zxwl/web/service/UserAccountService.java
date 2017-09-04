package com.zxwl.web.service;

import com.zxwl.web.bean.UserAccount;
import com.zxwl.web.service.GenericService;

import java.math.BigDecimal;

/**
 * 账户余额 服务类接口
 * Created by generator
 */
public interface UserAccountService extends GenericService<UserAccount, String> {

    BigDecimal getGAllMoney(String videoId);
}
