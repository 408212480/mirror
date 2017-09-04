package com.zxwl.web.dao;

import com.zxwl.web.bean.GoodsDividend;
import com.zxwl.web.bean.GoodsShop;
import com.zxwl.web.bean.UserDividend;
import com.zxwl.web.bean.common.QueryParam;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/1.
 */
public interface GoodsDividendMapper {

    //获取参与分佣服装信息，包括服装编码，商品名，分佣比例，所属店铺名称
    public List<GoodsDividend> selectGoodsDividend(QueryParam param);
    //获取参与分佣服装信息，包括服装编码，商品名，分佣比例，所属店铺名称，结果分页
    public List<GoodsDividend> selectGoodsDividendPage(QueryParam param);
    //获取参与分佣服装其他信息，包括服分佣总金额，购买次数，分佣人数
    public List<GoodsDividend> selectGoodsOrderInfo(QueryParam param);

    //获取参与分佣用户信息,包括用户帐号,昵称,被购买次数,获得分佣金额
    public List<UserDividend> selectUserDividend(QueryParam param);

    //获取参与分佣用户信息,包括用户帐号,昵称,被购买次数,获得分佣金额,结果分页
    public List<UserDividend> selectUserDividendPage(QueryParam param);

    //获取商品信息表，包括服装编码id，服装名称，所属店铺名称
    public List<GoodsShop> selectGoodsShop();

}
