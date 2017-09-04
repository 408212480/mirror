package com.zxwl.web.controller.api;

import com.google.common.base.Preconditions;
import com.zxwl.web.bean.FidGoodsSpec;
import com.zxwl.web.bean.VideoGoods;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.po.GenericPo;
import com.zxwl.web.bean.video;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.core.utils.RandomUtil;
import com.zxwl.web.core.utils.WebUtil;
import com.zxwl.web.service.FidGoodsSpecService;
import com.zxwl.web.service.VideoGoodsService;
import com.zxwl.web.service.videoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.Date;
import java.util.Map;

/**
 * Project: zxwl-framework
 * Author: Sendya <18x@loacg.com>
 * Date: 2017/8/31 16:16
 */
@RequestMapping("/api/video")
@RestController
public class VideoUploadApiController {

    @Autowired
    private videoService service;

    @Autowired
    private FidGoodsSpecService fidGoodsSpecService;

    @Autowired
    private VideoGoodsService videoGoodsService;

    /**
     * 接收硬件 镜子 上传的参数
     * @param data
     * @return
     */
    @RequestMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseMessage upload(@RequestBody Map data) {

        // primaryKey
        // filePath
        // height
        // gender 性别
        // age
        // phiz 表情
        // length 视频长度（秒）
        // acquisitionTime 采集时间
        // deviceId
        // userId

        Preconditions.checkNotNull(data);
        video video1 = new video();

        video1.setVideoUrl(RandomUtil.randomChar(6));
        video1.setHeight(Integer.valueOf(String.valueOf(data.get("height"))));
        video1.setGender(Integer.valueOf(String.valueOf(data.get("gender")))); // 0 男  1 女
        video1.setAge(Integer.valueOf(String.valueOf(data.get("age"))));
        video1.setPhiz(String.valueOf(data.get("phiz")));
        video1.setVideoLength(Long.valueOf(String.valueOf(data.get("videoLength"))));
        video1.setUploadTime(new Date());
        video1.setDeviceId(String.valueOf(data.get("deviceId")));
        video1.setDeviceOwnerId(String.valueOf(data.get("deviceOwnerId")));
        video1.setStatus(1);
        video1.setLikeNum(0);

        String id = service.insert(video1);
        if (id == null || "".equals(id)) {
            return ResponseMessage.error("Save video error");
        }

        if ( data.get("fid") != null && !"".equals(data.get("fid"))){
            String fid = String.valueOf(data.get("fid"));

            FidGoodsSpec fidGoodsSpec = fidGoodsSpecService.createQuery().select("*").where(FidGoodsSpec.Property.fidId, fid).single();
            if (fidGoodsSpec != null) {
                VideoGoods videoGoods = new VideoGoods();
                videoGoods.setGoodsspecId(fidGoodsSpec.getSpecId());
                videoGoods.setVideoId(video1.getId());
                videoGoods.setGmtCreate(new Date());
                videoGoods.setGmtModify(new Date());
                videoGoods.setLastChangeUser(WebUtil.getLoginUser().getId());
                videoGoodsService.insert(videoGoods);
            }
        }

        return ResponseMessage.created(id);
    }

}
