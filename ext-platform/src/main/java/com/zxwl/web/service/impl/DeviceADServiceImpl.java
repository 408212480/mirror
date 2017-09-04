package com.zxwl.web.service.impl;

import com.zxwl.web.bean.*;
import com.zxwl.web.bean.common.InsertParam;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.dao.*;
import com.zxwl.web.service.ADDeviceService;
import com.zxwl.web.service.impl.AbstractServiceImpl;
import com.zxwl.web.service.DeviceADService;
import com.zxwl.web.util.AreaShopUtil;
import groovy.lang.ObjectRange;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 设备广告 服务类实现
 * Created by wuei
 */
@Service("deviceADService")
public class DeviceADServiceImpl extends AbstractServiceImpl<DeviceAD, String> implements DeviceADService {

    private String imgPath = "/file/image/";
    private String videoPath = "/file/download/";

    @javax.annotation.Resource
    protected DeviceADMapper deviceADMapper;

    @javax.annotation.Resource
    protected AreaMapper areaMapper;

    @javax.annotation.Resource
    protected ShopMapper shopMapper;

    @javax.annotation.Resource
    protected ShopDeviceMapper shopDeviceMapper;

    @javax.annotation.Resource
    private ADDeviceService aDDeviceService;

    @javax.annotation.Resource
    private ADDeviceMapper adDeviceMapper;

    @Override
    protected DeviceADMapper getMapper() {
        return this.deviceADMapper;
    }

    @Override
    public List<DeviceAD> selectDeviceAD(QueryParam param) {
        List<DeviceAD> result = this.getMapper().selectDeviceAD(param);
        for(DeviceAD deviceAD:result){
            deviceAD.setResourceType((deviceAD.getResourceTypeInt() == 0)? "图片":"视频");
        }
        return result;
    }

    @Override
    public PagerResult<DeviceAD> selectDeviceADPage(QueryParam param) {
        param.setPaging(false);
        int total = this.getMapper().total(param);
        PagerResult<DeviceAD> result = new PagerResult<>();
        result.setTotal(total);
        if(total == 0){
            result.setData(new ArrayList<>());
        }
        else {
            param.setPaging(true);
            //如果pageIndex大于total，则显示最后一页
            if(param.getPageIndex() + 1 > total){
                int page = total/param.getPageSize();
                param.setPageIndex(page * param.getPageSize());
            }
            List<DeviceAD> deviceADS = this.getMapper().selectDeviceADPage(param);
            //查询出resourceTypeInt为0，则设置resourceType为“图片”，否则设置resourceType为“视频”
            for(DeviceAD deviceAD:deviceADS){
                deviceAD.setResourceType((deviceAD.getResourceTypeInt() == 0)? "图片":"视频");
            }

            result.setData(deviceADS);
        }
        return result;
    }

    @Override
    public Resource selectResourceById(String id) {
        return getMapper().selectResourceById(id);
    }

    private void  setImgOrVideoPath(List<DeviceAD> deviceADS){
        for(DeviceAD deviceAD:deviceADS){
            if(deviceAD.getResourceTypeInt()==1){
                deviceAD.setResourceType("视频");
                deviceAD.setMd5(videoPath + deviceAD.getMd5());
            }
            else{
                deviceAD.setResourceType("图片");
                deviceAD.setMd5(imgPath + deviceAD.getMd5());
            }
        }
    }

    @Override
    public List<DeviceAD> selectADList(QueryParam param) {
        List<DeviceAD> result = this.getMapper().selectADList(param);
        setImgOrVideoPath(result);
        return result;
    }

    @Override
    public PagerResult<DeviceAD> selectADListPage(QueryParam param) {
        param.setPaging(false);
        int total = this.getMapper().total(param);
        PagerResult<DeviceAD> result = new PagerResult<>();
        result.setTotal(total);
        if(total == 0){
            result.setData(new ArrayList<>());
        }
        else {
            param.setPaging(true);
            //如果pageIndex大于total，则显示最后一页
            if(param.getPageIndex() + 1 > total){
                int page = total/param.getPageSize();
                param.setPageIndex(page * param.getPageSize());
            }
            List<DeviceAD> deviceADs = this.getMapper().selectADListPage(param);
            //查询出resourceTypeInt为0，则设置resourceType为“图片”，否则设置resourceType为“视频”
            setImgOrVideoPath(deviceADs);

            result.setData(deviceADs);
        }
        return result;
    }

    @Override
    public List<DeviceAD> selectADDist(QueryParam param, String areaId) {
        ArrayList<String> shopIds = AreaShopUtil.findAllShopByAreaId(areaId, areaMapper, shopMapper);
        if(shopIds.size() == 0){
            return new ArrayList<>();
        }
        List<DeviceAD> result=this.getMapper().selectADDist(shopIds);
        setImgOrVideoPath(result);
        return result;
    }

    @Override
    public PagerResult<DeviceAD> selectADDistPage(QueryParam param, String areaId) {
        PagerResult<DeviceAD> result = new PagerResult<>();
        ArrayList<String> shopIds = AreaShopUtil.findAllShopByAreaId(areaId, areaMapper, shopMapper);
        int total = selectADDist(param, areaId).size();
        result.setTotal(total);
        if(total == 0){
            result.setData(new ArrayList<>());
            return result;
        }
        if(param.getPageIndex() >= total){   //如果pageIndex大于total，则展示最后一页
            int temp = total/param.getPageSize();
            if(temp > 0 && (total%param.getPageSize())==0)
                temp--;
            param.setPageIndex(temp*(param.getPageSize()));
        }

        List<DeviceAD> deviceADs = this.getMapper().selectADDistPage(param.getPageIndex(), param.getPageSize(), shopIds);
        setImgOrVideoPath(deviceADs);
        result.setData(deviceADs);
        return result;
    }

    /**
     * 将广告发布到区域内的所有店铺设备上
     * @param areaId 区域id
     * @param adIds  待发布的广告
     * @return 发布到设备上的广告数量
     */

    @Override
    public int addADsToArea(String areaId, String[] adIds) {
        if("area_tree".equals(areaId)){
            return 0;
        }
        ArrayList<String> shopIds = AreaShopUtil.findAllShopByAreaId(areaId, areaMapper, shopMapper);

        int sum = 0;
        for(String shopId:shopIds){
            List<ShopDevice> shopDevices = shopDeviceMapper.selectByShopId(shopId);     //找出店铺内所有设备
            ADDevice adDevice = new ADDevice();
            for(ShopDevice shopDevice:shopDevices){

                if(shopDevice.getDeviceId() == null || "".equals(shopDevice.getDeviceId())){
                    continue;
                }
                adDevice.setDeviceId(shopDevice.getDeviceId());
                for(String adId:adIds){         //将店铺内所有设备与广告关联，即发布广告到这个设备上，完成广告发布到区域内所有店铺的目的
                    ADDevice exist = adDeviceMapper.selectByADAndDeviceId(adId, shopDevice.getDeviceId());
                    if(adId == null || "".equals(adId) || exist != null){       //如果exist ！= null， 则说明设备中已有这个广告了，没必要再进行添加
                        continue;
                    }

                    adDevice.setAdId(adId);

                    String id = aDDeviceService.insert(adDevice);
                    if(id != null){
                        sum ++;
                    }
                    adDevice.setId(null);
                }
            }

        }
        return sum;
    }
}
