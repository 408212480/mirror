package com.zxwl.web.controller.api;

import com.zxwl.pay.common.util.str.StringUtils;
import com.zxwl.web.bean.UserAccount;
import com.zxwl.web.bean.UserInfo;
import com.zxwl.web.bean.api.PagerParamApi;
import com.zxwl.web.bean.api.UserHomePage;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.core.utils.WebUtil;
import com.zxwl.web.service.*;
import com.zxwl.web.service.resource.ResourcesService;
import com.zxwl.web.util.ResourceUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.zxwl.web.core.message.ResponseMessage.ok;

/**
 * 账户余额控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/api/user")
@AccessLogger("用户主页")
@Authorize
public class UserHomePageApiController extends GenericController<UserAccount, String> {

    @Resource
    private UserAccountService userAccountService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private GoodsOrderService goodsOrderService;
    @Resource
    private DealPayService dealPayService;
    @Resource
    private GoodsBrokerageService goodsBrokerageService;

    @Resource
    private VideoUserService videoUserService;
    @Resource
    private ResourcesService resourcesService;

    @Resource
    private videoService videoService;

    @Override
    public UserAccountService getService() {
        return this.userAccountService;
    }


    @RequestMapping(value = "/myhomepage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @AccessLogger("")
    public ResponseMessage getUserHomePage(HttpServletRequest req) {
        UserHomePage userAboutList = new UserHomePage();
        //获取当前用户的ID
        //查询但前用户的详细信息
        UserInfo userInfo = userInfoService.selectUserInfoByUserId(WebUtil.getLoginUser().getId());
        if (userInfo != null && userInfo.getAvatarId() != null && !"".equals(userInfo.getAvatarId())) {
            userInfo.setAvatarId(resourcesService.selectSingleImage(WebUtil.getBasePath(req), userInfo.getAvatarId()));
        }else {
            userInfo.setAvatarId(ResourceUtil.getUserDefaultUserImg(req, userInfo.getSex() == null ? 0 :Integer.valueOf(userInfo.getSex())));
        }
        userAboutList.setUserInfo(userInfo);
        //查询当前用户的账户余额情况
        String money =userAccountService.getGAllMoney(WebUtil.getLoginUser().getId()).toString();
        if(money.equals("0")||money.equals("0.0")||money.equals("0.00")){
            userAboutList.setAllMoney("0.00");
        }else {
            userAboutList.setAllMoney(String.valueOf(userAccountService.getGAllMoney(WebUtil.getLoginUser().getId())));
        }
        //查询当前用户的点赞数
        userAboutList.setAllLike(videoService.getAllLike(WebUtil.getLoginUser().getId()));
        //根据用户ID查询用户的收益排名情况
        Map ownerRank = goodsBrokerageService.getOwnerRank(WebUtil.getLoginUser().getId());
        if (ownerRank != null && ownerRank.get("ttmd5") != null) {
            ownerRank.put("ttmd5", ResourceUtil.resourceBuildPath(req, String.valueOf(ownerRank.get("ttmd5")).trim()));
        }
        else {

            ownerRank.put("ttallbokerage", ownerRank.get("ttallbokerage")==null? 0.00 :(Integer) ownerRank.get("ttallbokerage"));
            ownerRank.put("ttmd5", ResourceUtil.getUserDefaultUserImg(req, ownerRank.get("sex")==null? 0 :(Integer) ownerRank.get("sex")));
        }
        userAboutList.setUserOwnRankandBrokerage(ownerRank);

        return ok(userAboutList);
    }


    //字符串逗号分割
    public static String[] convertStrToArray(String str) {
        String[] strArray = null;
        strArray = str.split(","); //拆分字符为"," ,然后把结果交给数组strArray
        return strArray;
    }

    @RequestMapping(value = "/userhomepage/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @AccessLogger("")
    public ResponseMessage UserHomePage(@PathVariable("userId") String userId, HttpServletRequest req) {

        UserHomePage userAboutList = new UserHomePage();
        //获取当前用户的ID
        //查询但前用户的详细信息
        UserInfo userInfo = userInfoService.selectUserInfoByUserId(userId);
        if (userInfo != null && userInfo.getAvatarId() != null && !"".equals(userInfo.getAvatarId())) {
            userInfo.setAvatarId(resourcesService.selectSingleImage(WebUtil.getBasePath(req), userInfo.getAvatarId()));
        }else {
            userInfo.setAvatarId(ResourceUtil.getUserDefaultUserImg(req, userInfo.getSex() == null ? 0 :Integer.valueOf(userInfo.getSex())));
        }
        userAboutList.setUserInfo(userInfo);
        //查询当前用户的账户余额情况
//        userAboutList.setAllMoney(userAccountService.getGAllMoney(userId));
        //查询当前用户的点赞数
        userAboutList.setAllLike(videoService.getAllLike(userId));
        //获取用户的视频列表
        List<Map> mapPagerResult = resourcesService.userVideoListByUserId(userId);
        if (mapPagerResult != null) {
            for (Map map : mapPagerResult) {
                String videoimg = (String) map.get("videoimglist");
                if (!StringUtils.isEmpty(videoimg)) {
                    map.put("videoimglist", ResourceUtil.resourceBuildPath(req, String.valueOf(map.get("videoimglist")).trim()));
                }
                map.put("videourl", ResourceUtil.resourceBuildPath(req, String.valueOf(map.get("videourl")).trim(), ".MP4"));
            }
        }
        userAboutList.setUserVideoList(mapPagerResult);
        //根据用户ID查询用户的收益排名情况
        Map ownerRank = goodsBrokerageService.getOwnerRank(userId);
        if (ownerRank != null && ownerRank.get("ttmd5") != null) {
            ownerRank.put("ttmd5", ResourceUtil.resourceBuildPath(req, String.valueOf(ownerRank.get("ttmd5")).trim()));
        }else {
            ownerRank.put("ttmd5", ResourceUtil.getUserDefaultUserImg(req, ownerRank.get("sex")==null? 0 :(Integer) ownerRank.get("sex")));
        }
        ownerRank.put("ttallbokerage", ownerRank.get("ttallbokerage")==null? 0.00 : ownerRank.get("ttallbokerage"));
        userAboutList.setUserOwnRankandBrokerage(ownerRank);

        return ok(userAboutList);
    }

    @RequestMapping(value = "/getallrank", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @AccessLogger("")
    public ResponseMessage getAllRank(HttpServletRequest req) {
        //查询平台所有用户的收益情况及排名
        List<Map> mapPagerResult = goodsBrokerageService.getRank();

        if (mapPagerResult != null) {
            for (Map map : mapPagerResult) {
                if (map.get("md5") != null ) {

                    map.put("md5", ResourceUtil.resourceBuildPath(req, String.valueOf(map.get("md5")).trim()));
                }
                else  {
                    //设置默认头像
                    map.put("md5", ResourceUtil.getUserDefaultUserImg(req,(Integer) map.get("sex")));
                }
            }
        }
        return ok(mapPagerResult);
    }

    @RequestMapping(value = "/alllist/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @AccessLogger("")
    public ResponseMessage getList(@PathVariable("type") String type, QueryParam param) {
        //
        Object data = null;
        //我的分润
        PagerParamApi pagerParam = new PagerParamApi();
        pagerParam.setPageIndex(param.getPageIndex());
        pagerParam.setPageSize(param.getPageSize());
        pagerParam.setUserId(WebUtil.getLoginUser().getId());
        if ("percentage".equals(type)) {
            data = goodsBrokerageService.selectByUserId(pagerParam, WebUtil.getLoginUser().getId());
            //消费明细
        } else if ("consume".equals(type)) {
            data = dealPayService.getConsume(pagerParam, WebUtil.getLoginUser().getId());
            //充值明细
        } else if ("recharge".equals(type)) {
            data = dealPayService.getRecharge(pagerParam, WebUtil.getLoginUser().getId());
        }
        return ok(data)
                .include(getPOType(), param.getIncludes())
                .exclude(getPOType(), param.getExcludes());
    }

    @RequestMapping(value = "/balance", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @AccessLogger("获取用户余额")
    public ResponseMessage getBalance() {
        String userId = WebUtil.getLoginUser().getId();
        UserAccount ua = userAccountService.createQuery().select("balance").where(UserAccount.Property.userId, userId).single();
        if (ua != null) {
            return ok(ua.getBalance());
        }
        return ok(0.00);
    }
}
