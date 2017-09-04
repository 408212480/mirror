package com.zxwl.web.controller.api;

import com.zxwl.web.bean.GoodsBrokerage;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.service.GoodsBrokerageService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

/**
 * 导购收益控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/api/GoodsBrokerage")
@AccessLogger("导购收益")
public class GoodsBrokerageaApiController extends GenericController<GoodsBrokerage, String> {

    @Resource
    private  GoodsBrokerageService goodsBrokerageService;

    /**
     * 返回用户获利排名
     * @return
     */
    @Override
    public  GoodsBrokerageService getService() {
        return this.goodsBrokerageService;
    }

    @RequestMapping(value = "/getRank", method = RequestMethod.GET)
    public ResponseMessage getRank() {
        // 获取条件查询
        Object data = goodsBrokerageService.getRank();
        return ResponseMessage.ok(data);
    }

    /**
     * 获取个人排名
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getOwnerRank/{userId}", method = RequestMethod.GET)
    public ResponseMessage getOwnerRank(@PathVariable("userId") String userId ) {
        // 获取条件查询
        Object data = goodsBrokerageService.getOwnerRank(userId);
        return ResponseMessage.ok(data);
    }

    /**通过userId返回个人分佣列表
     *
     * @param userId
     * @return
     */
//    @RequestMapping(value = "/getGoodsBrokerageList", method = RequestMethod.GET)
//    public ResponseMessage getGoodsBrokerageList(@PathParam(value = "userId") String userId) {
//        // 获取条件查询
//        Object data = goodsBrokerageService.selectByUserId(userId);
//        return ResponseMessage.ok(data);
//    }
}
