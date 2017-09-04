package com.zxwl.web.service.impl;

import com.zxwl.web.bean.Area;
import com.zxwl.web.bean.Device;
import com.zxwl.web.bean.Shop;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.ShopDevice;
import com.zxwl.web.dao.AreaMapper;
import com.zxwl.web.dao.DeviceMapper;
import com.zxwl.web.dao.ShopDeviceMapper;
import com.zxwl.web.dao.ShopMapper;
import com.zxwl.web.service.impl.AbstractServiceImpl;
import com.zxwl.web.service.ShopDeviceService;
import com.zxwl.web.util.AreaShopUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 店铺设备关联表 服务类实现
 * Created by generator
 */
@Service("shopDeviceService")
public class ShopDeviceServiceImpl extends AbstractServiceImpl<ShopDevice, String> implements ShopDeviceService {

    @Resource
    protected ShopDeviceMapper shopDeviceMapper;

    @Resource
    protected DeviceMapper deviceMapper;

    @Resource
    protected AreaMapper areaMapper;

    @Resource
    protected ShopMapper shopMapper;

    @Override
    protected ShopDeviceMapper getMapper() {
        return this.shopDeviceMapper;
    }
  
    @Override
    public String insert(ShopDevice data) {
        return super.insert(data);
    }



    /**
     *
     * @param param 查询参数 {@link QueryParam}
     * @param shopId 选中的区域id。由于区域树中有的节点是区域，有的节点上店铺，因此参数shopId可能是店铺id，也可能是区域i
     * @return 返回查询结果
     */
    @Override
    public List<ShopDevice> selectByShopId(QueryParam param, String shopId) {
        param = preHandleParam(param, shopId);
        if(param != null){
            List<ShopDevice> shopDevices = this.getMapper().select(param);
            for(ShopDevice shopDevice:shopDevices){
                String code = deviceMapper.selectByPk(shopDevice.getDeviceId()).getCode();
                shopDevice.setCode(code);
                shopDevice.setDistTime(shopDevice.getGmtCreate().toString());
            }
            return shopDevices;
        }
        else{
            //返回空值
            return new LinkedList<>();
        }

    }

    //查询shop时对param进行预处理，把选中节点下的所有shopid都找出来，然后为param添加 or shop_id = #{shopId}条件
    private QueryParam preHandleParam(QueryParam param, String shopId){
        ArrayList<String> shopIds = AreaShopUtil.findAllShopByAreaId(shopId, areaMapper, shopMapper);
        for(String id:shopIds){
            //把条件加入查询条件
            param = param.or("shop_id", id);
        }
        //如果param内terms条件为空，则说明区域内没有店铺
        if(param.getTerms().size() == 0){
            param = null;
        }
        return param;
    }

    /**
     *
     * @param param 查询参数 {@link QueryParam}
     * @param shopId 选中的区域id。由于区域树中有的节点是区域，有的节点上店铺，因此参数shopId可能是店铺id，也可能是区域i
     * @return 返回查询结果
     */
    @Override
    public PagerResult<ShopDevice> selectByShopIdPager(QueryParam param, String shopId) {
        param = preHandleParam(param, shopId);
        if(param != null){
            PagerResult<ShopDevice> shopDevicesPage = super.selectPager(param);
            List<ShopDevice> shopDevices = shopDevicesPage.getData();
            for(ShopDevice shopDevice:shopDevices){
                String code = deviceMapper.selectByPk(shopDevice.getDeviceId()).getCode();
                shopDevice.setCode(code);
                shopDevice.setDistTime(shopDevice.getGmtCreate().toString());
            }
            shopDevicesPage.setData(shopDevices);
            return shopDevicesPage;
        }
        else{
            //返回空值
            PagerResult<Object> noResult = new PagerResult<>();
            noResult.setTotal(0);
            noResult.setData(new ArrayList<>());
            return new PagerResult<>();
        }
    }

    //返回未分配的设备列表
    @Override
    public List<Device> selectUndist(QueryParam param) {
        return deviceMapper.selectNotInShopDevice();
    }

    //返回未分配的设备列表
    @Override
    public PagerResult<Device> selectUndistPager(QueryParam param) {
        PagerResult<Device> pagerResult = new PagerResult<>();
        param.setPaging(false);
        List<Device> devices = deviceMapper.selectNotInShopDevice();
        int total = devices.size();
        pagerResult.setTotal(total);
        if (total == 0) {
            pagerResult.setData(new ArrayList<>());
        } else {
            //根据实际记录数量重新指定分页参数
            param.setPaging(true);
            pagerResult.setData(deviceMapper.selectNotInShopDevicePager(param));
        }
        return pagerResult;
    }

    @Override
    public int deleteShopInArea(String areaId) {
        ArrayList<String> shopIds = AreaShopUtil.findAllShopByAreaId(areaId, areaMapper, shopMapper);
        int sum = 0;
        for(String id:shopIds){
            int i = getMapper().deleteByShopId(id);
            sum +=i;
        }
        return sum;
    }
}
