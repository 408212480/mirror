package com.zxwl.web.service;

import com.zxwl.web.bean.FidGoodsSpec;
import com.zxwl.web.service.GenericService;

/**
 * 店铺信息 服务类接口
 * Created by generator
 */
public interface FidGoodsSpecService extends GenericService<FidGoodsSpec, String> {
    void delFidSpec(String id);
    String getFidId(String id);
}
