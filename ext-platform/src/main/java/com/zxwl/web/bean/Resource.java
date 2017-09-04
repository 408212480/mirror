package com.zxwl.web.bean;

/**
 * Created by Administrator on 2017/8/8.
 */
public class Resource {
    //资源类型
    private int type;
    //md5码
    private String md5;
    //s_resoures对应资源id
    private String resourceId;
    //资源文件名
    private String name;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
