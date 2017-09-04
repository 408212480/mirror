package com.zxwl.web.service.impl;

import com.zxwl.web.bean.UserAccount;
import com.zxwl.web.dao.UserAccountMapper;
import com.zxwl.web.service.UserAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 账户余额 服务类实现
 * Created by generator
 */
@Service("userAccountService")
public class UserAccountServiceImpl extends AbstractServiceImpl<UserAccount, String> implements UserAccountService {

    @Resource
    protected UserAccountMapper userAccountMapper;

    @Override
    protected UserAccountMapper getMapper() {
        return this.userAccountMapper;
    }
  
    @Override
    public String insert(UserAccount data) {
        return super.insert(data);
    }
  
    @Override
    public int  update(UserAccount data) {
        return super.update(data);
    }
  
    @Override
    public int  update(List<UserAccount> data) {
        return super.update(data);
    }

    @Override
    public BigDecimal getGAllMoney(String userId) {
        Map data = getMapper().getAllMoney(userId);
        if (data == null) return BigDecimal.valueOf(0.0);
        BigDecimal allMoney = BigDecimal.valueOf(data.get("balance") == null ? 0.0 : Double.valueOf(String.valueOf(data.get("balance"))));
        return allMoney;
    }
}
