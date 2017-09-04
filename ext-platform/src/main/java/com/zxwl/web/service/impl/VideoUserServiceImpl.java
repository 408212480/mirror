package com.zxwl.web.service.impl;

import com.zxwl.pay.common.util.str.StringUtils;
import com.zxwl.web.bean.*;
import com.zxwl.web.bean.api.PagerParamApi;
import com.zxwl.web.bean.api.PagerParamVideoByGoodsSpc;
import com.zxwl.web.bean.api.PagerParamVideoByUserCharacteristics;
import com.zxwl.web.bean.common.InsertParam;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.po.GenericPo;
import com.zxwl.web.bean.po.user.User;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.core.utils.WebUtil;
import com.zxwl.web.dao.*;
import com.zxwl.web.service.VideoUserService;
import com.zxwl.web.util.ResourceUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 设备管理 服务类实现
 * Created by generator
 */
@Service("videoUserService")
public class VideoUserServiceImpl extends AbstractServiceImpl<VideoUser, String> implements VideoUserService {

    @Resource
    protected VideoUserMapper videoUserMapper;

    @Resource
    protected videoMapper videoMapper;

    @Resource
    private ShopDeviceMapper shopDeviceMapper;

    @Resource
    private GoodsInfoMapper goodsInfoMapper;

    @Resource
    private VideoGoodsMapper videoGoodsMapper;

    @Override
    protected VideoUserMapper getMapper() {
        return this.videoUserMapper;
    }

    @Override
    public int updateByVideoUserID(String userId, String videoId, int status) {
        return getMapper().updateByVideoUserID(userId, videoId, status);
    }

    @Override
    public PagerResult<Map> userVideoListInfo(PagerParamApi pagerParamApi, HttpServletRequest req) {
        PagerResult<Map> pagerResult = new PagerResult<>();
        int total = getMapper().totalUserVideoListInfo();
        pagerResult.setTotal(total);
        if (total == 0) {
            pagerResult.setData(new ArrayList<>());
        } else {
            List<Map> mapLists = getMapper().userVideoListInfo(pagerParamApi);
            if (mapLists != null) {
                String type = ".MP4";
                for (Map map : mapLists) {
                    String videoimgs = (String) map.get("videoimglist");
                    if (!StringUtils.isEmpty(videoimgs)) {
                        String[] videoimglist = null;
                        videoimglist = convertStrToArray(videoimgs);
                        map.put("videoimglist", ResourceUtil.resourceBuildPath(req, String.valueOf(videoimglist[0]).trim()));
                    }
                    if (!StringUtils.isEmpty(String.valueOf(map.get("videomd5")))) {
                        map.put("videomd5", ResourceUtil.resourceBuildPath(req, String.valueOf(map.get("videomd5")).trim(), type));
                    }
                    if (!StringUtils.isEmpty(String.valueOf(map.get("usermd5")))) {
                        map.put("usermd5", ResourceUtil.resourceBuildPath(req, String.valueOf(map.get("usermd5")).trim()));
                    }
                }
            }
            //根据实际记录数量重新指定分页参数
            pagerResult.setData(mapLists);
        }
        return pagerResult;
    }

    @Override
    public PagerResult<Map> goodsVideoList(PagerParamApi pagerParamApi, String goodsId, HttpServletRequest req) {
        PagerResult<Map> pagerResult = new PagerResult<>();
        int total = getMapper().totalgoodsVideoList(goodsId);
        pagerResult.setTotal(total);
        if (total == 0) {
            pagerResult.setData(new ArrayList<>());
        } else {
            List<Map> mapLists = getMapper().goodsVideoList(pagerParamApi);
            if (mapLists != null) {
                String type = ".MP4";
                for (Map map : mapLists) {
                    String videoimgs = (String) map.get("videoimglist");
                    if (!StringUtils.isEmpty(videoimgs)) {
                        String[] videoimglist = null;
                        videoimglist = convertStrToArray(videoimgs);
                        map.put("videoimglist", ResourceUtil.resourceBuildPath(req, String.valueOf(videoimglist[0]).trim()));
                    }
                    if (!StringUtils.isEmpty(String.valueOf(map.get("userimgurl")))) {
                        map.put("userimgurl", ResourceUtil.resourceBuildPath(req, String.valueOf(map.get("userimgurl")).trim()));
                    }
                    if (!StringUtils.isEmpty(String.valueOf(map.get("videourl")))) {
                        map.put("videourl", ResourceUtil.resourceBuildPath(req, String.valueOf(map.get("videourl")).trim(), type));
                    }
                }
            }
            //根据实际记录数量重新指定分页参数
            pagerResult.setData(mapLists);
        }
        return pagerResult;
    }

