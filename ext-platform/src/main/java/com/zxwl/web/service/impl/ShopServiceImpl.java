package com.zxwl.web.service.impl;

import com.zxwl.pay.common.util.str.StringUtils;
import com.zxwl.web.bean.Shop;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.dao.AreaMapper;
import com.zxwl.web.dao.ShopMapper;
import com.zxwl.web.service.ShopService;
import com.zxwl.web.service.resource.ResourcesService;
import com.zxwl.web.util.AreaShopUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 店铺信息 服务类实现
 * Created by generator
 */
@Service("shopService")
public class ShopServiceImpl extends AbstractServiceImpl<Shop, String> implements ShopService {

    private String imgPath = "/file/image/";

    private String basePath = "/";

    @Resource
    protected ShopMapper shopMapper;

    @Resource
    protected AreaMapper areaMapper;

    @Resource
    protected ResourcesService resourcesService;

    @Override
    protected ShopMapper getMapper() {
        return this.shopMapper;
    }

    //查找区域id下所有店铺信息
    @Override
    public ArrayList<Shop> selectShopInArea(String areaId) {
        ArrayList<String> areaIds = new ArrayList<>();
        if(areaId == null || "".equals(areaId) || "area_tree".equals(areaId)){
            return new ArrayList<>();
        }
        areaIds.add(areaId);
        AreaShopUtil.getChildAreaId(areaIds, areaId, areaMapper);

        ArrayList<Shop> result = this.getMapper().selectShopInfoByAreaId(areaIds);

        result = resetShopImgPath(basePath, result);

        return result;
    }

    private ArrayList<Shop> resetShopImgPath(String basePath, ArrayList<Shop> shopList){
        for(Shop shop:shopList){
            if(StringUtils.isNotEmpty(shop.getLogo())){
                String logoUrl = resourcesService.selectSingleImage(basePath, shop.getLogo());
                shop.setLogo(logoUrl);
            }
            if(StringUtils.isNotEmpty(shop.getBusinessUrl())){
                String businessUrl = resourcesService.selectSingleImage(basePath, shop.getBusinessUrl());
                shop.setBusinessUrl(businessUrl);
            }
            if(StringUtils.isNotEmpty(shop.getImg1())){
                String img1Url = resourcesService.selectSingleImage(basePath, shop.getImg1());
                shop.setImg1(img1Url);
            }
            if(StringUtils.isNotEmpty(shop.getImg2())){
                String img2Url = resourcesService.selectSingleImage(basePath, shop.getImg2());
                shop.setImg2(img2Url);
            }
            if(StringUtils.isNotEmpty(shop.getImg3())){
                String img3Url = resourcesService.selectSingleImage(basePath, shop.getImg3());
                shop.setImg3(img3Url);
            }
        }

        return shopList;
    }

    //查找分页条件下，区域id下所有店铺信息
    @Override
    public PagerResult<Shop> selectShopInAreaPager(QueryParam param, String areaId) {
        PagerResult<Shop> result = new PagerResult<>();
        ArrayList<String> areaIds = new ArrayList<>();
        if(areaId == null || "".equals(areaId) || "area_tree".equals(areaId)){
            result.setTotal(0);
            return result;
        }
        areaIds.add(areaId);
        AreaShopUtil.getChildAreaId(areaIds, areaId, areaMapper);       //获取区域下所有子区域id，并存在areaIds中
        ArrayList<Shop> shops = selectShopInArea(areaId);
        int total = shops.size();
        int pageIndex = param.getPageIndex();
        int pageSize = param.getPageSize();
        result.setTotal(total);
        if(pageIndex >= total){         //如果pageIndex大于total，则显示最后一页
            int temp = total % pageSize;
            pageIndex = total - temp;
        }
        shops = this.getMapper().selectShopInfoByAreaIdPager(pageIndex, pageSize, areaIds);

        shops = resetShopImgPath(basePath, shops);

        result.setData(shops);
        return result;
    }

    @Override
    public String insert(Shop data) {
        return super.insert(data);
    }

    @Override
    public int update(Shop data) {
        return super.update(data);
    }

    @Override
    public int update(List<Shop> data) {
        return super.update(data);
    }

    @Override
    public Shop selectShopInfoById(String id) {
        Shop shop = this.getMapper().selectShopInfoById(id);
        if(shop != null){
            if(StringUtils.isNotEmpty(shop.getLogo()) && resourcesService.selectSingleImage(shop.getLogo()) != null){
                shop.setLogo(resourcesService.selectSingleImage(shop.getLogo()).getId());
            }
            if(StringUtils.isNotEmpty(shop.getBusinessUrl()) && resourcesService.selectSingleImage(shop.getBusinessUrl()) != null){
                shop.setBusinessUrl(resourcesService.selectSingleImage(shop.getBusinessUrl()).getId());
            }
            if(StringUtils.isNotEmpty(shop.getImg1()) && resourcesService.selectSingleImage(shop.getImg1()) != null){
                shop.setImg1(resourcesService.selectSingleImage(shop.getImg1()).getId());
            }
            if(StringUtils.isNotEmpty(shop.getImg2()) && resourcesService.selectSingleImage(shop.getImg2()) != null){
                shop.setImg2(resourcesService.selectSingleImage(shop.getImg2()).getId());
            }
            if(StringUtils.isNotEmpty(shop.getImg3()) && resourcesService.selectSingleImage(shop.getImg3()) != null){
                shop.setImg3(resourcesService.selectSingleImage(shop.getImg3()).getId());
            }
        }

        return shop;
    }
}
