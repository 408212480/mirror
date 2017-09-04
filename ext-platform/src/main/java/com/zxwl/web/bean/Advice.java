package com.zxwl.web.bean;

import com.zxwl.web.bean.po.GenericPo;

import java.util.Date;

/**
 * Created by wuei on 2017/8/25 09:17.
 *
 * 意见反馈
 * */
public class Advice extends GenericPo<String>{
    private String id;

    //意见内容
    private String advice;

    //反馈意见用户
    private String userId;

    //反馈时间
    private Date gmtCreate;

    //是否处理
    private int status;

    //处理结果
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
