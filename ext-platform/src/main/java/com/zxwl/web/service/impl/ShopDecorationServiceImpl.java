package com.zxwl.web.service.impl;

import com.zxwl.web.bean.ShopDecoration;
import com.zxwl.web.dao.ShopDecorationMapper;
import com.zxwl.web.service.ShopDecorationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 店铺装修表 服务类实现
 * Created by generator
 */
@Service("shopDecorationService")
public class ShopDecorationServiceImpl extends AbstractServiceImpl<ShopDecoration, String> implements ShopDecorationService {

    @Resource
    protected ShopDecorationMapper shopDecorationMapper;

    @Override
    protected ShopDecorationMapper getMapper() {
        return this.shopDecorationMapper;
    }

    @Override
    public ShopDecoration selectByShopID(String id) {
        return getMapper().selectByShopID(id);
    }

    @Override
    public Map selectByShopId(String shopId) {
        return getMapper().selectByShopId(shopId);
    }

    @Override
    public int deleteByShopID(String id) {
        return getMapper().deleteByShopID(id);
    }

    @Override
    public ShopDecoration shopView(String id) {
        return getMapper().shopView(id);
    }

    @Override
    public List<ShopDecoration> selectByImg1(String img1) {
        return getMapper().selectByImg1(img1);
    }

    @Override
    public List<ShopDecoration> selectByImg2(String img2) {
        return getMapper().selectByImg1(img2);
    }

    @Override
    public List<ShopDecoration> selectByImg3(String img3) {
        return getMapper().selectByImg1(img3);
    }

}
