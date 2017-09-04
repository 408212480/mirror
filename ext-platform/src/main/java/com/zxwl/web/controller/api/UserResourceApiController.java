package com.zxwl.web.controller.api;

import com.zxwl.pay.common.util.str.StringUtils;
import com.zxwl.web.bean.VideoGoodsFit;
import com.zxwl.web.bean.VideoUpvoteRecord;
import com.zxwl.web.bean.api.*;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.po.GenericPo;
import com.zxwl.web.bean.video;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.po.resource.Resources;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.core.utils.WebUtil;
import com.zxwl.web.service.GoodsPercentageService;
import com.zxwl.web.service.VideoGoodsFitService;
import com.zxwl.web.service.VideoUpvoteRecordService;
import com.zxwl.web.service.VideoUserService;
import com.zxwl.web.service.resource.ResourcesService;
import com.zxwl.web.util.ResourceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zxwl.web.core.message.ResponseMessage.created;
import static com.zxwl.web.core.message.ResponseMessage.ok;

/**
 * Created by xianghugui on 23/07/2017.
 */
@RestController
@RequestMapping(value = "/api/video")
@Authorize
public class UserResourceApiController extends GenericController<Resources, String> {

    //默认服务类
    @Resource
    private ResourcesService resourcesService;
    @Resource
    private VideoUpvoteRecordService videoUpvoteRecordService;
    @Resource
    private VideoUserService videoUserService;
    @Resource
    private VideoGoodsFitService videoGoodsFitService;
    @Resource
    private GoodsPercentageService goodsPercentageService;
    @Resource
    private com.zxwl.web.service.videoService videoService;

    @Override
    public ResourcesService getService() {
        return this.resourcesService;
    }

