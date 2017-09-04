package com.zxwl.web.service;

import com.zxwl.web.bean.VideoUser;
import com.zxwl.web.bean.api.PagerParamApi;
import com.zxwl.web.bean.api.PagerParamVideoByGoodsSpc;
import com.zxwl.web.bean.api.PagerParamVideoByUserCharacteristics;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 设备管理 服务类接口
 * Created by generator
 */
public interface VideoUserService extends GenericService<VideoUser, String> {

    public int updateByVideoUserID(String userId, String videoId, int status);

    // 视频列表详情
    PagerResult<Map> userVideoListInfo(PagerParamApi pagerParamApi, HttpServletRequest req);

    //消费明细
    PagerResult<Map> goodsVideoList(PagerParamApi pagerParamApi, String goodsId,HttpServletRequest req);

    // 分页查询用户视频列表
    PagerResult<Map> userVideoList(PagerParamApi pagerParamApi, String userId ,HttpServletRequest req);

    // 分页查询用户视频列表
    PagerResult<Map> userCharacteristicsSearchVideo(PagerParamVideoByUserCharacteristics pagerParamVideoByUserCharacteristics,HttpServletRequest req);

    // 分页查询用户视频列表
    PagerResult<Map> goodsSpcSearchVideo(PagerParamVideoByGoodsSpc pagerParamVideoByGoodsSpc ,HttpServletRequest req);

    // 分页查询用户视频列表
    PagerResult<Map> otherUserVideoList(PagerParamApi pagerParamApi, String userId ,HttpServletRequest req);

    //插入数据
    int insertAll(String[] ids);

}
