package com.zxwl.web.bean;

/**
 * Created by Administrator on 2017/8/2.
 */
public class GoodsShop {
    //商品编码
    private String id;
    //商品名称
    private String title;
    //店铺名
    private String shopName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
