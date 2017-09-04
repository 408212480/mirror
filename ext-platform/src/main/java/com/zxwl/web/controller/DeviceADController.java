package com.zxwl.web.controller;

import com.zxwl.web.bean.ADDevice;
import com.zxwl.web.bean.MetaDataRel;
import com.zxwl.web.bean.po.GenericPo;
import com.zxwl.web.core.exception.NotFoundException;
import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.DeviceAD;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.core.utils.WebUtil;
import com.zxwl.web.service.ADDeviceService;
import com.zxwl.web.service.MetaDataRelService;
import com.zxwl.web.service.ShopDeviceService;
import com.zxwl.web.util.ResourceUtil;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.zxwl.web.service.DeviceADService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.List;

import static com.zxwl.web.core.message.ResponseMessage.created;
import static com.zxwl.web.core.message.ResponseMessage.ok;

/**
 * 设备广告控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/device-ad")
@AccessLogger("设备广告")
@Authorize(module = "device-ad")
public class DeviceADController extends GenericController<DeviceAD, String> {

    private String imgPath = "/file/image/";

    private String videoPath = "/file/download/";


    @Resource
    private  DeviceADService deviceADService;

    @Resource
    private MetaDataRelService metaDataRelService;

    @Resource
    private ADDeviceService aDDeviceService;

    @Override
    public  DeviceADService getService() {
        return this.deviceADService;
    }


    /**
     * 查询列表,并返回查询结果
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
            data = getService().selectDeviceAD(param);
        else
            data = getService().selectDeviceADPage(param);
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
    @Transactional
    public ResponseMessage add(@RequestBody DeviceAD object) {
        //向t_metadata_rel添加记录
        String recordId = GenericPo.createUID();
        MetaDataRel md = new MetaDataRel();
        md.setType(object.getResourceTypeInt());
        md.setRecordId(recordId);
        md.setDataId(object.getResourceId());
        md.setDataType(1);
        metaDataRelService.insert(md);

        //向t_device_ad插入数据
        object.setId(recordId);
        object.setResourceId(recordId);
        object.setUserId(WebUtil.getLoginUser().getId());
        object.setUploadTime(new Date());
        String pk = getService().insert(object);

        return created(pk);
    }

    /**
     * 请求将广告发布到指定区域的店铺中
     *
     * @param adIds 待发布的广告id
     *        id    要被分配广告的区域id
     * @return 被添加数据的主键值
     * @throws javax.validation.ValidationException 验证数据格式错误
     */
    @RequestMapping(value = "/ad-distribution/area/{id}", method = RequestMethod.POST)
    @AccessLogger("新增")
    @Authorize(action = "C")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseMessage addADDevice(@PathVariable("id")String id, @RequestBody String[] adIds) {
        int k = this.getService().addADsToArea(id, adIds);
        return created(k);
    }

    /**
     * 根据deviceAD的id,查找deviceAD对应资源的访问路径和资源id
     *
     * @param id 请求添加的对象
     * @return 被添加数据的主键值
     * @throws javax.validation.ValidationException 验证数据格式错误
     */
    @RequestMapping(value="/resource/{id}",method = RequestMethod.GET)
    @AccessLogger("查找")
    @Authorize(action = "R")
    public ResponseMessage getResourceUrlById(@PathVariable("id") String id, HttpServletRequest req) {
        com.zxwl.web.bean.Resource resource = this.getService().selectResourceById(id);
        if(resource == null){
            return ResponseMessage.error("设备id不正确！", 500);
        }
        if(resource.getType() == 0){
            resource.setMd5(ResourceUtil.resourceBuildPath(req, resource.getMd5().trim()));
        }
        else{
            resource.setMd5(ResourceUtil.resourceBuildPath(req, resource.getMd5().trim(), ".mp4"));
        }

        return created(resource);
    }

    /**
     * 根据主键修改t_device_ad和t_metadata_rel数据
     *
     * @param id     要修改数据的主键值
     * @param object 要修改的数据
     * @return 请求结果
     * @throws NotFoundException 要修改的数据不存在
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @AccessLogger("修改")
    @Authorize(action = "U")
    @Transactional
    public ResponseMessage update(@PathVariable("id") String id, @RequestBody DeviceAD object) {
        DeviceAD old = getService().selectByPk(id);
        assertFound(old, "data is not found!");

        List<MetaDataRel> metaDR = metaDataRelService.selectByRecordId(old.getResourceId());
        if(metaDR != null && metaDR.size() > 0 && metaDR.get(0) != null){
            MetaDataRel metaDataRel = metaDR.get(0);
            //检查广告资源是更新，如果已更新，则更新t_metadata_rel表中相应数据
            if(object.getResourceId() != null && !object.getResourceId().equals(metaDataRel.getDataId())){
                metaDataRel.setDataId(object.getResourceId());
                metaDataRelService.update(metaDataRel);
            }
        }
        else{
            //如果t_metadata_rel 中没有广告与视频或图片资源记录，则插入一条记录
            if(object.getResourceId() != null){
                MetaDataRel metaDataRel = new MetaDataRel();
                metaDataRel.setDataType(1);
                metaDataRel.setRecordId(GenericPo.createUID());
                object.setResourceId(metaDataRel.getRecordId());
                metaDataRel.setDataId(object.getResourceId());
                metaDataRel.setType(old.getResourceTypeInt());
                metaDataRelService.insert(metaDataRel);
            }
        }

        if (object instanceof GenericPo) {
            ((GenericPo) object).setId(id);
        }
        object.setUserId(WebUtil.getLoginUser().getId());
        int number = getService().update(object);
        return ok(number);
    }

    /**
     * 查询列表,并返回查询结果
     *
     * @param param 查询参数 {@link QueryParam}
     * @return 查询结果, 如果参数指定了分页(默认指定)将返回格式如:{total:数据总数,data:[{}]}的数据.
     * 否则返回格式[{}]
     */
    @RequestMapping(value = "/ad_list",method = RequestMethod.GET)
    @AccessLogger("查询列表")
    @Authorize(action = "R")
    public ResponseMessage getAllAD(QueryParam param) {
        // 获取条件查询
        Object data;
        if (!param.isPaging())//不分页
            data = getService().selectADList(param);
        else
            data = getService().selectADListPage(param);
        return ok(data)
                .include(getPOType(), param.getIncludes())
                .exclude(getPOType(), param.getExcludes())
                .onlyData();
    }

    /**
     * 更具区域id，返回区域内店铺设备上已分配的广告信息列表
     *
     * @param param 查询参数 {@link QueryParam}
     *        id    区域树中选中节点的id值
     * @return 查询结果, 如果参数指定了分页(默认指定)将返回格式如:{total:数据总数,data:[{}]}的数据.
     * 否则返回格式[{}]
     */
    @RequestMapping(value = "/ad-dist/{id}",method = RequestMethod.GET)
    @AccessLogger("查询列表")
    @Authorize(action = "R")
    public ResponseMessage getADDist(@PathVariable("id")String id, QueryParam param) {
        // 获取条件查询
        Object data;
        if (!param.isPaging())//不分页
            data = getService().selectADDist(param, id);
        else
            data = getService().selectADDistPage(param ,id);
        return ok(data)
                .include(getPOType(), param.getIncludes())
                .exclude(getPOType(), param.getExcludes())
                .onlyData();
    }

    /**
     * 请求禁用指定id广告与设备绑定关系
     *
     * @param id 要禁用的id标识
     * @return 删除结果
     * @throws NotFoundException 要删除的数据不存在
     */
    @RequestMapping(value = "/disable/{id}", method = RequestMethod.PUT)
    @AccessLogger("修改")
    @Authorize(action = "U")
    @Transactional
    public ResponseMessage disable(@PathVariable("id") String id) {
        ADDevice old = aDDeviceService.selectByPk(id);
        assertFound(old, "data is not found!");
        old.setStatus(1);
        aDDeviceService.update(old);
        return ok();
    }

    /**
     * 请求启用指定id广告与设备绑定关系
     *
     * @param id 要启用的id标识
     * @return 删除结果
     * @throws NotFoundException 要删除的数据不存在
     */
    @RequestMapping(value = "/enable/{id}", method = RequestMethod.PUT)
    @AccessLogger("修改")
    @Authorize(action = "U")
    @Transactional
    public ResponseMessage enable(@PathVariable("id") String id) {
        ADDevice old = aDDeviceService.selectByPk(id);
        assertFound(old, "data is not found!");
        old.setStatus(0);
        aDDeviceService.update(old);
        return ok();
    }


}
