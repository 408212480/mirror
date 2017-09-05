package com.zxwl.web.controller;

import com.zxwl.web.bean.MetaDataRel;
import com.zxwl.web.bean.ShopDecoration;
import com.zxwl.web.bean.po.GenericPo;
import com.zxwl.web.bean.po.resource.Resources;
import com.zxwl.web.core.exception.ValidationException;
import com.zxwl.web.core.utils.WebUtil;
import com.zxwl.web.service.MetaDataRelService;
import com.zxwl.web.service.ShopDecorationService;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.core.exception.NotFoundException;
import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.Shop;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.service.resource.ResourcesService;
import org.apache.poi.util.StringUtil;
import org.thymeleaf.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.zxwl.web.service.ShopService;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.zxwl.web.core.message.ResponseMessage.created;
import static com.zxwl.web.core.message.ResponseMessage.ok;

/**
 * 店铺信息控制器
 * Created by wuei 2017.8.31
 */
@RestController
@RequestMapping(value = "/shop")
@AccessLogger("店铺信息")
@Authorize(module = "shop")
public class ShopController extends GenericController<Shop, String> {

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

    /**
     * 查询列表,并返回查询结果
     *
     * @param param 查询参数 {@link QueryParam}
     * @return 查询结果, 如果参数指定了分页(默认指定)将返回格式如:{total:数据总数,data:[{}]}的数据.
     * 否则返回格式[{}]
     */
    @RequestMapping(value = "/InArea/{id}", method = RequestMethod.GET)
    @AccessLogger("查询列表")
    @Authorize(action = "R")
    public ResponseMessage list(@PathVariable("id") String areaId, QueryParam param) {
        Object data;
        if (param == null || !param.isPaging()) {
            data = getService().selectShopInArea(areaId);
        } else {
            data = getService().selectShopInAreaPager(param, areaId);
        }
        return ok(data)
                .include(Shop.class, param.getIncludes())
                .exclude(Shop.class, param.getExcludes())
                .onlyData();

    }

    /**
     * 根据店铺id，返回店铺详细信息，包括店铺图片id等
     *
     * @param id 要修改数据的主键值
     * @return 请求结果
     * @throws NotFoundException 要修改的数据不存在
     */
    @RequestMapping(value = "/shopInfo/{id}", method = RequestMethod.GET)
    @AccessLogger("查询")
    @Authorize(action = "R")
    public ResponseMessage selectShopById(@PathVariable("id") String id) {
        Shop shop = this.getService().selectShopInfoById(id);
        if (shop == null) {
            return ResponseMessage.error("找不到对应数据！");
        }
        return ResponseMessage.created(shop);
    }

