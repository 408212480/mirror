package com.zxwl.web.controller;

import com.zxwl.web.bean.FidGoodsSpec;
import com.zxwl.web.bean.GoodsInfo;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.core.exception.BusinessException;
import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.GoodsInfoSpec;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.core.utils.WebUtil;
import com.zxwl.web.service.FidGoodsSpecService;
import com.zxwl.web.service.GoodsInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.zxwl.web.service.GoodsInfoSpecService;

import javax.annotation.Resource;

import java.util.List;

import static com.zxwl.web.core.message.ResponseMessage.created;
import static com.zxwl.web.core.message.ResponseMessage.ok;

/**
 * 服装分配控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/goodsinfospec")
@AccessLogger("服装分配")
@Authorize(module = "goodsinfospec")
public class GoodsInfoSpecController extends GenericController<GoodsInfoSpec, String> {

    @Resource
    private GoodsInfoSpecService goodsInfoSpecService;
    @Resource
    private GoodsInfoService goodsInfoService;
    @Resource
    FidGoodsSpecService fidGoodsSpecService;

    @Override
    public GoodsInfoSpecService getService() {
        return this.goodsInfoSpecService;
    }

    @RequestMapping("/getShopGoods")
    @AccessLogger("分页获取店铺商品")
    @Authorize(action = "R")
    public ResponseMessage getShopGoods(QueryParam param) {
        Object data;
        //获取当前登录用户ID
        String id = WebUtil.getLoginUser().getId();
        param.getParam().put("userId", id);
        if (!param.isPaging())//不分页
        {
            List<GoodsInfoSpec> goodsInfoSpecs = getService().select(param);
            for (GoodsInfoSpec goodsInfoSpec : goodsInfoSpecs) {
                GoodsInfo goodsInfo = goodsInfoService.selectByPk(goodsInfoSpec.getGoodsId());
                goodsInfoSpec.setClassCode(goodsInfo.getClassCode());
                goodsInfoSpec.setTitle(goodsInfo.getTitle());
            }
            data = goodsInfoSpecs;
        }else {
            List<GoodsInfoSpec> goodsInfoSpecs  = getService().getShopGoods(param);
            for (GoodsInfoSpec goodsInfoSpec : goodsInfoSpecs) {
                GoodsInfo goodsInfo = goodsInfoService.selectByPk(goodsInfoSpec.getGoodsId());
                goodsInfoSpec.setClassCode(goodsInfo.getClassCode());
                goodsInfoSpec.setTitle(goodsInfo.getTitle());
            }
            PagerResult result=new PagerResult();
            result.setData(goodsInfoSpecs);
            result.setTotal(goodsInfoSpecs.size());
            data=result;
        }
        return ok(data)
                .include(getPOType(), param.getIncludes())
                .exclude(getPOType(), param.getExcludes())
                .onlyData();
    }

    @RequestMapping(value = "/addFidSpec", method = RequestMethod.PUT)
    @AccessLogger("绑定射频规格")
    @Authorize(action = "U")
    public ResponseMessage addFidSpec(@RequestBody FidGoodsSpec fidGoodsSpec) {
        try {
            fidGoodsSpecService.update(fidGoodsSpec);
            return ok();
        } catch (BusinessException e) {
            return ResponseMessage.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}/getFidSpec", method = RequestMethod.GET)
    @AccessLogger("获取射频规格关系")
    @Authorize(action = "R")
    public ResponseMessage getFidSpec(@PathVariable("id") String id) {
        Object data;
        data = fidGoodsSpecService.getFidId(id);
        return ok(data);
    }

    @RequestMapping(value = "/{id}/delFidSpec", method = RequestMethod.PUT)
    @AccessLogger("删除射频规格关系")
    @Authorize(action = "U")
    public ResponseMessage delFidSpec(@PathVariable("id") String id) {
        try {
            fidGoodsSpecService.delFidSpec(id);
            return ResponseMessage.ok();
        } catch (BusinessException e) {
            return ResponseMessage.error(e.getMessage());
        }
    }
}
