package com.zxwl.web.controller;

import com.zxwl.web.bean.GoodsInfo;
import com.zxwl.web.bean.GoodsInfoSpec;
import com.zxwl.web.bean.GoodsOrder;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.GoodsOrderInfo;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.service.GoodsInfoSpecService;
import com.zxwl.web.service.GoodsOrderService;
import com.zxwl.web.service.ShopService;
import com.zxwl.web.service.user.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.zxwl.web.service.GoodsOrderInfoService;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.ValidationException;

import java.util.List;

import static com.zxwl.web.core.message.ResponseMessage.ok;

/**
 * 商品订单信息控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/goodsorderinfo")
@AccessLogger("商品订单信息")
@Authorize(module = "goodsorderinfo")
public class GoodsOrderInfoController extends GenericController<GoodsOrderInfo, String> {

    @Resource
    private GoodsOrderInfoService goodsOrderInfoService;
    @Resource
    private GoodsInfoSpecService goodsInfoSpecService;
    @Resource
    private ShopService shopService;
    @Resource
    private GoodsOrderService goodsOrderService;
    @Resource
    private UserService userService;

    @Override
    public GoodsOrderInfoService getService() {
        return this.goodsOrderInfoService;
    }

    /**
     * 查询列表,并返回查询结果
     *
     * @param param 查询参数 {@link QueryParam}
     * @return 查询结果, 如果参数指定了分页(默认指定)将返回格式如:{total:数据总数,data:[{}]}的数据.
     * 否则返回格式[{}]
     */
    @Override
    public ResponseMessage list(QueryParam param) {
        // 获取条件查询
        Object data;
        if (!param.isPaging())//不分页
        {
            List<GoodsOrderInfo> goodsOrderInfos = getService().getOrderInfo(param);
            data = goodsOrderInfos;
        } else {
            PagerResult pagerResult =new PagerResult();
            List<GoodsOrderInfo> goodsOrderInfos =getService().getOrderInfoPager(param);
            pagerResult.setData(goodsOrderInfos);
            pagerResult.setTotal(goodsOrderInfos.size());
            data = pagerResult;
        }
        return ok(data)
                .include(getPOType(), param.getIncludes())
                .exclude(getPOType(), param.getExcludes())
                .onlyData();
    }

    @RequestMapping(value = "/sendOrder", method = RequestMethod.PUT)
    @AccessLogger("发货")
    @Authorize(action = "U")
    public ResponseMessage sendOrder(@RequestBody GoodsOrderInfo goodsOrderInfo) {
        getService().sendOrder(goodsOrderInfo);
        return ok();
    }

    /**
     * 请求添加数据，请求必须以POST方式
     * @param goodsOrderInfo 请求添加的对象
     * @return 被添加数据的主键值
     * @throws ValidationException 验证数据格式错误
     */
    @Override
    public ResponseMessage add(GoodsOrderInfo goodsOrderInfo) {
        return super.add(goodsOrderInfo);
    }
}
