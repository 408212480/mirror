package com.zxwl.web.dao;

import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.dao.GenericMapper;
import com.zxwl.web.bean.video;
import org.apache.ibatis.annotations.Param;

import java.util.*;

/**
 * MyBatis 视频存储 数据映射接口
 * Created by generator
 */
public interface videoMapper extends GenericMapper<video, String> {
    List<video> List();

    List<video> ListPager(QueryParam param);

    List<video> getVideoByVideoId(String videoId);

    List<Map> selectVideoImgByVideoId(String videoId);

    int updateStatus(@Param("status") int status, @Param("videoId") String videoId);

    //用户的所有视频点赞数
    int getAllLike(@Param("id") String id);

    //更新用户点赞情况，点赞加一，取消减一
    int updateVideoUpvoteNum(@Param("likeNum") int likeNum, @Param("videoId") String videoId);

    video getVideoUpvoteByVideoId(String videoId);

    /**
     * author: wuei
     * data: 2017.8.22 15:46
     * 查询与面部识别结果匹配的视频列表
     * @param param 查询参数
     * @return 查询列表结果 ，分页
     */
    ArrayList<HashMap<String, String>> getRecognitionVideoPager(QueryParam param);

    ArrayList<HashMap<String, String>> getRecognitionVideo();

    /**
     * @author wuei
     * @date 2017.8.29 9:43
     * 根据时间查询用户的视频
     * @param start 开始时间
     *              end 结束时间
     *              userId 用户id
     * @return 查询结果
     *
     */
    ArrayList<HashMap<String, String>> getVideoByUploadTimePager(@Param("start") Date start, @Param("end") Date end, @Param("userId") String userId,
                                                            @Param("pageIndex")int pageIndex, @Param("pageSize")int pageSize);

    ArrayList<HashMap<String, String>> getVideoByUploadTime(@Param("start") Date start, @Param("end") Date end, @Param("userId") String userId);

}
