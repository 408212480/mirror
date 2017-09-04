package com.zxwl.web.service.impl;

import com.zxwl.pay.common.util.str.StringUtils;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.video;
import com.zxwl.web.core.utils.WebUtil;
import com.zxwl.web.dao.videoMapper;
import com.zxwl.web.service.impl.AbstractServiceImpl;
import com.zxwl.web.service.videoService;
import com.zxwl.web.util.ResourceUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 视频存储 服务类实现
 * Created by generator
 */
@Service("videoService")
public class videoServiceImpl extends AbstractServiceImpl<video, String> implements videoService {
    @Resource
    protected videoMapper videoMapper;

    @Override
    protected videoMapper getMapper() {
        return this.videoMapper;
    }

    @Override
    public List<video> List() {
        return getMapper().List();
    }

    @Override
    public List<video> List(QueryParam param) {
        return getMapper().ListPager(param);
    }

    @Override
    public List<video> getVideoByVideoId(String videoId) {
        return getMapper().getVideoByVideoId(videoId);
    }

    @Override
    public List<Map> selectVideoImgByVideoId(String videoId) {
        return getMapper().selectVideoImgByVideoId(videoId);
    }

    @Override
    public int updateStatus(int status, String videoId) {
        return getMapper().updateStatus(status, videoId);
    }

    @Override
    public int updateVideoUpvoteNum(int likeNum, String videoId) {
        return getMapper().updateVideoUpvoteNum(likeNum, videoId);
    }

    @Override
    public video getVideoUpvoteByVideoId(String videoId) {
        return getMapper().getVideoUpvoteByVideoId(videoId);
    }

    @Override
    public int getAllLike(String userId) {
        return getMapper().getAllLike(userId);
    }

    /**
     * author: wuei
     * data: 2017.8.22 15:46
     * 查询与面部识别结果匹配的视频列表
     *
     * @param param 查询参数
     * @return 查询列表结果 ，分页
     */
    @Override
    public PagerResult<HashMap<String, String>> getRecognitionVideoPager(QueryParam param, HttpServletRequest req) {
        PagerResult<HashMap<String, String>> result = new PagerResult<>();
        int total = this.getMapper().getRecognitionVideo().size();
        result.setTotal(total);
        if (param.getPageIndex() >= total) {
            int temp = total % param.getPageSize();
            param.setPageIndex(param.getPageIndex() - temp);
        }
        ArrayList<HashMap<String, String>> list = this.getMapper().getRecognitionVideoPager(param);
        resetUrl(list, req);
        result.setData(list);
        return result;
    }

    //重新设置视频和图片url
    private void resetUrl(ArrayList<HashMap<String, String>> list, HttpServletRequest req){
        for (HashMap<String, String> map : list) {
            if (StringUtils.isNotEmpty(String.valueOf(map.get("videoUrl")))) {
                map.put("videoUrl", ResourceUtil.resourceBuildPath(req, String.valueOf(map.get("videoUrl")), ".MP4"));
            }
            if (StringUtils.isNotEmpty(String.valueOf(map.get("videoImg")))) {
                map.put("videoImg", ResourceUtil.resourceBuildPath(req, String.valueOf(map.get("videoImg"))));
            }
        }
    }

    /**
     * author: wuei
     * data: 2017.8.29 10:14
     * 根据时间和用户id查询当月的用户视频
     *
     * @param date 视频上传时间
     * @return 查询列表结果
     */
    @Override
    public PagerResult<HashMap<String, String>> getVideoByUploadTimePager(String date, QueryParam param, HttpServletRequest req) throws ParseException {
        PagerResult<HashMap<String, String>> result = new PagerResult<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Date start = dateFormat.parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.add(calendar.MONTH, 1);
        Date end = calendar.getTime();
        int total = this.getMapper().getVideoByUploadTime(start, end, WebUtil.getLoginUser().getId()).size();
        result.setTotal(total);
        if (param.getPageIndex() >= total) {
            int temp = total % param.getPageSize();
            param.setPageIndex(param.getPageIndex() - temp);
        }
        ArrayList<HashMap<String, String>> list = this.getMapper().getVideoByUploadTimePager(start, end, WebUtil.getLoginUser().getId(), param.getPageIndex(), param.getPageSize());
        resetUrl(list, req);
        result.setData(list);

        return result;
    }
}