    @Override
    public PagerResult<Map> userVideoList(PagerParamApi pagerParamApi, String userId, HttpServletRequest req) {
        PagerResult<Map> pagerResult = new PagerResult<>();
        int total = getMapper().totalUserVideoList(userId);
        pagerResult.setTotal(total);
        if (total == 0) {
            pagerResult.setData(new ArrayList<>());
        } else {
            List<Map> mapLists = getMapper().userALLVideoList(pagerParamApi);
            if (mapLists != null) {
                String type = ".MP4";
                for (Map map : mapLists) {
                    String videoimgs = (String) map.get("videoimglist");
                    if (!StringUtils.isEmpty(videoimgs)) {
                        String[] videoimglist = null;
                        videoimglist = convertStrToArray(videoimgs);
                        map.put("videoimglist", ResourceUtil.resourceBuildPath(req, String.valueOf(videoimglist[0]).trim()));
                    }
                    if (!StringUtils.isEmpty(String.valueOf(map.get("videourl")))) {
                        map.put("videourl", ResourceUtil.resourceBuildPath(req, String.valueOf(map.get("videourl")).trim(), type));
                    }
                }
            }
            //根据实际记录数量重新指定分页参数
            pagerResult.setData(mapLists);
        }
        return pagerResult;
    }

    @Override
    public PagerResult<Map> userCharacteristicsSearchVideo(PagerParamVideoByUserCharacteristics pagerParamVideoByUserCharacteristics, HttpServletRequest req) {
        PagerResult<Map> pagerResult = new PagerResult<>();
        int total = getMapper().totalUserCharacteristicsSearchVideo(pagerParamVideoByUserCharacteristics);
        pagerResult.setTotal(total);
        if (total == 0) {
            pagerResult.setData(new ArrayList<>());
        } else {
            List<Map> mapLists = getMapper().userCharacteristicsSearchVideo(pagerParamVideoByUserCharacteristics);
            if (mapLists != null) {
                String type = ".MP4";
                for (Map map : mapLists) {
                    if (!StringUtils.isEmpty(String.valueOf(map.get("usermd5")))) {
                        map.put("usermd5", ResourceUtil.resourceBuildPath(req, String.valueOf(map.get("usermd5")).trim()));
                    }
                    String videoimgs = (String) map.get("videoimglist");
                    if (!StringUtils.isEmpty(videoimgs)) {
                        String[] videoimglist = null;
                        videoimglist = convertStrToArray(videoimgs);
                        map.put("videoimglist", ResourceUtil.resourceBuildPath(req, String.valueOf(videoimglist[0]).trim()));
                    }
                    if (!StringUtils.isEmpty(String.valueOf(map.get("videomd5")))) {
                        map.put("videomd5", ResourceUtil.resourceBuildPath(req, String.valueOf(map.get("videomd5")).trim(), type));
                    }
                }
            }
            //根据实际记录数量重新指定分页参数
            pagerResult.setData(mapLists);
        }
        return pagerResult;
    }

    //字符串逗号分割
    public static String[] convertStrToArray(String str) {
        String[] strArray = null;
        strArray = str.split(","); //拆分字符为"," ,然后把结果交给数组strArray
        return strArray;
    }

