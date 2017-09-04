package com.zxwl.web.controller.api;

import com.alibaba.druid.support.json.JSONUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zxwl.web.bean.GoodsClass;
import com.zxwl.web.bean.GoodsInfoSpec;
import com.zxwl.web.bean.po.config.Config;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.service.GoodsClassService;
import com.zxwl.web.service.GoodsInfoSpecService;
import com.zxwl.web.service.config.ConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;


/**
 * Project: zxwl-framework
 * Author: Sendya <18x@loacg.com>
 * Date: 2017/8/15 15:04
 */
@RequestMapping("/api/base")
@RestController
public class BaseDataApiController {

    private static final String SHARE_INNER_TEXT_KEY = "api.base.shareInnerText";

    private static final String SEARCH_CONDITION = "api.base.searchCondition";

    @Resource
    private ConfigService configService;

    @Resource
    private GoodsClassService goodsClassService;

    @Resource
    private GoodsInfoSpecService goodsInfoSpecService;

    @RequestMapping(value = "/shareInnerText", method = RequestMethod.GET)
    public ResponseMessage shareGoodsVideoInnerText() {
        Config config = configService.selectByPk(SHARE_INNER_TEXT_KEY);
        // 如果没有此项配置，则向数据库加入默认配置
        if (config == null || config.getContent() == null || "".equals(config.getContent())) {
            Config tmp = new Config();
            tmp.setId(SHARE_INNER_TEXT_KEY);
            List<String> data = new ArrayList<>();
            data.add("公开后，有机会得到别人的打赏");
            data.add("公开后，若用户通过你的试衣效果进行购买该衣服，您将获得衣服总价5%的分佣，并且有机会享受更低折扣");
            data.add("公开后，您将有机会获得更多好友关注");
            tmp.setContent(JSONUtils.toJSONString(data));
            configService.insert(tmp);

            return ResponseMessage.ok(data);
        }

        List<String> data = (List) JSONUtils.parse(config.getContent());

        return ResponseMessage.ok(data);
    }


    @RequestMapping(value = "/searchCondition", method = RequestMethod.GET)
    public ResponseMessage searchCondition() {
        Config config = configService.selectByPk(SEARCH_CONDITION);

        Map goodsSearchInfo = Maps.newHashMap();
        // 服装款式
        List<Object> styleList = Lists.newArrayList();
        final List<GoodsClass> goodsClassList = goodsClassService.select();
        if (goodsClassList != null && goodsClassList.size() > 0) {
            goodsClassList.forEach(goodsClass -> styleList.add(ImmutableMap.of("text", goodsClass.getClassName(), "value", goodsClass.getClassCode())));
        }
        // 服装款式
        goodsSearchInfo.put("style", styleList);
        // 服装颜色
        goodsSearchInfo.put("color", goodsInfoSpecService.selectColorAll());
        // 服装尺码
        goodsSearchInfo.put("size", goodsInfoSpecService.selectSizeAll());

        if (config == null || config.getContent() == null || "".equals(config.getContent())) {
            Config tmp = new Config();
            tmp.setId(SEARCH_CONDITION);
            // end 为正整数 0 时
            // 代表 begin 数以下条件
            // end 为负数 -1 时
            // 代表 begin 数以上条件

            Map<String, Object> c = Maps.newHashMap();
            // 身高
            c.put("height", ImmutableList.of(
                    ImmutableMap.of("text", "140cm~150cm", "value", "140,150"),
                    ImmutableMap.of("text", "150cm~160cm", "value", "150,160"),
                    ImmutableMap.of("text", "160cm~170cm", "value", "160,170"),
                    ImmutableMap.of("text", "170cm以上", "value", "170,-1")
            ));
            c.put("height_unit", "cm");
            // 体重
            c.put("weight", ImmutableList.of(
                    ImmutableMap.of("text", "40kg以下", "value", "40,0"),
                    ImmutableMap.of("text", "40kg~50kg", "value", "40,50"),
                    ImmutableMap.of("text", "50kg~60kg", "value", "50,60"),
                    ImmutableMap.of("text", "60kg~70kg", "value", "60,70"),
                    ImmutableMap.of("text", "70kg以上", "value", "70,-1")
            ));
            c.put("weight_unit", "kg");
            // 性别
            c.put("sex", ImmutableList.of("男", "女"));
            // 年龄
            c.put("age", ImmutableList.of(
                    ImmutableMap.of("text", "18岁以下", "value", "18,0"),
                    ImmutableMap.of("text", "18岁~30岁", "value", "18,30"),
                    ImmutableMap.of("text", "30岁~40岁", "value", "30,40"),
                    ImmutableMap.of("text", "40岁~50岁", "value", "40,50"),
                    ImmutableMap.of("text", "50岁~60岁", "value", "50,60"),
                    ImmutableMap.of("text", "60岁以上", "value", "60,-1")
            ));
            c.put("age_unit", "岁");
            // 体型
            c.put("type", ImmutableList.of(
                    ImmutableMap.of("text", "标准", "value", "t1"),
                    ImmutableMap.of("text", "梨型", "value", "t2"),
                    ImmutableMap.of("text", "苹果型", "value", "t3")
            ));
            tmp.setContent(JSONUtils.toJSONString(c));
            configService.insert(tmp);
            c.putAll(goodsSearchInfo);
            return ResponseMessage.ok(c);
        }

        Map data = (Map) JSONUtils.parse(config.getContent());
        if (data != null) {
            data.putAll(goodsSearchInfo);
        }
        return ResponseMessage.ok(data);
    }
}