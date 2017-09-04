package com.zxwl.web.service.impl;

import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.UserPrivacy;
import com.zxwl.web.dao.UserPrivacyMapper;
import com.zxwl.web.service.impl.AbstractServiceImpl;
import com.zxwl.web.service.UserPrivacyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户隐私设置 服务类实现
 * Created by generator
 */
@Service("userPrivacyService")
public class UserPrivacyServiceImpl extends AbstractServiceImpl<UserPrivacy, String> implements UserPrivacyService {

    @Resource
    protected UserPrivacyMapper userPrivacyMapper;

    @Override
    protected UserPrivacyMapper getMapper() {
        return this.userPrivacyMapper;
    }
  
    @Override
    public String insert(UserPrivacy data) {
        return super.insert(data);
    }
  
    @Override
    public int update(UserPrivacy data) {
        return super.update(data);
    }
  
    @Override
    public int  update(List<UserPrivacy> data) {
        return super.update(data);
    }

}
