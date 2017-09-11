package com.zxwl.web.bean.api;

import com.zxwl.web.bean.GoodsInfo;
import com.zxwl.web.bean.GoodsInfoSpec;
import com.zxwl.web.bean.UserInfo;
import com.zxwl.web.bean.common.PagerResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xianghugui on 02/08/2017.
 */
public class GoodsInfoPage {
    private List<String> mapGoodsImgList = new ArrayList<>();

    private List<String> goodsInfoImgs = new ArrayList<>();

    private GoodsInfo goodsInfo;

    List<Map> goodsInfoSpecList;

    List<Map> goodsSpcColorList;

    List<Map> goodsSpcSizeList;

    private int commentTotal;
    //反馈视频商品不符ID
    private String feedBackVideoGoodsId;
    //已购数量
    private int buySum;
    //已购数量
    private int goodsStasticsQuality;

    PagerResult<Map> commentList = new PagerResult<>();

    public List<String> getGoodsInfoImgs() {
        return goodsInfoImgs;
    }

    public void setGoodsInfoImgs(List<String> goodsInfoImgs) {
        this.goodsInfoImgs = goodsInfoImgs;
    }

    public List<Map> getGoodsSpcColorList() {
        return goodsSpcColorList;
    }

    public void setGoodsSpcColorList(List<Map> goodsSpcColorList) {
        this.goodsSpcColorList = goodsSpcColorList;
    }

    public List<Map> getGoodsSpcSizeList() {
        return goodsSpcSizeList;
    }

    public void setGoodsSpcSizeList(List<Map> goodsSpcSizeList) {
        this.goodsSpcSizeList = goodsSpcSizeList;
    }

    public int getGoodsStasticsQuality() {
        return goodsStasticsQuality;
    }

    public void setGoodsStasticsQuality(int goodsStasticsQuality) {
        this.goodsStasticsQuality = goodsStasticsQuality;
    }

    public String getFeedBackVideoGoodsId() {
        return feedBackVideoGoodsId;
    }

    public void setFeedBackVideoGoodsId(String feedBackVideoGoodsId) {
        this.feedBackVideoGoodsId = feedBackVideoGoodsId;
    }

    public int getBuySum() {
        return buySum;
    }

    public void setBuySum(int buySum) {
        this.buySum = buySum;
    }

    public int getCommentTotal() {
        return commentTotal;
    }

    public void setCommentTotal(int commentTotal) {
        this.commentTotal = commentTotal;
    }

    public PagerResult<Map> getCommentList() {
        return commentList;
    }

    public void setCommentList(PagerResult<Map> commentList) {
        this.commentList = commentList;
    }

    public List<Map> getGoodsInfoSpecList() {
        return goodsInfoSpecList;
    }

    public void setGoodsInfoSpecList(List<Map> goodsInfoSpecList) {
        this.goodsInfoSpecList = goodsInfoSpecList;
    }

    public List<String> getMapGoodsImgList() {
        return mapGoodsImgList;
    }

    public void setMapGoodsImgList(List<String> mapGoodsImgList) {
        this.mapGoodsImgList = mapGoodsImgList;
    }

    public GoodsInfo getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    @Override
    public String toString() {
        return "GoodsInfoPage{" +
                "mapGoodsImgList=" + mapGoodsImgList +
                ", goodsInfoImgs=" + goodsInfoImgs +
                ", goodsInfo=" + goodsInfo +
                ", goodsInfoSpecList=" + goodsInfoSpecList +
                ", goodsSpcColorList=" + goodsSpcColorList +
                ", goodsSpcSizeList=" + goodsSpcSizeList +
                ", commentTotal=" + commentTotal +
                ", feedBackVideoGoodsId='" + feedBackVideoGoodsId + '\'' +
                ", buySum=" + buySum +
                ", goodsStasticsQuality=" + goodsStasticsQuality +
                ", commentList=" + commentList +
                '}';
    }
}
