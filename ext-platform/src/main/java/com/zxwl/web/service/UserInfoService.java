package com.zxwl.web.service;

import com.zxwl.web.bean.UserInfo;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.po.user.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户信息 服务类接口
 * Created by generator
 */
public interface UserInfoService extends GenericService<UserInfo, String> {
    void updateDefaultAddress(String userId, String defaultAddress);

    String insertApiUser(User user);

    int setPaymentPassword(UserInfo userInfo);

    int setName(UserInfo userInfo);

    int setImg(UserInfo userInfo);

    UserInfo selectByUserId(String userId);
    //查询用户信息
    UserInfo selectUserInfoByUserId(String userId);


    /**
     * @author wuei 2017.8.30 15:36
     * 查询用户信息列表
     * @return
     */
    List<UserInfo> selectUserInfo(HttpServletRequest req);
    PagerResult<UserInfo> selectUserInfoPager(QueryParam param, HttpServletRequest req);
}
