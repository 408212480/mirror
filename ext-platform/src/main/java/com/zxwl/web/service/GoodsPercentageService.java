package com.zxwl.web.service;

import com.zxwl.web.bean.GoodsDividend;
import com.zxwl.web.bean.GoodsPercentage;
import com.zxwl.web.bean.GoodsShop;
import com.zxwl.web.bean.UserDividend;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品百分比 服务类接口
 * Created by generator
 */
public interface GoodsPercentageService extends GenericService<GoodsPercentage, String> {
    /**
     * 通过视频ID获取当前视频的晒衣获利金额
     * @param videoId
     * @return
     */
    public BigDecimal getGoodsPricePercentage(String videoId);

    public List<GoodsDividend> selectGoodsDividend(QueryParam param);

    public PagerResult<GoodsDividend> selectGoodsDividendPage(QueryParam param);

    public List<UserDividend> selectUserDividend(QueryParam param);

    public PagerResult<UserDividend> selectUserDividendPage(QueryParam param);

    public List<GoodsShop> selectGoodsShop();

}
