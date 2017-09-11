package com.zxwl.web.dao;

import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.dao.GenericMapper;
import com.zxwl.web.bean.GoodsInfoSpec;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * MyBatis 服装分配 数据映射接口
 * Created by generator
 */
public interface GoodsInfoSpecMapper extends GenericMapper<GoodsInfoSpec, String> {
    List<GoodsInfoSpec> getShopGoods(Integer pageIndex, Integer pageSize, String userId);

    List<Map> selectByGoodsId(@Param("id") String goodsId);

    List<String> selectColorAll();

    List<String> selectSizeAll();

    /**
     * statistics  goods total quality
     *
     * @param goodsId
     * @return
     */
    Integer statisticsAllGoodsQuality(String goodsId);

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

    List<GoodsInfoSpec> selectList(QueryParam param);

}
