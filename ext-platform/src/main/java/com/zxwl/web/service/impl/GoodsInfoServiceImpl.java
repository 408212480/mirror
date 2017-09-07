package com.zxwl.web.service.impl;

import com.zxwl.web.bean.GoodsInfo;
import com.zxwl.web.bean.MetaDataRel;
import com.zxwl.web.bean.ShopGoods;
import com.zxwl.web.bean.common.DeleteParam;
import com.zxwl.web.bean.common.InsertParam;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.po.GenericPo;
import com.zxwl.web.core.utils.WebUtil;
import com.zxwl.web.dao.GoodsInfoMapper;
import com.zxwl.web.dao.MetaDataRelMapper;
import com.zxwl.web.dao.ShopGoodsMapper;
import com.zxwl.web.service.GoodsInfoService;
import com.zxwl.web.service.ShopGoodsService;
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
    protected ShopGoodsService shopGoodsService;

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
            this.insertShopGoodsRel(data.getShopId(), data.getId());
        }
        // 插入 t_metadata_rel 中间表（商品详情图）
        if (data.getImgIds() != null && !"".equals(data.getImgIds())) {
            this.insertImg(data.getImgIds(), data.getImgId());
        }

        // 插入 t_metadata_rel 中间表（轮播图）
        if (data.getCarouselImgUrls() != null && !"".equals(data.getCarouselImgUrls())) {
            this.insertImg(data.getCarouselImgUrls(), data.getCreatorId());
        }

        return data.getId();
    }

    @Override
    @Transactional
    public int update(GoodsInfo data) {
        tryValidPo(data);

        data.setLastChangeUser(WebUtil.getLoginUser().getId());

        // 删除资源表关联资源
        if (data.getImgId() != null) {
            metaDataRelMapper.deleteByRecordId(data.getImgId());
        }

        // 删除 店铺商品关联
        shopGoodsService.createDelete().where(ShopGoods.Property.goodsId, data.getId()).exec();

        createUpdate().fromBean(data).where(GenericPo.Property.id).exec();


        // 插入 t_shop_goods 中间表
        if (data.getShopId() != null && !"".equals(data.getShopId())) {
            this.insertShopGoodsRel(data.getShopId(), data.getId());
        }

        // 插入 t_metadata_rel 中间表（商品详情图）
        if (data.getImgIds() != null && !"".equals(data.getImgIds())) {
            this.insertImg(data.getImgIds(), data.getImgId());
        }

        // 插入 t_metadata_rel 中间表（轮播图）
        if (data.getCarouselImgUrls() != null && !"".equals(data.getCarouselImgUrls())) {
            this.insertImg(data.getCarouselImgUrls(), data.getCreatorId());
        }

        return 1;
    }

    /**
     * 插入 店铺商品关联表
     * @param ids
     * @param goodsId
     * @return
     */
    private int insertShopGoodsRel(String ids, String goodsId) {
        int flag = 0;
        String[] shopIds = ids.split(",");
        for (String shopId : shopIds) {
            ShopGoods shopGoods = new ShopGoods();
            shopGoods.setId(GenericPo.createUID());
            shopGoods.setGoodsId(goodsId);
            shopGoods.setShopId(shopId);
            flag = shopGoodsMapper.insert(InsertParam.build(shopGoods));
        }
        return flag;
    }

    /**
     * 插入 媒体资源 关联表
     * @param imgs
     * @param recordId
     * @return
     */
    private int insertImg(String imgs, String recordId) {
        int flag = 0;
        String[] args = imgs.split(",");
        for (String str : args) {
            if (str != null && !"".equals(str)) {
                MetaDataRel metaDataRel = new MetaDataRel();
                metaDataRel.setId(GenericPo.createUID());
                metaDataRel.setDataId(str.trim());
                metaDataRel.setRecordId(recordId);
                metaDataRel.setDataType(2);
                metaDataRel.setType(0);
                flag = metaDataRelMapper.insert(InsertParam.build(metaDataRel));
            }
        }
        return flag;
    }

    @Override
    public int update(List<GoodsInfo> data) {
        return super.update(data);
    }

    public int delete(GoodsInfo object) {
        // 删除媒体资源关系
        metaDataRelMapper.deleteByRecordId(object.getImgId());
        // 删除 店铺商品关系
        shopGoodsService.createDelete().where(ShopGoods.Property.goodsId, object.getId());
        // 删除 自身数据
        int val = this.createDelete().where(GenericPo.Property.id, object.getId()).exec();
        return val;
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