    /**
     * 根据主键修改数据
     *
     * @param id   要修改数据的主键值
     * @param shop 要修改的数据
     * @return 请求结果
     * @throws NotFoundException 要修改的数据不存在
     * @throws javax.validation.ValidationException 验证数据格式错误
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @AccessLogger("修改")
    @Authorize(action = "U")
    @Transactional
    public ResponseMessage update(@PathVariable("id") String id, @RequestBody Shop shop) {
        if (shop.getLogo() == null && "".equals(shop.getLogo())) {
            throw new ValidationException("店铺logo图片不能为空！");
        }
        if (shop.getBusinessUrl() == null && "".equals(shop.getBusinessUrl())) {
            throw new ValidationException("店铺营业执照图片不能为空！");
        }
        if (shop.getImg1() == null && "".equals(shop.getImg1())) {
            throw new ValidationException("店铺图片不能为空！");
        }
        Shop old = getService().selectByPk(id);
        assertFound(old, "data is not found!");
        ShopDecoration shopde = shopDecorationService.selectByShopID(id);

        if (shopde == null) {
            shopde = new ShopDecoration();
            shopde.setShopId(id);
        }
        String recordId = null;
        recordId = updateImg(shop.getImg1(), shopde.getImg1());
        shopde.setImg1(recordId);
        recordId = updateImg(shop.getImg2(), shopde.getImg2());
        shopde.setImg2(recordId);
        recordId = updateImg(shop.getImg3(), shopde.getImg3());
        shopde.setImg3(recordId);
        recordId = updateImg(shop.getLogo(), old.getLogo());
        shop.setLogo(recordId);
        recordId = updateImg(shop.getBusinessUrl(), old.getBusinessUrl());
        shop.setBusinessUrl(recordId);

        shop.setId(id);
        //设置区域编码
        shop.setAreaId(old.getAreaId());
        //设置创建用户id
        shop.setCreatorId(WebUtil.getLoginUser().getId());
        int number = getService().update(shop);
        old.setContent(shopde.getContent());

        shopde.setContent(shop.getContent());
        if (shopde.getId() == null) {
            shopDecorationService.insert(shopde);
        } else {
            shopDecorationService.update(shopde);
        }

        //如果图片有改变，则删除t_meta_data_rel中的相应记录，重新插入新的图片记录
        if (!compareContent(old.getContent(), shop.getContent())) {
            if(old.getContent() != null){
                metaDataRelService.deleteByRecordId(shopde.getId());        //t_meta_data_rel中的相应记录
            }
            if(shop.getContent() != null){
                //解析店铺图文信息中的资源id，找到图文信息中的资源id，并插入t_metadata_rel表中
                insertResourceInContentToRel(shop.getContent(), shopde.getId());
            }
            ArrayList<String> contentImgs = getResourcesIdInContent(old.getContent());
            for(String imgId:contentImgs){      //删除s_resources中的相应记录
                deleteResources(imgId);
            }

        }
        return ok(number);
    }

    //更新店铺操作中，更新店铺decoration表中的图片, 返回t_metadata_rel中的recordId
    private String updateImg(String newImgId, String oldRecordId){
        List<MetaDataRel> mdrList = metaDataRelService.selectByRecordId(oldRecordId);
        MetaDataRel oldMdr = null;
        if(mdrList != null && mdrList.size() > 0){
            oldMdr = mdrList.get(0);
        }
        if(oldMdr != null && oldMdr.getDataId() != null){
            if(newImgId != null && !oldMdr.getDataId().equals(newImgId)){ //如果原图片和更新图片都存在，且不相等，则更新图片
                String oldDataId = oldMdr.getDataId();
                oldMdr.setDataId(newImgId);
                metaDataRelService.update(oldMdr);
                deleteResources(oldDataId);
            }
            if(newImgId == null){         //如果更新图片为空，则删除原图片与店铺的关系记录
                metaDataRelService.deleteByRecordId(oldMdr.getRecordId());
                deleteResources(oldMdr.getDataId());
                return "";
            }
        }else{
            //如果原图片为空，更新图片不为空，则添加新图片与shopde的关联到t_metadata_rel
            if(newImgId != null){
                String recordId = GenericPo.createUID();
                insertMetaDataRef(recordId, newImgId);
                return recordId;
            }
        }
        return oldRecordId;
    }

    private ArrayList<String> getResourcesIdInContent(String content){
        ArrayList<String> result = new ArrayList<>();
        String[] array = content.split("/file/image/");
        if (array.length > 1) {
            for (int i = 1; i < array.length; i++) {
                if (array[i].length() >= 7) {
                    String id = array[i].substring(0, 6);
                    result.add(id);
                }
            }
        }
        return result;
    }

    //根据判断两个shop的图文信息所有图片是否相同
    private boolean compareContent(String oldContent, String newContent) {
        if(oldContent != null && newContent != null){
            ArrayList<String> oldResourcesId = getResourcesIdInContent(oldContent);
            ArrayList<String> newResourcesId = getResourcesIdInContent(newContent);
            if(oldResourcesId.size() == newResourcesId.size()){
                for(String id:oldResourcesId){
                    if(!newResourcesId.contains(id)){
                        return false;
                    }
                }
                return true;
            }
        }
        if(oldContent == null && newContent == null){
            return true;
        }

        return false;
    }

    private String insertMetaDataRef(String recordId, String dataId) {
        MetaDataRel metaDataRel = new MetaDataRel();
        metaDataRel.setDataId(dataId);
        metaDataRel.setRecordId(recordId);
        metaDataRel.setType(0);
        metaDataRel.setDataType(5);
        String id = metaDataRelService.insert(metaDataRel);
        return id;
    }

    //如果t_metadata_rel中无图片资源的引用记录，则说明没有表格用到这张图片，可删除这张图片
    private void deleteResources(String id) {
        ArrayList<MetaDataRel> rels = (ArrayList) metaDataRelService.selectByDataId(id);
        if (rels == null || rels.size() == 0 ) {
            Resources resources = resourcesService.selectByPk(id);
            if(resources != null){
                resourcesService.delete(id);
            }
        }
    }

    //解析店铺图文信息中的资源id，找到图文信息中的资源id,并将资源id与shopDecorationId插入t_metadata_rel关联表
    private int insertResourceInContentToRel(String content, String shopDeId) {
        String[] array = content.split("/file/image/");
        int count = 0;
        if (array.length > 1) {
            for (int i = 1; i < array.length; i++) {
                if (array[i].length() >= 7) {
                    String id = array[i].substring(0, 6);
                    Resources resource = resourcesService.selectByPk(id);
                    if (resource != null) {
                        insertMetaDataRef(shopDeId, id);
                        count ++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * 请求添加数据，请求必须以POST方式
     *
     * @param shop 请求添加的对象
     * @return 被添加数据的主键值
     * @throws javax.validation.ValidationException 验证数据格式错误
     */
    @RequestMapping(method = RequestMethod.POST)
    @AccessLogger("新增")
    @Authorize(action = "C")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseMessage add(@RequestBody Shop shop) {
        if (shop.getLogo() == null && "".equals(shop.getLogo())) {
            throw new ValidationException("店铺logo图片不能为空！");
        }
        if (shop.getBusinessUrl() == null && "".equals(shop.getBusinessUrl())) {
            throw new ValidationException("店铺营业执照图片不能为空！");
        }
        if (shop.getImg1() == null && "".equals(shop.getImg1())) {
            throw new ValidationException("店铺图片不能为空！");
        }

        //设置创建用户id
        shop.setCreatorId(WebUtil.getLoginUser().getId());
        //插入logo 与t_metadata_rel 关联数据
        String uuid = GenericPo.createUID();
        insertMetaDataRef(uuid, shop.getLogo());
        shop.setLogo(uuid);

        //插入businiss_url 与t_metadata_rel 关联数据
        uuid = GenericPo.createUID();
        insertMetaDataRef(uuid, shop.getBusinessUrl());
        shop.setBusinessUrl(uuid);

        String id = getService().insert(shop);

        ShopDecoration shopDecoration = new ShopDecoration();
        shopDecoration.setShopId(id);
        shopDecoration.setContent(shop.getContent());

        if(shop.getImg1() != null && !"".equals(shop.getImg1())){
            uuid = GenericPo.createUID();
            insertMetaDataRef(uuid, shop.getImg1());
            shop.setImg1(uuid);
            shopDecoration.setImg1(shop.getImg1());
        }

        if(shop.getImg2() != null && !"".equals(shop.getImg2())){
            uuid = GenericPo.createUID();
            insertMetaDataRef(uuid, shop.getImg2());
            shop.setImg2(uuid);
            shopDecoration.setImg2(shop.getImg2());
        }

        if(shop.getImg3() != null && !"".equals(shop.getImg3())){
            uuid = GenericPo.createUID();
            insertMetaDataRef(uuid, shop.getImg3());
            shop.setImg3(uuid);
            shopDecoration.setImg3(shop.getImg3());
        }

        String decorationId = shopDecorationService.insert(shopDecoration);

        insertResourceInContentToRel(shop.getContent(), decorationId);

        return created(id);
    }

