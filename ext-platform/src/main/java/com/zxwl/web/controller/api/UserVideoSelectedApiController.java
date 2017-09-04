package com.zxwl.web.controller.api;

import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.po.resource.Resources;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.message.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.zxwl.web.core.message.ResponseMessage.ok;

/**
 * Project: zxwl-framework
 * Author: Sendya <18x@loacg.com>
 * Date: 2017/8/18 9:24
 *
 * 用户选择视频接口
 */
@RequestMapping("/api/userVideo")
@RestController
@Authorize
public class UserVideoSelectedApiController {

    private static final Logger logger = LoggerFactory.getLogger(UserVideoSelectedApiController.class);


    @Resource
    private com.zxwl.web.service.videoService videoService;

    @Resource
    private com.zxwl.web.service.VideoUserService videoUserService;


    @RequestMapping(value = "/selected", method = RequestMethod.POST)
    @AccessLogger("用户视频选中保存")
    @Authorize
    public ResponseMessage selectedVideoUpload(@RequestBody List selectIds) {
        // 将移动端人脸识别后，选择的视频保存到服务器端

        logger.info("selected ids: {}", selectIds);

        return ResponseMessage.ok();
    }

    /**
     * Project: zxwl-framework
     * Author: Wuei
     * Date: 2017/8/22 14:00
     *
     * @param file 用户视频，用于进行人脸识别
     *
     * 将移动端拍摄人脸识别视频，上传给服务器识别
     */
    @RequestMapping(value = "/recognitionImg", method = RequestMethod.POST)
    @AccessLogger("上传用户人脸识别视频")
    @Authorize
    public ResponseMessage VideoUpload(@RequestParam("file") MultipartFile file, HttpSession session) {
        // 将移动端拍摄人脸识别视频，上传给服务器识别
        if (logger.isInfoEnabled())
            logger.info("上传移动端人脸识别图片.");
        Resources resources = null;
        if (!file.isEmpty()) {
            if (logger.isInfoEnabled())
                logger.info("开始解析并写入人脸识别图片： ", file.getOriginalFilename());
            String fileName = file.getOriginalFilename();
            //TODO 人脸识别算法分析

        }
        else{
            return ResponseMessage.error("上传失败，请重试!", 500);
        }

        session.setAttribute("recognitionId", "");

        //响应上传成功的资源信息
        return ResponseMessage.ok();
    }

    /**
     * Author: Wuei
     * Date: 2017/8/22 14:00
     *
     * 上传人脸识别图片后，获取识别出的视频列表给用户
     * @param param 分页信息
     * @param session
     * @return
     */
    @RequestMapping(value = "/recognitionResult", method = RequestMethod.GET)
    @AccessLogger("获取人脸识别结果相关视频列表")
    @Authorize
    public ResponseMessage getRecognitionResult(QueryParam param, HttpSession session, HttpServletRequest req){
//        if(session.getAttribute("recognitionId") == null){
//            return ResponseMessage.error("您还没上传面部识别图片", 500);
//        }
        Object data;
        data = videoService.getRecognitionVideoPager(param, req);
        return ResponseMessage.ok(data);
    }

    /**
     * Project: zxwl-framework
     * Author: Wuei
     * Date: 2017/8/22 16:50
     *系统返回人脸识别后的视频列表后，用户选择自己的视频，上传保存
     *
     * @param id 用户选中的视频id串，多个id用逗号分隔
     *
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @AccessLogger("使用户关联选中的视频")
    @Authorize(action = "C")
    public ResponseMessage insertVideoUser(@PathVariable(value = "id")String id){
        if(id == null || "".equals(id)){
            return ResponseMessage.error("没有找到视频id", 500);
        }
        String[] videos = id.split(",");
        int count = videoUserService.insertAll(videos);
        return ResponseMessage.ok(count);
    }



}
