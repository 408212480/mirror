package com.zxwl.web.controller.api;

import com.google.common.collect.Lists;
import com.zxwl.pay.common.util.str.StringUtils;
import com.zxwl.web.bean.*;
import com.zxwl.web.bean.api.GoodsInfoPage;
import com.zxwl.web.bean.api.PagerGoodsCommentList;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.po.GenericPo;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.core.utils.WebUtil;
import com.zxwl.web.service.*;
import com.zxwl.web.service.resource.ResourcesService;
import com.zxwl.web.util.ResourceUtil;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.zxwl.web.core.message.ResponseMessage.ok;

/**
 * Created by generator
 */
@RestController
@RequestMapping(value = "/api/goods")
@AccessLogger("商品详情页面")
@Authorize
public class GoodsInfoApiController extends GenericController<UserAccount, String> {

    @Resource
    private UserAccountService userAccountService;
    @Resource
    private ResourceAssociationService resourceAssociationService;
    @Resource
    private GoodsInfoService goodsInfoService;
    @Resource
    private ResourcesService resourcesService;
    @Resource
    private GoodsCommentService goodsCommentService;
    @Resource
    private GoodsOrderInfoService goodsOrderInfoService;
    @Resource
    private ShopDecorationService shopDecorationService;
    @Resource
    private GoodsInfoSpecService goodsInfoSpecService;
    @Resource
    private GoodsOrderService goodsOrderService;

    @Override
    public UserAccountService getService() {
        return this.userAccountService;
    }

