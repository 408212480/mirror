package com.zxwl.web.service.impl;

import com.zxwl.web.bean.GoodsInfo;
import com.zxwl.web.bean.MetaDataRel;
import com.zxwl.web.bean.ShopGoods;
import com.zxwl.web.bean.common.InsertParam;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.po.GenericPo;
import com.zxwl.web.core.utils.WebUtil;
import com.zxwl.web.dao.GoodsInfoMapper;
import com.zxwl.web.dao.MetaDataRelMapper;
import com.zxwl.web.dao.ShopGoodsMapper;
import com.zxwl.web.service.GoodsInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品信息 服务类实现
 * Created by generator
 */
@Service("goodsInfoService")
public class GoodsInfoServiceImpl extends AbstractServiceImpl<GoodsInfo, String> implements GoodsInfoService {

    @Resource
    protected GoodsInfoMapper goodsInfoMapper;

    @Resource
    protected ShopGoodsMapper shopGoodsMapper;

    @Resource
    protected MetaDataRelMapper metaDataRelMapper;

    @Override
    protected GoodsInfoMapper getMapper() {
        return this.goodsInfoMapper;
    }

    @Override
    public List<GoodsInfo> select(QueryParam param) {
        return null;
    }

    /**
     * 插入商品信息，同时往 店铺商品关联表 插入关联关系
     * @param data 要添加的数据
     * @return
     */
    @Override
    @Transactional
    public String insert(GoodsInfo data) {
        data.setCreatorId(WebUtil.getLoginUser().getId());
        if (data.getId() == null) {
            data.setId(GenericPo.createUID());
        }
        if (data.getImgId() == null) {
            data.setImgId(GenericPo.createUID());
        }
        if (data.getCarouselImgUrl() == null) {
            data.setCarouselImgUrl(GenericPo.createUID());
        }
        tryValidPo(data);
        getInsertMapper().insert(InsertParam.build(data));

        // 插入 t_shop_goods 中间表
        if (data.getShopId() != null && !"".equals(data.getShopId())) {
            String[] shopId = data.getShopId().split(",");
            for (int i = 0; i < shopId.length; i++) {
                ShopGoods shopGoods = new ShopGoods();
                shopGoods.setId(GenericPo.createUID());
                shopGoods.setGoodsId(data.getId());
                shopGoods.setShopId(shopId[i]);

                shopGoodsMapper.insert(InsertParam.build(shopGoods));
            }
        }
        // 插入 t_metadata_rel 中间表（商品详情图）
        if (data.getImgIds() != null && !"".equals(data.getImgIds())) {
            String[] imgIds = data.getImgIds().split(",");
            for (int i = 0; i < imgIds.length; i++) {

                if (imgIds[i] != null && !"".equals(imgIds[i])) {
                    MetaDataRel metaDataRel = new MetaDataRel();
                    metaDataRel.setId(GenericPo.createUID());
                    metaDataRel.setDataId(imgIds[i].trim());
                    metaDataRel.setRecordId(data.getImgId());
                    metaDataRel.setDataType(2);
                    metaDataRel.setType(0);
                    metaDataRelMapper.insert(InsertParam.build(metaDataRel));
                }
            }
        }
        // 插入 t_metadata_rel 中间表（轮播图）
        if (data.getCarouselImgUrls() != null && !"".equals(data.getCarouselImgUrls())) {
            String[] carouselImgUrls = data.getCarouselImgUrls().split(",");
            for (int i = 0; i < carouselImgUrls.length; i++) {
                if (carouselImgUrls[i] != null && !"".equals(carouselImgUrls[i])) {
                    MetaDataRel metaDataRel = new MetaDataRel();
                    metaDataRel.setId(GenericPo.createUID());
                    metaDataRel.setDataId(carouselImgUrls[i].trim());
                    metaDataRel.setRecordId(data.getCarouselImgUrl());
                    metaDataRel.setDataType(2);
                    metaDataRel.setType(0);
                    metaDataRelMapper.insert(InsertParam.build(metaDataRel));
                }
            }
        }

        return data.getId();
    }

    @Override
    public int update(GoodsInfo data) {
        return super.update(data);
    }

    @Override
    public int update(List<GoodsInfo> data) {
        return super.update(data);
    }

    /**
     * 查询列表
     * @param param
     * @return
     */
    @Override
    public PagerResult<GoodsInfo> selectList(QueryParam param) {

        PagerResult<GoodsInfo> pagerResult = new PagerResult<>();
        param.setPaging(false);
        int total = getQueryMapper().total(param);
        pagerResult.setTotal(total);
        if (total == 0) {
            pagerResult.setData(new ArrayList<>());
        } else {
            //根据实际记录数量重新指定分页参数
            param.rePaging(total);
            pagerResult.setData(goodsInfoMapper.selectList(param));
        }

        return pagerResult;
    }

    /**
     * 查询单条信息
     * @param uid
     * @return
     */
    @Override
    public GoodsInfo selectSingleInfo(String uid) {
        return goodsInfoMapper.selectSingleInfo(uid);
    }
}