    @Override
    public PagerResult<Map> goodsSpcSearchVideo(PagerParamVideoByGoodsSpc pagerParamVideoByGoodsSpc, HttpServletRequest req) {
        PagerResult<Map> pagerResult = new PagerResult<>();
        int total = getMapper().totalGoodsSpcSearchVideo(pagerParamVideoByGoodsSpc);
        pagerResult.setTotal(total);
        if (total == 0) {
            pagerResult.setData(new ArrayList<>());
        } else {
            List<Map> mapList = getMapper().goodsSpcSearchVideo(pagerParamVideoByGoodsSpc);
            if (mapList != null) {
                String type = ".MP4";
                for (Map map : mapList) {
                    String videoimg = (String) map.get("videoimglist");
                    String[] videoimglist = null;
                    if (!StringUtils.isEmpty(videoimg)) {
                        videoimglist = convertStrToArray(videoimg);
                        map.put("videoimglist", ResourceUtil.resourceBuildPath(req, String.valueOf(videoimglist[0]).trim()));
                    }
                    if (!StringUtils.isEmpty(String.valueOf(map.get("videomd5")))) {
                        map.put("videomd5", ResourceUtil.resourceBuildPath(req, String.valueOf(map.get("videomd5")).trim(), type));
                    }
                    if (!StringUtils.isEmpty(String.valueOf(map.get("usermd5")))) {
                        map.put("usermd5", ResourceUtil.resourceBuildPath(req, String.valueOf(map.get("usermd5")).trim()));
                    }
                }
            }
            //根据实际记录数量重新指定分页参数
            pagerResult.setData(mapList);
        }
        return pagerResult;
    }

    @Override
    public PagerResult<Map> otherUserVideoList(PagerParamApi pagerParamApi, String userId, HttpServletRequest req) {
        PagerResult<Map> pagerResult = new PagerResult<>();
        int total = getMapper().totalOtherVideoList(userId);
        pagerResult.setTotal(total);
        if (total == 0) {
            pagerResult.setData(new ArrayList<>());
        } else {
            List<Map> mapListVideo = getMapper().otherALLVideoList(pagerParamApi);
            if (mapListVideo != null) {
                String type = ".MP4";
                for (Map maps : mapListVideo) {
                    String videoimgs = (String) maps.get("videoimglist");
                    String[] videoimglist = null;
                    if (!StringUtils.isEmpty(videoimgs)){
                        videoimglist = convertStrToArray(videoimgs);
                        maps.put("videoimglist", ResourceUtil.resourceBuildPath(req, String.valueOf(videoimglist[0]).trim()));
                    }if(!StringUtils.isEmpty(String.valueOf(maps.get("videourl")))){
                        maps.put("videourl", ResourceUtil.resourceBuildPath(req, String.valueOf(maps.get("videourl")).trim(), type));
                    }
                }
            }
            //根据实际记录数量重新指定分页参数
            pagerResult.setData(mapListVideo);
        }
        return pagerResult;
    }


    /**
     * @param ids 需批量插入的视频id
     * @return 返回插入数据条数
     * @author Wuei
     * @data 2017.8.22.17:01
     * 批量插入视频用户关联表t_video_user
     */
    @Override
    public int insertAll(String[] ids) {
        String userId = WebUtil.getLoginUser().getId();
        int count = 0;
        for (String id : ids) {
            VideoUser videoUser = new VideoUser();
            videoUser.setUserId(userId);

            video v = videoMapper.selectByPk(id);
            if (v == null || "".equals(id)) {
                continue;
            }
            videoUser.setVideoId(id);

            List<ShopDevice> shopDevice = shopDeviceMapper.select(new QueryParam().where("device_id", v.getDeviceId()));
            if (shopDevice != null && shopDevice.size() != 0) {
                videoUser.setShopId(shopDevice.get(0).getShopId());
            }

            videoUser.setGmtCreate(new Date());
            videoUser.setLastChangeUser(WebUtil.getLoginUser().getId());
            videoUser.setGmtModify(new Date());

            String goodsId = goodsInfoMapper.selectByVideoId(id);

            videoUser.setGoodsId(goodsId);
            videoUser.setId(GenericPo.createUID());
            videoUserMapper.insert(InsertParam.build(videoUser));
            count++;
        }
        return count;
    }
}

