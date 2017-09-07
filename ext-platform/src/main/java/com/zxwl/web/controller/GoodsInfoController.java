package com.zxwl.web.controller;

import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.po.GenericPo;
import com.zxwl.web.core.exception.NotFoundException;
import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.GoodsInfo;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.core.utils.WebUtil;
import com.zxwl.web.service.GoodsClassService;
import com.zxwl.web.service.resource.ResourcesService;
import com.zxwl.web.util.ResourceUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.zxwl.web.service.GoodsInfoService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.zxwl.web.core.message.ResponseMessage.created;
import static com.zxwl.web.core.message.ResponseMessage.ok;

/**
 * 商品信息控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/goodsinfo")
@AccessLogger("商品信息")
@Authorize(module = "goodsinfo")
public class GoodsInfoController extends GenericController<GoodsInfo, String> {

    @Resource
    private  GoodsInfoService goodsInfoService;

    @Resource
    private GoodsClassService goodsClassService;

    @Resource
    private ResourcesService resourcesService;

    @Override
    public  GoodsInfoService getService() {
        return this.goodsInfoService;
    }


    /**
     * 根据id（主键）查询数据
     *
     * @param id 主键
     * @return 请求结果
     * @throws NotFoundException 要查询的数据不存在
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @AccessLogger("查询明细")
    @Authorize(action = "R")
    @Override
    public ResponseMessage info(@PathVariable("id") String id) {
        GoodsInfo po = getService().selectSingleInfo(id);
        if (po == null)
            throw new NotFoundException("data is not found!");
        return ok(po);
    }

    /**
     * 根据主键修改数据
     *
     * @param id     要修改数据的主键值
     * @param object 要修改的数据
     * @return 请求结果
     * @throws NotFoundException 要修改的数据不存在
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @AccessLogger("修改")
    @Authorize(action = "U")
    @Override
    public ResponseMessage update(@PathVariable("id") String id, @RequestBody GoodsInfo object) {
        GoodsInfo old = getService().selectSingleInfo(id);
        assertFound(old, "data is not found!");
        object.setId(id);
        int number = getService().update(object);
        return ok(number);
    }

    /**
     * 请求删除指定id的数据，请求方式为DELETE，使用rest风格，如请求 /delete/1 ，将删除id为1的数据
     *
     * @param id 要删除的id标识
     * @return 删除结果
     * @throws NotFoundException 要删除的数据不存在
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @AccessLogger("删除")
    @Authorize(action = "D")
    @Override
    public ResponseMessage delete(@PathVariable("id") String id) {
        GoodsInfo old = getService().selectSingleInfo(id);
        assertFound(old, "data is not found!");
        getService().delete(old);
        return ok();
    }

    @RequestMapping(value = "/pic/{id}", method = RequestMethod.GET)
    @Authorize(action = "R")
    public ResponseMessage pic(@PathVariable("id") String id, HttpServletRequest request) {
        String basePath = WebUtil.getBasePath(request);
        List list = resourcesService.selectImages(basePath, id);
        return ok(list);
    }


    @RequestMapping( value = "/list/{classCode}", method = RequestMethod.GET)
    @Authorize(action = "R")
    public ResponseMessage selectlist(@PathVariable("classCode") String classCode, QueryParam param) {
        param.getParam().put("class_code", classCode);
        // 获取条件查询
        Object data;
        if (!param.isPaging())//不分页
            data = getService().select(param);
        else
            data = getService().selectList(param);
        return ok(data)
                .include(getPOType(), param.getIncludes())
                .exclude(getPOType(), param.getExcludes())
                .onlyData();
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
    public ResponseMessage add(@RequestBody GoodsInfo object) {
        String id = goodsInfoService.insert(object);
        return created(id);
    }

    @RequestMapping(value = "/goodsClassTree", method = RequestMethod.GET)
    @AccessLogger("查询商品类别树")
    @Authorize(action = "R")
    public ResponseMessage classListTree() {
        Object data = goodsClassService.getTreeNodes();
        return ResponseMessage.ok(data);
    }

    @RequestMapping(value = "/goodsClassTree/{id}", method = RequestMethod.GET)
    @AccessLogger("查询商品类别树")
    @Authorize(action = "R")
    public ResponseMessage classChildrenTree(@PathVariable("id") String classCode) {
        Object data = goodsClassService.getTreeNodeByClassCode(classCode);
        return ResponseMessage.ok(data);
    }
}