    /**
     * @param id 待删除图片id
     * @return
     */
    @RequestMapping(value = "/img/{id}", method = RequestMethod.DELETE)
    @AccessLogger("删除图片")
    @Transactional
    public ResponseMessage deleteImg(@PathVariable("id") String id) {
        deleteResources(id);
        return ResponseMessage.ok();
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
    @Transactional
    public ResponseMessage delete(@PathVariable("id") String id) {
        Shop old = getService().selectByPk(id);
        assertFound(old, "data is not found!");
        getService().delete(id);            //从t_shop中删除shop
        ShopDecoration shopDecoration = shopDecorationService.selectByShopID(id);

        deleteByRecordId(old.getLogo());
        deleteByRecordId(old.getBusinessUrl());
        if(shopDecoration != null){
            shopDecorationService.deleteByShopID(id);       //从t_shop_decoration中删除
            deleteByRecordId(shopDecoration.getImg1());
            deleteByRecordId(shopDecoration.getImg2());
            deleteByRecordId(shopDecoration.getImg3());
            deleteByRecordId(shopDecoration.getId());           //删除content中图片与shopDecoration在t_metadata_rel中的关联数据

        }

        return ok();
    }

    //根据t_metadata_rel中的recordId ，删除图片与shop或者shopDecoration关联记录
    private void deleteByRecordId(String recordId){
        List<MetaDataRel> rel = metaDataRelService.selectByRecordId(recordId);
        if(rel != null && rel.size() > 0){
            metaDataRelService.deleteByRecordId(recordId);
            for(MetaDataRel metaDataRel:rel){
                List<MetaDataRel> rels = metaDataRelService.selectByDataId(metaDataRel.getDataId());
                if(rels == null || rels.size() == 0){
                    resourcesService.delete(metaDataRel.getDataId());
                }
            }

        }
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    @AccessLogger("详情")
    public ResponseMessage shopView(String shopId, HttpServletRequest req) {
        ShopDecoration shop = shopDecorationService.shopView(shopId);
        if (!StringUtils.isEmpty(shop.getImg1()))
            resourcesService.selectSingleImage(WebUtil.getBasePath(req), shop.getImg1());
        if (!StringUtils.isEmpty(shop.getImg2()))
            resourcesService.selectSingleImage(WebUtil.getBasePath(req), shop.getImg2());
        if (!StringUtils.isEmpty(shop.getImg3()))
            resourcesService.selectSingleImage(WebUtil.getBasePath(req), shop.getImg3());
        if (!StringUtils.isEmpty(shop.getMd5()))
            resourcesService.selectSingleImage(WebUtil.getBasePath(req), shop.getMd5());
        return ok(shopDecorationService.shopView(shopId));
    }

    /**
     * @author wuei
     * 用于接收fileinput插件的删除图片消息，对于编辑店铺时，如果删除图片，则不做任何动作，返回删除成功，编辑店铺完成点击保存后再从数据库中删除图片
     * @param
     * @return
     */
    @RequestMapping(value = "/img/delete", method = RequestMethod.POST)
    @AccessLogger("删除已上传的logo")
    @Authorize(action = "D")
    public ResponseMessage deleteLogo() {
        return ok();
    }
}
