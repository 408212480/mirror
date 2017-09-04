package com.zxwl.web.dao;

import com.zxwl.web.bean.VideoUser;
import com.zxwl.web.bean.api.PagerParamApi;
import com.zxwl.web.bean.api.PagerParamVideoByGoodsSpc;
import com.zxwl.web.bean.api.PagerParamVideoByUserCharacteristics;
import com.zxwl.web.bean.common.QueryParam;
import org.apache.catalina.LifecycleState;

import java.util.List;
import java.util.Map;

/**
* MyBatis 设备管理 数据映射接口
* Created by generator 
*/
public interface VideoUserMapper extends GenericMapper<VideoUser,String> {

    public int updateByVideoUserID(String id, String  userId, int status);
    //发现页面用户视频信息
    List<Map> userVideoListInfo(PagerParamApi pagerParamApi);

    //当前商品的所有公开试衣视频
    List<Map> goodsVideoList(PagerParamApi pagerParamApi);

    //用户所有视频列表
    List<Map> userALLVideoList(PagerParamApi pagerParamApi);
    //当前用户视频列表总数
    int totalUserVideoList( String userId);
    //当前商品的所有公开试衣视频数量
    int  totalgoodsVideoList(String goodsId);

    int totalUserVideoListInfo();

    //根据用户特征查询视频列表
    List<Map> userCharacteristicsSearchVideo(PagerParamVideoByUserCharacteristics pagerParamVideoByUserCharacteristics);
    //根据用户特征查询视频列表匹配总数
    int totalUserCharacteristicsSearchVideo( PagerParamVideoByUserCharacteristics pagerParamVideoByUserCharacteristics);

    //根据服装规格查询视频列表
    List<Map> goodsSpcSearchVideo(PagerParamVideoByGoodsSpc pagerParamVideoByGoodsSpc);

    //根据服装规格查询视频列表匹配总数
    int totalGoodsSpcSearchVideo( PagerParamVideoByGoodsSpc pagerParamVideoByGoodsSpc);

    //用户所有视频列表
    List<Map> otherALLVideoList(PagerParamApi pagerParamApi);
    //当前用户视频列表总数
    int totalOtherVideoList( String userId);
}
