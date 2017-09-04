package com.zxwl.web.service.impl;

import com.zxwl.web.bean.DeviceFidGoodsSpec;
import com.zxwl.web.bean.DeviceUseInfs;
import com.zxwl.web.bean.api.PagerParamApi;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.dao.DeviceUseInfsMapper;
import com.zxwl.web.service.DeviceUseInfsService;
import com.zxwl.web.util.ResourceUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 设备使用记录表 服务类实现
 * Created by generator
 */
@Service("deviceUseInfsService")
public class DeviceUseInfsServiceImpl extends AbstractServiceImpl<DeviceUseInfs, String> implements DeviceUseInfsService {

    @Resource
    protected DeviceUseInfsMapper deviceUseInfsMapper;

    @Override
    protected DeviceUseInfsMapper getMapper() {
        return this.deviceUseInfsMapper;
    }

    @Override
    public List<DeviceUseInfs> getDeviceInfs(String deviceId) {


        return getMapper().getDeviceInfs(deviceId);
    }
    //将传入的shopId参数加入QueryParam中
    private QueryParam preHandleParam(QueryParam param, String deviceId){
        param = param.where("device_id", deviceId);
        return param;
    }
    @Override
    public List<DeviceUseInfs> selectByDeviceId(QueryParam param, String deviceId) {
        param = preHandleParam(param, deviceId);
        if(param != null){
            List<DeviceUseInfs> deviceFidGoodsSpecs = this.getMapper().select(param);
            return deviceFidGoodsSpecs;
        }
        else{
            //返回空值
            return new LinkedList<>();
        }
    }

    /**
     *
     * @param pagerParamApi 分页查询参数
     * @param userId 设备ID
     * @return
     */
    @Override
    public PagerResult<Map> deviceHistoryList(PagerParamApi pagerParamApi, String userId) {
        PagerResult<Map> pagerResult = new PagerResult<>();
        int total = getMapper().totalDeviceUseHistoryList(userId);
        pagerResult.setTotal(total);
        if (total == 0) {
            pagerResult.setData(new ArrayList<>());
        } else {
            List<Map> mapLists = getMapper().selectDeviceUseHistoryList(pagerParamApi);

            //根据实际记录数量重新指定分页参数
            pagerResult.setData(mapLists);
        }
        return pagerResult;
    }

    @Override
    public PagerResult<DeviceUseInfs> selectByDeviceIdPager(QueryParam param, String deviceId) {
        param = preHandleParam(param, deviceId);
        if(param != null){
            PagerResult<DeviceUseInfs> shopDevicesPage = super.selectPager(param);
            List<DeviceUseInfs> deviceFidGoodsSpecs = shopDevicesPage.getData();
            shopDevicesPage.setData(deviceFidGoodsSpecs);
            return shopDevicesPage;
        }
        else{
            //返回空值
            return new PagerResult<>();
        }
    }

}
