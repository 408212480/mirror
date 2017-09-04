package com.zxwl.web.controller.api;

import com.zxwl.web.bean.MetaDataRel;
import com.zxwl.web.bean.ShopDecoration;
import com.zxwl.web.bean.po.resource.Resources;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.core.exception.ValidationException;
import com.zxwl.web.core.utils.WebUtil;
import com.zxwl.web.service.MetaDataRelService;
import com.zxwl.web.service.ShopDecorationService;
import com.zxwl.web.core.exception.NotFoundException;
import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.bean.Shop;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.service.resource.ResourcesService;
import org.springframework.stereotype.Controller;
import org.thymeleaf.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.zxwl.web.service.ShopService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;



import static com.zxwl.web.core.message.ResponseMessage.ok;

/**
 * 店铺信息控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/api/shop")
@AccessLogger("店铺信息h5")
//@Authorize
public class ShopApiController extends GenericController<Shop, String> {

    @Resource
    private ShopService shopService;

    @Resource
    private ResourcesService resourcesService;

    @Resource
    private MetaDataRelService metaDataRelService;

    @Resource
    private ShopDecorationService shopDecorationService;

    public ShopService getService() {
        return this.shopService;
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    @AccessLogger("详情")
    public ResponseMessage shopView(String shopId, HttpServletRequest req) {
        ShopDecoration shop = shopDecorationService.shopView(shopId);
        if (!StringUtils.isEmpty(shop.getImg1()))
            shop.setImg1(resourcesService.selectSingleImage(WebUtil.getBasePath(req), shop.getImg1()));
        if (!StringUtils.isEmpty(shop.getImg2()))
            shop.setImg2( resourcesService.selectSingleImage(WebUtil.getBasePath(req), shop.getImg2()));
        if (!StringUtils.isEmpty(shop.getImg3()))
            shop.setImg3( resourcesService.selectSingleImage(WebUtil.getBasePath(req), shop.getImg3()));
        if (!StringUtils.isEmpty(shop.getMd5()))
            shop.setMd5(resourcesService.selectSingleImage(WebUtil.getBasePath(req), shop.getMd5()));
        return ok(shop);
    }
}
