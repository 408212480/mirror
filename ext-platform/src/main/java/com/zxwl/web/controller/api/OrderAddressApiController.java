package com.zxwl.web.controller.api;

import com.zxwl.web.bean.OrderAddress;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.core.utils.WebUtil;
import com.zxwl.web.service.OrderAddressService;
import com.zxwl.web.service.UserInfoService;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.ValidationException;

import java.util.List;

import static com.zxwl.web.core.message.ResponseMessage.created;

import static com.zxwl.web.core.message.ResponseMessage.ok;

/**
 * 店铺信息控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/api/orderaddress")
@AccessLogger("店铺信息")
//@Authorize(module = "orderaddress")
public class OrderAddressApiController extends GenericController<OrderAddress, String> {

    @Resource
    private OrderAddressService orderAddressService;
    @Resource
    private UserInfoService userInfoService;

    @Override
    public OrderAddressService getService() {
        return this.orderAddressService;
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
        Object data;
        String userId = WebUtil.getLoginUser().getId();
        OrderAddress orderAddress = getService().getDefaultAddress(userId);
        if (!param.isPaging()) {//不分页
            List<OrderAddress> orderAddresses = getService().getAddress(userId);
            for (int i = 0; i < orderAddresses.size(); i++) {
                if (orderAddresses.get(i).equals(orderAddress)) {
                    orderAddresses.get(i).setDefault(true);
                }
            }
            data = orderAddresses;
            return ok(data);
        }
        param.getParam().put("userId", userId);
        List<OrderAddress> orderAddresses = getService().getAddressPager(param);
        for (int i = 0; i < orderAddresses.size(); i++) {
            if (orderAddresses.get(i).equals(orderAddress)) {
                orderAddresses.get(i).setDefault(true);
//                    orderAddresses.add(0,orderAddresses.get(i));
//                    orderAddresses.remove(i);
            }
        }
        data = orderAddresses;
        return ok(data);

    }

    @RequestMapping(value = "/defaultAddress", method = RequestMethod.GET)
    @AccessLogger("查询默认地址")
    @Authorize(action = "R")
    public ResponseMessage getDefaultAddress() {
        String userId = WebUtil.getLoginUser().getId();
        OrderAddress orderAddress = getService().getDefaultAddress(userId);
        if (orderAddress == null) {
            List<OrderAddress> orderAddresses = getService().getAddress(userId);
            if (orderAddresses.size() > 0) {
                orderAddresses.get(0).setDefault(true);
                orderAddress = orderAddresses.get(0);
            }
        }
        return ok(orderAddress);
    }

    /**
     * 请求添加数据，请求必须以POST方式
     *
     * @param orderAddress 请求添加的对象
     * @return 被添加数据的主键值
     * @throws ValidationException 验证数据格式错误
     */
    @Override
    public ResponseMessage add(OrderAddress orderAddress) {
        orderAddress.setUserId(WebUtil.getLoginUser().getId());
        if (orderAddress.isDefault()) {
            String id = getService().insert(orderAddress);
            userInfoService.updateDefaultAddress(orderAddress.getUserId(), id);
            return created(id);
        }
        return super.add(orderAddress);
    }

    /**
     * 根据主键修改数据
     *
     * @param id           要修改数据的主键值
     * @param orderAddress 要修改的数据
     * @return 请求结果
     */
    @Override
    public ResponseMessage update(@PathVariable("id") String id, OrderAddress orderAddress) {
        if (orderAddress.isDefault()) {
            super.update(id, orderAddress);
            userInfoService.updateDefaultAddress(WebUtil.getLoginUser().getId(), id);
            return created(id);
        }
        return super.update(id, orderAddress);
    }
}
