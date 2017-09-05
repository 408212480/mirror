package com.zxwl.web.service;

import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.video;
import com.zxwl.web.service.GenericService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 视频存储 服务类接口
 * Created by generator
 */
public interface videoService extends GenericService<video, String> {
    List<video> List();

    List<video> List(QueryParam param);

    video getVideoByVideoId(String videoId);

    List<Map> selectVideoImgByVideoId(String videoId);

    int updateStatus(int status, String videoId);

    int updateVideoUpvoteNum(  int likeNum,String videoId);

    video getVideoUpvoteByVideoId(String videoId);
    //用户所有视频点赞总数统计
    int getAllLike(String userId);

    /**
     * author: wuei
     * data: 2017.8.22 15:46
     * 查询与面部识别结果匹配的视频列表
     * @param param 查询参数
     * @return 查询列表结果 ，分页
     */
    public PagerResult<HashMap<String, String>> getRecognitionVideoPager(QueryParam param, HttpServletRequest req);

    /**
     * author: wuei
     * data: 2017.8.29 10:14
     * 根据时间和用户id查询当月的用户视频
     * @param date 视频上传时间
     * @return 查询列表结果
     */
    public PagerResult<HashMap<String, String>> getVideoByUploadTimePager(String date, QueryParam param, HttpServletRequest req) throws ParseException;
}
