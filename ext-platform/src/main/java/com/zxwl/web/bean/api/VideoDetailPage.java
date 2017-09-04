package com.zxwl.web.bean.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by xianghugui on 02/08/2017.
 */
public class VideoDetailPage {
    Map videoDetail;

    List<Map> videoImgUrl;

    BigDecimal money;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Map getVideoDetail() {
        return videoDetail;
    }

    public void setVideoDetail(Map videoDetail) {
        this.videoDetail = videoDetail;
    }

    public List<Map> getVideoImgUrl() {
        return videoImgUrl;
    }

    public void setVideoImgUrl(List<Map> videoImgUrl) {
        this.videoImgUrl = videoImgUrl;
    }

    @Override
    public String toString() {
        return "VideoDetailPage{" +
                "videoDetail=" + videoDetail +
                ", videoImgUrl=" + videoImgUrl +
                ", money=" + money +
                '}';
    }
}
