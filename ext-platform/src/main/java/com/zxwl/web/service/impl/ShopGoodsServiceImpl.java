package com.zxwl.web.service.impl;

import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.ShopGoods;
import com.zxwl.web.dao.ShopGoodsMapper;
import com.zxwl.web.service.impl.AbstractServiceImpl;
import com.zxwl.web.service.ShopGoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 店铺-商店关系表 服务类实现
 * Created by generator
 */
@Service("shopGoodsService")
public class ShopGoodsServiceImpl extends AbstractServiceImpl<ShopGoods, String> implements ShopGoodsService {

    @Resource
    protected ShopGoodsMapper shopGoodsMapper;

    @Override
    protected ShopGoodsMapper getMapper() {
        return this.shopGoodsMapper;
    }
}
