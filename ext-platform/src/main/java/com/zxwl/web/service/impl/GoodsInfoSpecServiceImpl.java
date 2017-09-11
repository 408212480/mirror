package com.zxwl.web.service.impl;

import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.GoodsInfoSpec;
import com.zxwl.web.dao.GoodsInfoSpecMapper;
import com.zxwl.web.service.impl.AbstractServiceImpl;
import com.zxwl.web.service.GoodsInfoSpecService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 服装分配 服务类实现
 * Created by generator
 */
@Service("goodsInfoSpecService")
public class GoodsInfoSpecServiceImpl extends AbstractServiceImpl<GoodsInfoSpec, String> implements GoodsInfoSpecService {

    @Resource
    protected GoodsInfoSpecMapper goodsInfoSpecMapper;

    @Override
    protected GoodsInfoSpecMapper getMapper() {
        return this.goodsInfoSpecMapper;
    }


    @Override
    public List<GoodsInfoSpec> getShopGoods(QueryParam param) {
        String userId=param.getParam().get("userId").toString();
        return getMapper().getShopGoods(param.getPageIndex(),param.getPageSize(),userId);
    }

    /**
     * 通过商品ID查询商品尺寸信息
     * @param goodsId
     * @return
     */
    @Override
    public List<Map> selectByGoodsId(String goodsId) {
        return getMapper().selectByGoodsId(goodsId);
    }

    /**
     * 查询所有商品中有的颜色类型
     * @return
     */
    @Override
    public List<String> selectColorAll() {
        return getMapper().selectColorAll();
    }

    /**
     * 查询所有商品中有的商品规格（尺码）
     * @return
     */
    @Override
    public List<String> selectSizeAll() {
        return getMapper().selectSizeAll();
    }

    /**
     * statistics all goods quality
     * @param goodsId
     * @return
     */
    @Override
    public Integer statisticsAllGoodsQuality(String goodsId) {
      Integer statisticsAllGoodsQuality = getMapper().statisticsAllGoodsQuality(goodsId);
      if (statisticsAllGoodsQuality==null){
          return 0;
      }
      else
          return statisticsAllGoodsQuality;

    }

    @Override
    public List<Map> goodsColorList(String goodsId) {
        return getMapper().goodsColorList(goodsId);
    }

    @Override
    public List<Map> goodsSizeList(String goodsId) {
        return getMapper().goodsSizeList(goodsId);
    }

    @Override
    public GoodsInfoSpec selectOne(GoodsInfoSpec goodsInfoSpec) {
        return goodsInfoSpecMapper.selectOne(goodsInfoSpec);
    }

    @Override
    public PagerResult<GoodsInfoSpec> selectList(QueryParam param) {
        PagerResult<GoodsInfoSpec> pagerResult = new PagerResult<>();
        param.setPaging(false);
        int total = this.createQuery().where(GoodsInfoSpec.Property.goodsId,param.getParam().get("goods_id")).list().size();
        pagerResult.setTotal(total);
        if (total == 0) {
            pagerResult.setData(new ArrayList<>());
        } else {
            //根据实际记录数量重新指定分页参数
            param.rePaging(total);
            pagerResult.setData(this.createQuery().setParam(param).where(GoodsInfoSpec.Property.goodsId,param.getParam().get("goods_id")).list());
        }
        return pagerResult;
    }
}