    @RequestMapping(value = "/info/{videoId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @AccessLogger("获取视频相关信息")
    public ResponseMessage videoList(@PathVariable("videoId") String videoId, HttpServletRequest req) {
        VideoDetailPage videoDetailPage = new VideoDetailPage();

        Map videoDetail = resourcesService.videoDetail(videoId, WebUtil.getLoginUser().getId());
        if (videoDetail != null && videoDetail.get("userimgurl") != null) {
            videoDetail.put("userimgurl", resourcesService.selectSingleImage(WebUtil.getBasePath(req), String.valueOf(videoDetail.get("userimgurl")).trim()));
        }
        videoDetailPage.setVideoDetail(videoDetail);
        List<Map> videoImgUrl = resourcesService.videoImgUrl(videoId);
        if (videoImgUrl != null) {
            for (Map map : videoImgUrl) {
                map.put("md5", ResourceUtil.resourceBuildPath(req, String.valueOf(map.get("md5")).trim()));
            }
        }
        videoDetailPage.setVideoImgUrl(videoImgUrl);
        videoDetailPage.setMoney(goodsPercentageService.getGoodsPricePercentage(videoId));
        return ok(videoDetailPage);
    }

    /**
     * @return
     */
    @RequestMapping(value = "/uservideolist/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @AccessLogger("获取视频列表")
    public ResponseMessage VideoList(@PathVariable("userId") String userId, QueryParam param) {

        PagerParamApi pagerParam = new PagerParamApi();
        pagerParam.setPageIndex(param.getPageIndex());
        pagerParam.setPageSize(param.getPageSize());
        pagerParam.setUserId(userId);
        Object data = resourcesService.selectVideoListByUserId(pagerParam, userId);
        return ok(data);
    }

    @RequestMapping(value = "/updateVideoStatus/{id}/{status}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @AccessLogger("修改视频状态， 2，不公开，3，公开")
    public ResponseMessage updateVideoStatus(@PathVariable("id") String id, @PathVariable("status") int status) {
        video old = videoService.selectByPk(id);
        assertFound(old, "data is not found!");
        int number = videoService.updateStatus(status, id);
        return ok(number);
    }


    @RequestMapping(value = "/pagerallVideoList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @AccessLogger("发现页面分页展示视频列表")
    public ResponseMessage allVideoList(QueryParam queryParam, HttpServletRequest req) {
        PagerParamApi pagerParamApi =new PagerParamApi();
        pagerParamApi.setUserId(WebUtil.getLoginUser().getId());
        pagerParamApi.setPageSize(queryParam.getPageSize());
        pagerParamApi.setPageIndex(queryParam.getPageIndex());
        Object data = videoUserService.userVideoListInfo(pagerParamApi, req);
        return ok(data);
    }


    @RequestMapping(value = "/allvideoaboutgoodslist/{goodsId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @AccessLogger("展示该商品所有试衣视频")
    public ResponseMessage allVideoAboutGoodsList(@PathVariable("goodsId") String goodsId, QueryParam queryParam, HttpServletRequest req) {
        PagerParamApi pagerParamApi = new PagerParamApi();
        pagerParamApi.setPageIndex(queryParam.getPageIndex());
        pagerParamApi.setPageSize(queryParam.getPageSize());
        pagerParamApi.setUserId(goodsId);
        Object data = videoUserService.goodsVideoList(pagerParamApi, goodsId, req);
        return ok(data);
    }

    @RequestMapping(value = "/alluservideolist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @AccessLogger("用户(自己)所有试衣视频")
    public ResponseMessage allUserVideoList(QueryParam queryParam, HttpServletRequest req) {
        PagerParamApi pagerParamApi = new PagerParamApi();
        pagerParamApi.setPageIndex(queryParam.getPageIndex());
        pagerParamApi.setPageSize(queryParam.getPageSize());
        pagerParamApi.setUserId(WebUtil.getLoginUser().getId());
        return ok(videoUserService.userVideoList(pagerParamApi, WebUtil.getLoginUser().getId(), req));
    }

    @RequestMapping(value = "/videolist/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @AccessLogger("他人(自己)公开的所有试衣视频")
    public ResponseMessage otherUserVideoList(@PathVariable("userId") String userId, QueryParam queryParam, HttpServletRequest req) {
        PagerParamApi pagerParamApi = new PagerParamApi();
        pagerParamApi.setPageIndex(queryParam.getPageIndex());
        pagerParamApi.setPageSize(queryParam.getPageSize());
        pagerParamApi.setUserId(userId);
        return ok(videoUserService.otherUserVideoList(pagerParamApi, userId, req));
    }

    //字符串逗号分割
    public static String[] convertStrToArray(String str) {
        String[] strArray = null;
        strArray = str.split(","); //拆分字符为"," ,然后把结果交给数组strArray
        return strArray;
    }

    @RequestMapping(value = "/searchVideoByUserCharacteristics", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @AccessLogger("根据用户特征查询视频列表")
    public ResponseMessage searchVideoByUserCharacteristics(@RequestBody(required = false) SearchUserCharacteristicsJsonBean bean, HttpServletRequest req, QueryParam queryParam) {

        if (bean == null) {
            return ResponseMessage.error("params error");
        }

        PagerParamVideoByUserCharacteristics pagerParamVideoByUserCharacteristics = new PagerParamVideoByUserCharacteristics();
        String sexArray = bean.getSex();

        //年龄查询条件
        //当该参数不传值是，最小值设为-1
        int minAge = -1;
        int maxAge = -1;

        if (!StringUtils.isBlank(bean.getAge())) {
            String[] ageList = convertStrToArray(bean.getAge());
            if (ageList != null && ageList.length > 0) {
                if (Integer.parseInt(ageList[1]) == 0) {
                    minAge = 0;
                    maxAge = Integer.parseInt(ageList[0]);
                } else if (Integer.parseInt(ageList[1]) == -1) {
                    minAge = Integer.parseInt(ageList[0]);
                    maxAge = 200;
                } else {
                    minAge = Integer.parseInt(ageList[0]);
                    maxAge = Integer.parseInt(ageList[1]);
                }
            }
        }
        //身高查询条件
        //当该参数不传值是，最小值设为-1
        int minHeight = -1;
        int maxHeight = -1;

        if (!StringUtils.isEmpty(bean.getHeight())) {
            String[] heightList = convertStrToArray(bean.getHeight());
            if (heightList != null && heightList.length > 0) {
                if (Integer.parseInt(heightList[1]) == 0) {
                    minHeight = 0;
                    maxHeight = Integer.parseInt(heightList[0]);
                } else if (Integer.parseInt(heightList[1]) == -1) {
                    minHeight = Integer.parseInt(heightList[0]);
                    maxHeight = 300;
                } else {
                    minHeight = Integer.parseInt(heightList[0]);
                    maxHeight = Integer.parseInt(heightList[1]);
                }
            }
        }
        //体重查询条件
        //当该参数不传值是，最小值设为-1
        int minWeight = -1;
        int maxWeight = -1;

        if (!StringUtils.isEmpty(bean.getWeight())) {
            String[] weightList = convertStrToArray(bean.getWeight());
            if (weightList != null && weightList.length > 0) {
                if (Integer.parseInt(weightList[1]) == 0) {
                    minWeight = 0;
                    maxWeight = Integer.parseInt(weightList[0]);
                } else if (Integer.parseInt(weightList[1]) == -1) {
                    minWeight = Integer.parseInt(weightList[0]);
                    maxWeight = 400;
                } else {
                    minWeight = Integer.parseInt(weightList[0]);
                    maxWeight = Integer.parseInt(weightList[1]);
                }
            }
        }
        //性别查询条件
        //当该参数不传值是，最小值设为-1
        int sex = -1;

        if (!StringUtils.isEmpty(sexArray)) {
            String userSex = sexArray;
            if (userSex.equals("男")) {
                sex = 0;
            } else if (userSex.equals("女")) {
                sex = 1;
            }
        }

        pagerParamVideoByUserCharacteristics.setMinAge(minAge);
        pagerParamVideoByUserCharacteristics.setMaxAge(maxAge);
        pagerParamVideoByUserCharacteristics.setMinHeight(minHeight);
        pagerParamVideoByUserCharacteristics.setMaxHeight(maxHeight);
        pagerParamVideoByUserCharacteristics.setMinWeight(minWeight);
        pagerParamVideoByUserCharacteristics.setMaxWeight(maxWeight);
        pagerParamVideoByUserCharacteristics.setSex(sex);
        pagerParamVideoByUserCharacteristics.setUserId(WebUtil.getLoginUser().getId());
        pagerParamVideoByUserCharacteristics.setPageIndex(queryParam.getPageIndex());
        pagerParamVideoByUserCharacteristics.setPageSize(queryParam.getPageSize());

        return ok(videoUserService.userCharacteristicsSearchVideo(pagerParamVideoByUserCharacteristics, req));
    }

    @RequestMapping(value = "/searchVideoByGoodsSpc", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @AccessLogger("根据商品规格查询视频列表")
    public ResponseMessage searchVideoByGoodsSpc(@RequestBody(required = false) SearchGoodsSpcJsonBean bean, HttpServletRequest req, QueryParam queryParam) {
        if (bean == null) {
            return ResponseMessage.error("请求参数不正确");
        }

        PagerParamVideoByGoodsSpc pagerParamVideoByGoodsSpc = new PagerParamVideoByGoodsSpc();

        pagerParamVideoByGoodsSpc.setClass_code(!StringUtils.isEmpty(bean.getStyle()) ? bean.getStyle() : "");
        pagerParamVideoByGoodsSpc.setColor(!StringUtils.isEmpty(bean.getColor()) ? bean.getColor() : "");
        pagerParamVideoByGoodsSpc.setSize(!StringUtils.isEmpty(bean.getSize()) ? bean.getSize() : "");
        pagerParamVideoByGoodsSpc.setUserId(WebUtil.getLoginUser().getId());
        pagerParamVideoByGoodsSpc.setPageIndex(queryParam.getPageIndex());
        pagerParamVideoByGoodsSpc.setPageSize(queryParam.getPageSize());

        return ok(videoUserService.goodsSpcSearchVideo(pagerParamVideoByGoodsSpc, req));
    }

    @RequestMapping(value = "/feedback/{videoGoodsID}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @AccessLogger("错误反馈")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseMessage feedBack(@PathVariable("videoGoodsID") String videoGoodsID) {
        VideoGoodsFit videoGoods = new VideoGoodsFit();
        videoGoods.setId(GenericPo.createUID());
        videoGoods.setReportUserId(WebUtil.getLoginUser().getId());
        videoGoods.setReportTime(new Date());
        videoGoods.setLastChangeUser(WebUtil.getLoginUser().getName());
        videoGoods.setIsFit(1);
        videoGoods.setVideoGoodId(videoGoodsID);
        String pk = videoGoodsFitService.insert(videoGoods);
        return created(pk);
    }

    @Transactional
    @RequestMapping(value = "/updateVideoUpvoteNum/{videoId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @AccessLogger("视频点赞或取消点赞")
    public ResponseMessage updateVideoUpvoteNum(@PathVariable("videoId") String videoId) {
        video old = videoService.getVideoUpvoteByVideoId(videoId);

        int videoUpvoteNum = old.getLikeNum();
        int likeNum = 0;
        VideoUpvoteRecord videoUpvoteRecord = videoUpvoteRecordService.getVideoUpvoteByVideoId(videoId, WebUtil.getLoginUser().getId());
        if (videoUpvoteRecord == null) {
            //添加一条点赞记录
            VideoUpvoteRecord newVideoUpvoteRecord = new VideoUpvoteRecord();
            newVideoUpvoteRecord.setId(GenericPo.createUID());
            newVideoUpvoteRecord.setUserId(WebUtil.getLoginUser().getId());
            newVideoUpvoteRecord.setVideoId(videoId);
            newVideoUpvoteRecord.setCreateTime(new Date());
            videoUpvoteRecordService.saveOrUpdate(newVideoUpvoteRecord);
            //点赞数+1
            likeNum = videoUpvoteNum + 1;
            videoService.updateVideoUpvoteNum(likeNum, videoId);
        } else {
            //删除该用户该条视频的点赞记录
            videoUpvoteRecordService.deleteUpvoteByVideoUserId(videoId, WebUtil.getLoginUser().getId());
            //点赞数—1
            likeNum = videoUpvoteNum - 1;
            videoService.updateVideoUpvoteNum(likeNum, videoId);
        }
        return ok(1);
    }


    /**
     * @author wuei
     * @date 2017.8.28 14:54
     * 根据时间查询视频信息
     *
     */
    @RequestMapping(value = "/getVideoByDate/{date}", method = RequestMethod.GET)
    @AccessLogger("查询对应时间内的视频信息")
    @Authorize(action = "R")
    public ResponseMessage getVideoByDate(@PathVariable("date") String date, QueryParam param, HttpServletRequest req){
        PagerResult<HashMap<String, String>> result = null;
        try {
            result = videoService.getVideoByUploadTimePager(date, param, req);
        } catch (ParseException e) {
            return ResponseMessage.error("日期格式不正确", 500);
        }

        return ResponseMessage.ok(result);
    }
}