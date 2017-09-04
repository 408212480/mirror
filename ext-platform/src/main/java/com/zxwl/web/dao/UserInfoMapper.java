package com.zxwl.web.dao;

import com.zxwl.web.bean.UserInfo;
import com.zxwl.web.bean.common.QueryParam;

import java.util.List;

/**
* MyBatis 用户信息 数据映射接口
* Created by wuei
*/
public interface UserInfoMapper extends GenericMapper<UserInfo,String> {

    /**
     * @author wuei 2017.8.30 15:36
     * 查询用户信息列表
     * @return
     */
    List<UserInfo> selectUserInfo();
    List<UserInfo> selectUserInfoPager(QueryParam param);

}
