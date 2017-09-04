package com.zxwl.web.service;

import com.zxwl.web.bean.GoodsInfoSpec;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.service.GenericService;

import java.util.List;
import java.util.Map;

/**
 * 服装分配 服务类接口
 * Created by generator
 */
public interface GoodsInfoSpecService extends GenericService<GoodsInfoSpec, String> {
List<GoodsInfoSpec> getShopGoods(QueryParam param);

    List<Map> selectByGoodsId(String goodsId);

    List<String> selectColorAll();

    List<String> selectSizeAll();
    /**
     *  statistics  goods total quality
     * @param goodsId
     * @return
     */
    int statisticsAllGoodsQuality(String goodsId);

    /**
     * goodsSpc color list
     * @param goodsId
     * @return
     */
    List<Map> goodsColorList(String goodsId);

    /**
     * goodsSpc size list
     * @param goodsId
     * @return
     */
    List<Map> goodsSizeList(String goodsId);
    GoodsInfoSpec selectOne(GoodsInfoSpec goodsInfoSpec);
}
