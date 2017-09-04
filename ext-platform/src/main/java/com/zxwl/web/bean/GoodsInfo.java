package com.zxwl.web.bean;

import com.zxwl.web.bean.po.BasePo;
import com.zxwl.web.bean.po.GenericPo;

/**
 * 商品信息
 * Created by generator Jul 12, 2017 9:36:12 AM
 */
public class GoodsInfo extends BasePo<String> {
    //类别编码
    private String classCode;
    //标题/名称
    private String title;
    //价格
    private Double price;
    //详情
    private String describe;
    //商品图片ID，与 t_metadata_rel 表 record_id 对应
    private String imgId;
    //轮播图地址，与 t_metadata_rel 表 record_id 对应
    private String carouselImgUrl;
    //所属用户id
    private String creatorId;

    private int quality;

    // 扩展字段 商店ID
    private String shopId;
    private String shopName;
    // 扩展字段 图片ID
    private String imgIds;
    private String carouselImgUrls;
    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    /**
     * 获取 类别编码
     *
     * @return String 类别编码
     */
    public String getClassCode() {
        return this.classCode;
    }

    /**
     * 设置 类别编码
     */
    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    /**
     * 获取 标题/名称
     *
     * @return String 标题/名称
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 设置 标题/名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取 价格
     *
     * @return Double 价格
     */
    public Double getPrice() {
        return this.price;
    }

    /**
     * 设置 价格
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    /**
     * 获取 详情
     *
     * @return String 详情
     */
    public String getDescribe() {
        return this.describe;
    }

    /**
     * 设置 详情
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * 获取 所属用户id
     *
     * @return String 所属用户id
     */
    public String getCreatorId() {
        return this.creatorId;
    }

    /**
     * 设置 所属用户id
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getImgIds() {
        return imgIds;
    }

    public void setImgIds(String imgIds) {
        this.imgIds = imgIds;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCarouselImgUrl() {
        return carouselImgUrl;
    }

    public void setCarouselImgUrl(String carouselImgUrl) {
        this.carouselImgUrl = carouselImgUrl;
    }

    public String getCarouselImgUrls() {
        return carouselImgUrls;
    }

    public void setCarouselImgUrls(String carouselImgUrls) {
        this.carouselImgUrls = carouselImgUrls;
    }

    public interface Property extends BasePo.Property {
        //类别编码
        String classCode = "classCode";
        //标题/名称
        String title = "title";
        //价格
        String price = "price";
        //详情
        String describe = "describe";
        // 图片id (可对应多张)
        String imgId = "img_id";
        //所属用户id
        String creatorId = "creatorId";
        String quality = "quality";
        String carouselImgUrl="carouselImgUrl";
    }
}