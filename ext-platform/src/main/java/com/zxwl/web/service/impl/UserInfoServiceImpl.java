package com.zxwl.web.service.impl;

import com.zxwl.web.bean.UserAccount;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.service.QueryService;
import com.zxwl.web.service.UserAccountService;
import com.zxwl.web.util.ResourceUtil;
import org.hsweb.commons.MD5;
import com.zxwl.pay.common.util.str.StringUtils;
import com.zxwl.web.bean.UserInfo;
import com.zxwl.web.bean.po.GenericPo;
import com.zxwl.web.bean.po.user.User;
import com.zxwl.web.core.utils.RandomUtil;
import com.zxwl.web.dao.UserInfoMapper;
import com.zxwl.web.service.UpdateService;
import com.zxwl.web.service.UserInfoService;
import com.zxwl.web.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用户信息 服务类实现
 * Created by generator
 */
@Service("userInfoService")
public class UserInfoServiceImpl extends AbstractServiceImpl<UserInfo, String> implements UserInfoService {

    @Resource
    protected UserInfoMapper userInfoMapper;
    @Resource
    private UserService userService;
    @Resource
    private UserAccountService userAccountService;

    @Override
    protected UserInfoMapper getMapper() {
        return this.userInfoMapper;
    }

    @Override
    public void updateDefaultAddress(String userId, String defaultAddress) {
        UpdateService.createUpdate(getMapper()).set(UserInfo.Property.defaultAddress, defaultAddress).where(UserInfo.Property.userId, userId).exec();
    }


    /**
     * 客户端用户初始化创建账户服务类
     *
     * 2017-09-05 修正增加插入 t_user_account 表
     * @param user
     * @return
     */
    @Transactional
    @Override
    public String insertApiUser(User user) {
        if (StringUtils.isEmpty(user.getUsername())) {
            user.setUsername(user.getPhone());
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(RandomUtil.randomChar(6)); // 随机密码
        }

        String userId = userService.insert(user);
        // 插入 t_user_info 表
        UserInfo apiUser = new UserInfo();
        apiUser.setUserId(userId);
        apiUser.setId(GenericPo.createUID());
        apiUser.setTelephone(user.getPhone());
        apiUser.setName("用户" + user.getPhone());

        // 插入 t_user_account 表
        UserAccount account = new UserAccount();
        account.setBalance(new BigDecimal(0.00));
        account.setUserId(userId);
        account.setGmtCreate(new Date());
        account.setGmtModify(new Date());
        userAccountService.insert(account);

        return insert(apiUser);
    }

    @Override
    public int setPaymentPassword(UserInfo userInfo) {
        return UpdateService.createUpdate(userInfoMapper)
                .set(UserInfo.Property.paymentPassword,MD5.encode(userInfo.getPaymentPassword()))
                .where(UserInfo.Property.userId,userInfo.getUserId()).exec();
    }

    @Override
    public int setName(UserInfo userInfo) {
        return UpdateService.createUpdate(userInfoMapper)
                .set(UserInfo.Property.name,userInfo.getName())
                .where(UserInfo.Property.userId,userInfo.getUserId()).exec();
    }


    @Override
    public int setImg(UserInfo userInfo) {
        return UpdateService.createUpdate(userInfoMapper)
                .set(UserInfo.Property.avatarId,userInfo.getAvatarId())
                .where(UserInfo.Property.userId,userInfo.getUserId()).exec();
    }

    @Override
    public UserInfo selectByUserId(String userId) {
        return QueryService.createQuery(userInfoMapper).where(UserInfo.Property.userId,userId).single();
    }

    //获取用户信息
    @Override
    public List<UserInfo> selectUserInfo(HttpServletRequest req) {
        List<UserInfo> result = this.getMapper().selectUserInfo();
        for(UserInfo userInfo:result){
            if(StringUtils.isNotEmpty(userInfo.getAvatarId())){
                userInfo.setAvatarId(ResourceUtil.resourceBuildPath(req, userInfo.getAvatarId()));
            }
            if(userInfo.getSex() != null && "0".equals(userInfo.getSex())){
                userInfo.setSex("女");
            }
            else{
                userInfo.setSex("男");
            }
        }
        return result;
    }

    //获取用户信息
    @Override
    public PagerResult<UserInfo> selectUserInfoPager(QueryParam param, HttpServletRequest req) {
        PagerResult<UserInfo> result = new PagerResult<>();
        int total = this.getMapper().selectUserInfo().size();
        result.setTotal(total);
        if(total <= param.getPageIndex()){
            int temp = total % param.getPageSize();
            param.setPageIndex(param.getPageIndex() - temp);
        }
        List<UserInfo> data = this.getMapper().selectUserInfoPager(param);
        for(UserInfo userInfo:data){
            if(StringUtils.isNotEmpty(userInfo.getAvatarId())){
                userInfo.setAvatarId(ResourceUtil.resourceBuildPath(req, userInfo.getAvatarId()));
            }
            if(userInfo.getSex() != null && "0".equals(userInfo.getSex())){
                userInfo.setSex("女");
            }
            else{
                userInfo.setSex("男");
            }
        }
        result.setData(data);
        return result;
    }
}
