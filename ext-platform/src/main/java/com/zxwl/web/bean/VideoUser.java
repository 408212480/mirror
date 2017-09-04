package com.zxwl.web.bean;

import com.zxwl.web.bean.po.BasePo;
import com.zxwl.web.bean.po.GenericPo;

/**
 * 设备管理
 * Created by generator Jul 12, 2017 9:18:48 AM
 */
public class VideoUser extends BasePo<String> {
    //用户ID
    private String userId;
    //视频ID
    private String videoId;
    //视频ID
    private String goodsId;
    //店铺ID
    private String shopId;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public interface Property extends GenericPo.Property {
        //用户ID
        String userId = "userId";
        //视频id
        String videoId = "videoId";
        //视频id
        String goodsId = "goodsId";
        //视频id
        String shopId = "shopId";
    }
}