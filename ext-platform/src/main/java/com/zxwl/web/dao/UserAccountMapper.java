package com.zxwl.web.dao;

import com.zxwl.web.dao.GenericMapper;
import com.zxwl.web.bean.UserAccount;

import java.util.Map;

/**
* MyBatis 账户余额 数据映射接口
* Created by generator 
*/
public interface UserAccountMapper extends GenericMapper<UserAccount,String> {

    Map getAllMoney(String userId);
}