    /**
     * @param goodsId 根据商品ID查询商品的信息及评论泪飙
     * @param type    info 表示详细信息， content 评论
     * @return
     */
    @RequestMapping(value = "/info/{goodsId}/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @AccessLogger("")
    public ResponseMessage getGoodsInfo(@PathVariable("goodsId") String goodsId, @PathVariable("type") String type, QueryParam queryParam, HttpServletRequest req) {

        PagerGoodsCommentList pagerGoodsCommentList = new PagerGoodsCommentList();
        pagerGoodsCommentList.setGoodsId(goodsId);
        pagerGoodsCommentList.setPageIndex(queryParam.getPageIndex());
        pagerGoodsCommentList.setPageSize(queryParam.getPageSize());

        GoodsInfoPage goodsInfoPage = new GoodsInfoPage();
        //获取商品图片信息
        //获取当前商品图片关联ID
        String goodsRecodId = resourceAssociationService.getGoodsImgs(goodsId);
        List<String> goodsImgs = null;
        if (!StringUtils.isEmpty(goodsRecodId)) {
            String basePath = WebUtil.getBasePath(req);
            goodsImgs = resourcesService.selectImages(basePath, goodsRecodId);
        }
        goodsInfoPage.setMapGoodsImgList(goodsImgs  == null ? Lists.newArrayList() : goodsImgs);
        //获取商品信息
        GoodsInfo goodsInfo = goodsInfoService.selectByPk(goodsId);
        if (goodsInfo!=null &&!StringUtils.isEmpty(goodsInfo.getImgId())) {
            String recodId = goodsInfo.getImgId();
            List<String> goodsInfoImgs = null;
            if (!StringUtils.isEmpty(recodId)) {
                String basePath = WebUtil.getBasePath(req);
                goodsInfoImgs = resourcesService.selectImages(basePath, recodId);
            }
            goodsInfoPage.setGoodsInfoImgs(goodsInfoImgs  == null ? Lists.newArrayList() : goodsInfoImgs);
        }

        goodsInfoPage.setGoodsInfo(goodsInfo);
        goodsInfoPage.setBuySum(goodsOrderInfoService.buySum(goodsId));
        goodsInfoPage.setGoodsInfoSpecList(goodsInfoSpecService.selectByGoodsId(goodsId));
        goodsInfoPage.setGoodsStasticsQuality(goodsInfoSpecService.statisticsAllGoodsQuality(goodsId));
        goodsInfoPage.setGoodsSpcColorList(goodsInfoSpecService.goodsColorList(goodsId));
        goodsInfoPage.setGoodsSpcSizeList(goodsInfoSpecService.goodsSizeList(goodsId));
        if ("detail".equals(type)) {
            //评论总数
            goodsInfoPage.setCommentTotal(goodsCommentService.totalGoodsComment(goodsId));
        }
        if ("comment".equals(type)) {
            //评论列表信息

            goodsInfoPage.setCommentList((goodsCommentService.getPagerGoodsCommentList(pagerGoodsCommentList, goodsId, req)));
            //评论总数
            goodsInfoPage.setCommentTotal(goodsCommentService.totalGoodsComment(goodsId));
        }
        return ok(goodsInfoPage);
    }

    @RequestMapping(value = "/shopdetail/{shopId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @AccessLogger("根据商品ID获店铺信息")
    public ResponseMessage getShopDetail(@PathVariable("shopId") String shopId) {
        Object goodsSpcInfo = shopDecorationService.selectByShopId(shopId);
        return ok(goodsSpcInfo);
    }

    @Transactional
    @RequestMapping(value = "/addGoodsComment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @AccessLogger("添加商品评论")
    public ResponseMessage addGoodsComment(@RequestParam("goodsId") String goodsId, @RequestBody GoodsComment goodsComment) throws IOException {
        if (goodsComment != null) {
            if (goodsComment.getGoodsspcInfo().equals("") || goodsComment.getGoodsspcInfo().equals(null) || goodsComment.getCommentLevel() <= 0) {
                return ResponseMessage.ok(" 商品规格信息，商品评论星级均为必传值");
            } else {
                GoodsComment gc = new GoodsComment();
                gc.setId(GenericPo.createUID());
                gc.setCommentLevel(goodsComment.getCommentLevel());
                gc.setComment(goodsComment.getComment());
                gc.setGmtCreate(new Date());
                gc.setGoodsId(goodsId);
                gc.setGmtModify(new Date());
                gc.setOrderId(goodsComment.getOrderId());
                gc.setGoodsspcInfo(goodsComment.getGoodsspcInfo());
                gc.setUserId(WebUtil.getLoginUser().getId());
                gc.setLastChangeUser(WebUtil.getLoginUser().getId());
                gc.setRecordId(GenericPo.createUID());
                //添加一条商品评论
                String cuId = goodsCommentService.insert(gc);
                //判断评论是否有上传图片
                //如果有则在中间表插入图片关联数据
                String[] cImgUrl = goodsComment.getcImgUrl();
                if (cImgUrl != null && cImgUrl.length > 0) {
                    String recordId = gc.getRecordId();
                    for (String imgURL : cImgUrl
                            ) {
                        ResourceAssociation resourceAssociation = new ResourceAssociation();
                        resourceAssociation.setId(GenericPo.createUID());
                        resourceAssociation.setDataType(3);
                        resourceAssociation.setRecordId(recordId);
                        resourceAssociation.setDataId(imgURL);
                        resourceAssociation.setType(0);
                        resourceAssociationService.insert(resourceAssociation);
                    }
                }
                //更新订单状态
                GoodsOrder goodsOrder=goodsOrderService.selectByPk(goodsComment.getOrderId());
                if (goodsOrder==null)
                    return ResponseMessage.error("order not found");
                goodsOrder.setOrderStatus(GoodsOrder.order_closed);
                goodsOrderService.update(goodsOrder);
                return ok(cuId);
            }
        } else {
            return ResponseMessage.ok("商品规格信息，商品评论星级均为必传值");
        }
    }

    @RequestMapping(value = "/userCommentList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @AccessLogger("获取用户个人评论列表")
    public ResponseMessage getUserCommentList(QueryParam queryParam, HttpServletRequest req) {
        PagerGoodsCommentList pagerGoodsCommentList = new PagerGoodsCommentList();
        pagerGoodsCommentList.setGoodsId(WebUtil.getLoginUser().getId());
        pagerGoodsCommentList.setPageIndex(queryParam.getPageIndex());
        pagerGoodsCommentList.setPageSize(queryParam.getPageSize());
        Object data = goodsCommentService.getPagerUserGoodsCommentList(pagerGoodsCommentList, WebUtil.getLoginUser().getId(), req);
        return ok(data);
    }
}
