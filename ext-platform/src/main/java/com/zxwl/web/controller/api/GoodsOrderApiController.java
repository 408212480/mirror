package com.zxwl.web.controller.api;

import com.zxwl.pay.common.util.str.StringUtils;
import com.zxwl.web.bean.*;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.po.resource.Resources;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.core.utils.WebUtil;
import com.zxwl.web.service.*;
import com.zxwl.web.service.resource.FileService;
import com.zxwl.web.service.resource.ResourcesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 商品订单控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/api/GoodsOrder")
@AccessLogger("商品订单")
//@Authorize(module = "GoodsOrder")
public class GoodsOrderApiController extends GenericController<GoodsOrder, String> {

    @Resource
    private GoodsOrderService goodsOrderService;

    @Resource
    private FileService fileService;
    @Resource
    private GoodsCommentService goodsCommentService;
    @Resource
    private MetaDataRelService metaDataRelService;
    @Resource
    private ResourcesService resourcesService;
    @Resource
    private GoodsInfoService goodsInfoService;
    @Resource
    private GoodsOrderInfoService goodsorderinfoservice;

    @Override
    public GoodsOrderService getService() {
        return this.goodsOrderService;
    }


    /**
     * 请求所有订单
     *
     * @return 登录用户的订单列表
     */
    @RequestMapping(value = "/allOrderList", method = RequestMethod.GET)
    @AccessLogger("获取全部订单")
    @Authorize(action = "R")
    public ResponseMessage allOrderList(HttpServletRequest req, QueryParam queryParam) {
        List<GoodsOrderInfo> goodsOrderInfoList = getService().allOrderList(queryParam);
        for (GoodsOrderInfo goodsOrderInfo : goodsOrderInfoList) {
            String md5 = getService().goodsImg(goodsOrderInfo);
            if (!StringUtils.isBlank(md5)) {
                md5 = resourcesService.selectSingleImage(WebUtil.getBasePath(req), md5);
                goodsOrderInfo.setMd5(md5);
            }
            goodsOrderInfo.setMd5(md5);
        }
        return ResponseMessage.ok(goodsOrderInfoList);


    }

    /**
     * 根据订单状态筛选订单
     *
     * @param orderStatus 要筛选出的订单状态
     * @return 订单列表
     */
    @RequestMapping(value = "/orderList/{orderStatus}", method = RequestMethod.GET)
    @AccessLogger("根据订单状态获取订单")
    @Authorize(action = "R")
    public ResponseMessage orderList(@PathVariable("orderStatus") String orderStatus, HttpServletRequest req, QueryParam queryParam) {
        List<GoodsOrderInfo> goodsOrderInfoList = getService().orderList(orderStatus, queryParam);
        for (GoodsOrderInfo goodsOrderInfo : goodsOrderInfoList) {
            String md5 = getService().goodsImg(goodsOrderInfo);
            if (!StringUtils.isBlank(md5)) {
                md5 = resourcesService.selectSingleImage(WebUtil.getBasePath(req), md5);
                goodsOrderInfo.setMd5(md5);
            }
        }
        return ResponseMessage.ok(goodsOrderInfoList);
    }

    /**
     * 根据订单id 获取订单详情
     *
     * @param orderId 订单id
     * @return 订单详情
     */
    @RequestMapping(value = "/detail/{orderId}", method = RequestMethod.GET)
    @AccessLogger("获取订单详情")
    @Authorize(action = "R")
    public ResponseMessage detail(@PathVariable("orderId") String orderId, HttpServletRequest req) {
        GoodsOrderInfo goodsOrderInfo = getService().detail(orderId);
        String md5 = getService().goodsImg(goodsOrderInfo);
        if (!StringUtils.isBlank(md5)) {
            md5 = resourcesService.selectSingleImage(WebUtil.getBasePath(req), md5);
            goodsOrderInfo.setMd5(md5);
        }
        return ResponseMessage.ok(goodsOrderInfo);
    }

    @RequestMapping(value = "/refundDetail/{orderId}", method = RequestMethod.GET)
    @AccessLogger("获取退款订单详情")
    @Authorize(action = "R")
    public ResponseMessage refundDetail(@PathVariable("orderId") String orderId) {
        GoodsOrderInfo goodsOrderInfo = getService().detail(orderId);
        if (goodsOrderInfo != null && goodsOrderInfo.getOrderStatus() == 5) {
            switch (goodsOrderInfo.getPayType()) {
                case "0":
                    goodsOrderInfo.setUsername("余额");
                    break;
                case "1":
                    goodsOrderInfo.setUsername("微信");
                    break;
                case "2":
                    goodsOrderInfo.setUsername("支付宝");
                    break;
            }
            return ResponseMessage.ok(goodsOrderInfo).include(GoodsOrderInfo.class, "totalPrice", "username", "gmtRefund", "gmtModify", "refundStatus");
        }
        return ResponseMessage.error("data not found");
    }

    /**
     * 请求添加数据，请求必须以POST方式
     *
     * @param params 请求添加的Json对象
     * @return 被添加数据的主键值
     * @throws ValidationException 验证数据格式错误
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @AccessLogger("新增")
    @Authorize(action = "C")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseMessage add(@RequestBody Map<String, Object> params) {
        String orderId = getService().insert(params);
        switch (orderId) {
            case "GoodsInfoNull":
                return ResponseMessage.error("商品信息未找到");
            case "orderAddressNull":
                return ResponseMessage.error("地址信息未找到");
            case "GoodsInfoSpecNull":
                return ResponseMessage.error("规格信息未找到");
            case "500":
                return ResponseMessage.error("商品库存不足");
            default:
                return ResponseMessage.created(orderId);
        }
    }


    /**
     * 根据主键取消订单
     *
     * @param id     要取消的主键值
     * @param object 要取消的订单
     * @return 请求结果
     */
    @RequestMapping(value = "/cancleOrder/{id}", method = RequestMethod.PUT)
    @AccessLogger("退款")
    @Authorize(action = "U")
    public ResponseMessage cancle(@PathVariable("id") String id, GoodsOrder object) {
        object.setOrderStatus(GoodsOrder.order_cancled);
        GoodsOrderInfo goodsOrderInfo = new GoodsOrderInfo();
        goodsOrderInfo.setOrderId(id);
        goodsOrderInfo.setGmtModify(new Date());
        goodsOrderInfo.setGmtRefund(new Date());
        goodsOrderInfo.setRefundStatus(GoodsOrderInfo.refund_status_init);
        goodsorderinfoservice.updateByOrderId(goodsOrderInfo);
        return super.update(id, object);
    }


    /**
     * 根据主键修改数据
     *
     * @param id     要修改数据的主键值
     * @param object 要修改的数据
     * @return 请求结果
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @AccessLogger("设置订单状态为已付款")
    @Authorize(action = "U")
    public ResponseMessage update(@PathVariable("id") String id, GoodsOrder object) {
        object.setOrderStatus(GoodsOrder.order_payed);
        return super.update(id, object);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @AccessLogger("删除订单")
    @Authorize(action = "D")
    public ResponseMessage deleteOrder(@PathVariable("id") String id) {
        return super.delete(id);
    }
}
