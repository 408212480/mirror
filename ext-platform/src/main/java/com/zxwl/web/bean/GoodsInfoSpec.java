package com.zxwl.web.bean;

import com.zxwl.web.bean.po.GenericPo;

/**
 * 服装分配
 * Created by generator Jul 25, 2017 7:47:48 AM
 */
public class GoodsInfoSpec extends GenericPo<String> {
    //尺码
    private String size;
    //颜色
    private String color;
    //商品ID
    private String goodsId;
    //创建时间
    private java.util.Date gmtCreate;
    //修改时间
    private java.util.Date gmtModify;
    //上次更新用户
    private String lastChangeUser;
    //商品名称
    private String title;

    private int quality;
    //类别编号
    private String classCode;

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    /**
     * 获取 尺码
     *
     * @return int
     */
    public String getSize() {
        return this.size;
    }

    /**
     * 设置 尺码
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * 获取 颜色
     *
     * @return int
     */
    public String getColor() {
        return this.color;
    }

    /**
     * 设置 颜色
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * 获取 商品ID
     *
     * @return String
     */
    public String getGoodsId() {
        return this.goodsId;
    }

    /**
     * 设置 商品ID
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取 创建时间
     *
     * @return java.util.Date
     */
    public java.util.Date getGmtCreate() {
        return this.gmtCreate;
    }

    /**
     * 设置 创建时间
     */
    public void setGmtCreate(java.util.Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取 修改时间
     *
     * @return java.util.Date
     */
    public java.util.Date getGmtModify() {
        return this.gmtModify;
    }

    /**
     * 设置 修改时间
     */
    public void setGmtModify(java.util.Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    /**
     * 获取 上次更新用户
     *
     * @return String
     */
    public String getLastChangeUser() {
        return this.lastChangeUser;
    }

    /**
     * 设置 上次更新用户
     */
    public void setLastChangeUser(String lastChangeUser) {
        this.lastChangeUser = lastChangeUser;
    }

    /**
     * 获取 商品名称
     *
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置 商品名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取 类别编号
     *
     * @return String
     */
    public String getClassCode() {
        return classCode;
    }

    /**
     * 设置 类别编号
     */
    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public interface Property extends GenericPo.Property {
        //尺寸
        String size = "size";
        //颜色
        String color = "color";
        //商品ID
        String goodsId = "goodsId";
        //创建时间
        String gmtCreate = "gmtCreate";
        //修改时间
        String gmtModify = "gmtModify";
        //上次更新人
        String lastChangeUser = "lastChangeUser";
        //商品名称
        String title="title";
        String quality="quality";
        //类别编号
        String classCode="classCode";
    }
}