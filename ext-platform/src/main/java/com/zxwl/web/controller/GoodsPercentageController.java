package com.zxwl.web.controller;

import com.zxwl.web.bean.GoodsPercentage;
import com.zxwl.web.bean.GoodsShop;
import com.zxwl.web.bean.po.GenericPo;
import com.zxwl.web.core.exception.NotFoundException;
import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.core.utils.WebUtil;
import com.zxwl.web.service.GoodsPercentageService;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;

import static com.zxwl.web.core.message.ResponseMessage.created;
import static com.zxwl.web.core.message.ResponseMessage.ok;

/**
 * 分佣设置表控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/dividend")
@AccessLogger("分佣设置表")
@Authorize(module = "dividend")
public class GoodsPercentageController  extends GenericController<GoodsPercentage, String> {

    @Resource
    private GoodsPercentageService goodsPercentageService;

    public  GoodsPercentageService getService() {
        return this.goodsPercentageService;
    }

    /**
     * 查询分佣信息列表，返回查询结果
     *
     * @param param 查询参数 {@link QueryParam}
     * @return 查询结果, 如果参数指定了分页(默认指定)将返回格式如:{total:数据总数,data:[{}]}的数据.
     * 否则返回格式[{}]
     */
    @RequestMapping(method = RequestMethod.GET)
    @AccessLogger("查询列表")
    @Authorize(action = "R")
    public ResponseMessage list(QueryParam param) {
        // 获取条件查询
        Object data;
        if (!param.isPaging())//不分页
            data = getService().selectGoodsDividend(param);
        else
            data = getService().selectGoodsDividendPage(param);
        return ok(data).onlyData();
    }



    /**
     * 查询分佣信息列表，返回查询结果
     *
     * @param param 查询参数 {@link QueryParam}
     * @return 查询结果, 如果参数指定了分页(默认指定)将返回格式如:{total:数据总数,data:[{}]}的数据.
     * 否则返回格式[{}]
     */
    @RequestMapping(value = "/userDividendList", method = RequestMethod.GET)
    @AccessLogger("查询列表")
    @Authorize(action = "R")
    public ResponseMessage userDividendList(QueryParam param) {
        // 获取条件查询
        Object data;
        if (!param.isPaging())//不分页
            data = getService().selectUserDividend(param);
        else
            data = getService().selectUserDividendPage(param);
        return ok(data).onlyData();
    }

    /**
     * 查询商品编码列表，返回查询结果
     *
     * @return 查询结果, 如果参数指定了分页(默认指定)将返回格式如:{total:数据总数,data:[{}]}的数据.
     * 否则返回格式[{}]
     */
    @RequestMapping(value = "/goods_shop", method = RequestMethod.GET)
    @AccessLogger("查询列表")
    @Authorize(action = "R")
    public ResponseMessage goodsCodeList() {
        // 获取条件查询
        List<GoodsShop> data = getService().selectGoodsShop();
        return ok(data);
    }

    /**
     * 请求添加数据，请求必须以POST方式
     *
     * @param object 请求添加的对象
     * @return 被添加数据的主键值
     * @throws javax.validation.ValidationException 验证数据格式错误
     */
    @RequestMapping(method = RequestMethod.POST)
    @AccessLogger("新增")
    @Authorize(action = "C")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseMessage add(@RequestBody GoodsPercentage object) {
        if(object.getPercentage()<0 || object.getPercentage()>100){
            throw new java.lang.IllegalArgumentException("输入折扣值不合法");
        }
        object.setUserId(WebUtil.getLoginUser().getId());
        String id = getService().insert(object);
        return created(id);
    }

    /**
     * 请求启用指定id的percentage（分佣），请求方式为put，使用rest风格，如请求 enable/1 ，将启用id为1的数据
     *
     * @param id 要启用的id标识
     * @return 删除结果
     * @throws NotFoundException 要删除的数据不存在
     */
    @RequestMapping(value = "percentage/enable/{id}", method = RequestMethod.PUT)
    @AccessLogger("修改")
    @Authorize(action = "U")
    @Transactional
    public ResponseMessage enable(@PathVariable("id") String id) {
        GoodsPercentage old = getService().selectByPk(id);
        assertFound(old, "data is not found!");
        old.setStatus(0);
        this.getService().update(old);
        return ok();
    }

    @RequestMapping(value = "percentage/disable/{id}", method = RequestMethod.PUT)
    @AccessLogger("修改")
    @Authorize(action = "U")
    @Transactional
    public ResponseMessage disable(@PathVariable("id") String id) {
        GoodsPercentage old = getService().selectByPk(id);
        assertFound(old, "data is not found!");
        old.setStatus(1);
        this.getService().update(old);
        return ok();
    }
}
