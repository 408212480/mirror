package com.zxwl.web.service.impl;

import com.zxwl.web.bean.Area;
import com.zxwl.web.bean.DeviceFidGoodsSpec;
import com.zxwl.web.bean.Shop;
import com.zxwl.web.bean.ShopDevice;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.dao.AreaMapper;
import com.zxwl.web.dao.DeviceFidGoodsSpecMapper;
import com.zxwl.web.dao.ShopMapper;
import com.zxwl.web.service.DeviceFidGoodsSpecService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 射频商品规格关联表 服务类实现
 * Created by generator
 */
@Service("deviceFidGoodsSpecService")
public class DeviceFidGoodsSpecServiceImpl extends AbstractServiceImpl<DeviceFidGoodsSpec, String> implements DeviceFidGoodsSpecService {

    @Resource
    protected DeviceFidGoodsSpecMapper deviceFidGoodsSpecMapper;

    @Resource
    protected ShopMapper shopMapper;

    @Override
    protected DeviceFidGoodsSpecMapper getMapper() {
        return this.deviceFidGoodsSpecMapper;
    }
  
    @Override
    public String insert(DeviceFidGoodsSpec data) {
        return super.insert(data);
    }
  
    @Override
    public int update(DeviceFidGoodsSpec data) {
        return super.update(data);
    }
  
    @Override
    public int update(List<DeviceFidGoodsSpec> data) {
        return super.update(data);
    }




    @Override
    @Transactional
    public int BulkInsert(String[] deviceIds) {
        if(deviceIds != null && deviceIds.length >= 2){
            int length = deviceIds.length;
            String shopId = deviceIds[length - 1];
            for (int i = 0; i < length - 1; i++) {
                DeviceFidGoodsSpec deviceFidGoodsSpec = new DeviceFidGoodsSpec();
                deviceFidGoodsSpec.setFidId(String.valueOf(deviceIds[i]));
                deviceFidGoodsSpec.setShopId(String.valueOf(shopId));
                super.insert(deviceFidGoodsSpec);
            }

        }
        return 0;
    }
//将传入的shopId参数加入QueryParam中
    private QueryParam preHandleParam(QueryParam param, String shopId){
                    param = param.where("shop_id", shopId);
        return param;
    }
    @Override
    public List<DeviceFidGoodsSpec> selectByShopId(QueryParam param, String shopId) {
        param = preHandleParam(param, shopId);
        if(param != null){
            List<DeviceFidGoodsSpec> deviceFidGoodsSpecs = this.getMapper().select(param);
            return deviceFidGoodsSpecs;
        }
        else{
            //返回空值
            return new LinkedList<>();
        }
    }

    @Override
    public PagerResult<DeviceFidGoodsSpec> selectByShopIdPager(QueryParam param, String shopId) {
        param = preHandleParam(param, shopId);
        if(param != null){
            PagerResult<DeviceFidGoodsSpec> shopDevicesPage = super.selectPager(param);
            List<DeviceFidGoodsSpec> deviceFidGoodsSpecs = shopDevicesPage.getData();
            shopDevicesPage.setData(deviceFidGoodsSpecs);
            return shopDevicesPage;
        }
        else{
            //返回空值
            return new PagerResult<>();
        }
    }

    @Override
    @Transactional
    public Integer deleteAll(String shopId) {

        return getMapper().deleteAll(shopId);
    }
}
