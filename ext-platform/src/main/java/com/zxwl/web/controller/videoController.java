package com.zxwl.web.controller;

import com.zxwl.pay.common.util.str.StringUtils;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.po.GenericPo;
import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.video;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.core.utils.WebUtil;
import com.zxwl.web.service.resource.ResourcesService;
import com.zxwl.web.util.ResourceUtil;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import com.zxwl.web.service.videoService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.zxwl.web.core.message.ResponseMessage.ok;

/**
 * 视频存储控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/video")
@AccessLogger("视频存储")
@Authorize(module = "video")
public class videoController extends GenericController<video, String> {

    @Resource
    private  videoService videoService;
    @Resource
    private ResourcesService resourcesService;

    @Override
    public  videoService getService() {
        return this.videoService;
    }

    /**
     * 查询列表,并返回查询结果
     *
     * @param param 查询参数 {@link QueryParam}
     * @return 查询结果, 如果参数指定了分页(默认指定)将返回格式如:{total:数据总数,data:[{}]}的数据.
     * 否则返回格式[{}]
     */
    @Override
    public ResponseMessage list(QueryParam param) {

        // 获取条件查询
        Object data;
        if (!param.isPaging())//不分页
            data = getService().List();
        else{
            PagerResult result=new PagerResult();
            List<video> videoList=getService().List(param);
            for (video video:videoList){
                if (!StringUtils.isEmpty(video.getMd5())){
                    video.setMd5(ResourceUtil.resourceBuildPath(WebUtil.getHttpServletRequest(),video.getMd5(),"1"));
                }
            }
            result.setData(videoList);
            result.setTotal(getService().total(param));
            data = result;
        }
        return ok(data)
                .include(getPOType(), param.getIncludes())
                .exclude(getPOType(), param.getExcludes())
                .onlyData();
    }

    @RequestMapping(value = "/videodetail/{videoId}", method = RequestMethod.GET)
    @AccessLogger("视频详情")
    @Authorize(action = "R")
    public ResponseMessage videoInfo(@PathVariable("videoId") String videoId) {
        // 获取条件查询
        video data = videoService.getVideoByVideoId(videoId);
        if (data==null)
            ResponseMessage.error("data not found");
        if (data.getMd5()==null)
            return ResponseMessage.error("视频文件未找到");
        if (data.getRecordId()==null)
            return ResponseMessage.error("视频图片未找到");
        data.setMd5(ResourceUtil.resourceBuildPath(WebUtil.getHttpServletRequest(),data.getMd5(),"1"));
        data.setVideoImgs(resourcesService.selectImages(WebUtil.getBasePath(WebUtil.getHttpServletRequest()),data.getRecordId()));
        return ResponseMessage.ok(data);
    }
    @RequestMapping(value = "/videodetailimg/{videoId}", method = RequestMethod.GET)
    @AccessLogger("视频详情")
    @Authorize(action = "R")
    public ResponseMessage videoImgList(@PathVariable("videoId") String videoId) {
        // 获取条件查询
        Object data = videoService.selectVideoImgByVideoId(videoId);
        return ResponseMessage.ok(data);
    }

    @RequestMapping(value = "/update/{id}/{status}", method = RequestMethod.PUT)
    @AccessLogger("修改")
    @Authorize(action = "U")
    public ResponseMessage update(@PathVariable("id") String id,@PathVariable("status") int status) {
        video old = getService().selectByPk(id);
        assertFound(old, "data is not found!");
        int number = getService().updateStatus(status,id);
        return ok(number);
    }

}
