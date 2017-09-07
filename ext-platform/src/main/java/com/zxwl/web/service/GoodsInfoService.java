package com.zxwl.web.service;

import com.zxwl.web.bean.GoodsInfo;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品信息 服务类接口
 * Created by generator
 */
public interface GoodsInfoService extends GenericService<GoodsInfo, String> {

    PagerResult<GoodsInfo> selectList(QueryParam param);

    GoodsInfo selectSingleInfo(@Param("uid") String uid);

    int delete(GoodsInfo object);
}
