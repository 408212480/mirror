package com.zxwl.web.controller.api;

import com.zxwl.web.bean.po.user.User;
import com.zxwl.web.bean.po.user.UserDefInfo;
import com.zxwl.web.core.exception.AuthorizeForbiddenException;
import com.zxwl.web.core.exception.NotFoundException;
import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.core.session.HttpSessionManager;
import com.zxwl.web.core.utils.WebUtil;
import com.zxwl.web.service.config.ConfigService;
import com.zxwl.web.service.resource.ResourcesService;
import com.zxwl.web.service.user.UserService;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.hsweb.commons.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;

/**
 * Project: zxwl-framework
 * Author: Sendya <18x@loacg.com>
 * Date: 2017/7/11 11:01
 */
@RestController
@RequestMapping("/api/auth")
public class LoginApiController {


    /**
     * 授权过程所需缓存
     */
    @Autowired(required = false)
    private CacheManager cacheManager;

    /**
     * 用户服务类
     */
    @Resource
    private UserService userService;

    /**
     * 配置服务类
     */
    @Resource
    private ConfigService configService;

    /**
     * 资源服务类
     */
    @Resource
    private ResourcesService resourcesService;

    /**
     * httpSession管理器
     */
    @Autowired
    private HttpSessionManager httpSessionManager;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @AccessLogger("登录")
    public ResponseMessage login(@RequestParam String username,
                                 @RequestParam String password,
                                 HttpServletRequest request) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        //判断用户是否多次输入密码错误
        String userIp = WebUtil.getIpAddr(request);
        int maxErrorNumber = configService.getInt("login", "error.max_number", 5);
        int waitMinutes = configService.getInt("login", "error.wait_minutes", 10);
        Cache cache = cacheManager.getCache("login.error");
        String cachePrefix = username.concat("@").concat(userIp);
        String timeCacheKey = cachePrefix.concat("-time");
        String numberCacheKey = cachePrefix.concat("-number");
        Integer error_number = cache.get(numberCacheKey, Integer.class);
        Long error_time = cache.get(timeCacheKey, Long.class);
        long now_time = System.currentTimeMillis();
        if (error_number != null && error_time != null) {
            if ((now_time - error_time) / 1000 / 60d > waitMinutes) {
                cache.evict(timeCacheKey);
                cache.evict(numberCacheKey);
                error_number = 0;
                error_time = 0l;
            }
            if (error_number >= maxErrorNumber)
                throw new AuthorizeForbiddenException("您的账户已被锁定登录,请" + (waitMinutes - ((now_time - error_time) / 1000 / 60)) + "分钟后再试!");
        }
        User user = userService.selectByUserName(username);
        if (user == null || user.getStatus() != 1) throw new NotFoundException("用户不存在或已注销");
        //密码错误
        if (!user.getPassword().equals(password) && !user.getPassword().equals(MD5.encode(password))) {
            if (error_number == null) error_number = 0;
            cache.put(timeCacheKey, System.currentTimeMillis());
            cache.put(numberCacheKey, ++error_number);
            throw new AuthorizeForbiddenException("密码错误,你还可以重试" + (maxErrorNumber - error_number) + "次");
        }
        cache.evict(timeCacheKey);
        cache.evict(numberCacheKey);
        user.setPassword("");//去除密码
        if (user.getUsername().equals("admin"))
            userService.initAdminUser(user);
        else
            user.initRoleInfo();

        if (user.getUserInfo() == null) {
            UserDefInfo userInfo = userService.selectUserInfo(user.getId());
            if (userInfo != null) {
                user.setUserInfo(userInfo);
                user.setAvatar(userInfo.getAvatarId());
            }
        }

        // 2017.08.14 增加用户头像地址获取
        if (user.getAvatar() != null && !"".equals(user.getAvatar()) && user.getAvatar().length() == 32) {
            String userAvatar = resourcesService.selectSingleImage(WebUtil.getBasePath(request), user.getAvatar());
            user.setAvatar(userAvatar);
        } else {
            user.setAvatar(WebUtil.getBasePath(request) + "img/user2-160x160.jpg");
        }

        User newUser = new User();
        BeanUtilsBean.getInstance().getPropertyUtils()
                .copyProperties(newUser, user);
        httpSessionManager.addUser(newUser, request.getSession());
        return ResponseMessage.ok();
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    @AccessLogger("注销登陆")
    public ResponseMessage logout(HttpSession session) {
        User user = WebUtil.getLoginUser();
        if (user != null) {
            httpSessionManager.removeUser(user.getId());
            //使用redis时,有时候removeUser会失效,removeSession总可以了吧
            httpSessionManager.removeSession(session.getId());
        }
        return ResponseMessage.ok();
    }
}
