package com.zxwl.web.bean;

import com.zxwl.web.bean.po.GenericPo;

import java.util.Date;

/**
 * 视频存储
 * Created by generator Aug 3, 2017 3:06:40 AM
 */
public class video extends GenericPo<String> {
    //视频资源关联id
    private String videoUrl;
    //上传时间
    private java.util.Date uploadTime;
    //设备id
    private String deviceId;
    //设备所属id
    private String deviceOwnerId;
    //0 表示禁用，1 解禁，9 删除（后端）  前端视频状态（2 表示不公开）
    private  Integer  status;
    // 身高
    private Integer height;
    // 性别
    private Integer gender;
    // 年龄
    private Integer age;
    // 表情
    private String phiz;
    // 视频长度
    private Long videoLength;


    // 扩展字段

    //点赞数
    private int likeNum;
    //设备编码
    private String code;
    //试衣账号
    private String userName;
    //视频地址
    private String md5;
    //视频文件名
    private String name;
    //射频ID
    private String fidId;
    //分佣
    private String dividend;

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceOwnerId() {
        return deviceOwnerId;
    }

    public void setDeviceOwnerId(String deviceOwnerId) {
        this.deviceOwnerId = deviceOwnerId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhiz() {
        return phiz;
    }

    public void setPhiz(String phiz) {
        this.phiz = phiz;
    }

    public Long getVideoLength() {
        return videoLength;
    }

    public void setVideoLength(Long videoLength) {
        this.videoLength = videoLength;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFidId() {
        return fidId;
    }

    public void setFidId(String fidId) {
        this.fidId = fidId;
    }

    public String getDividend() {
        return dividend;
    }

    public void setDividend(String dividend) {
        this.dividend = dividend;
    }

    public interface Property extends GenericPo.Property {
        //
        String videoUrl = "videoUrl";
        //
        String uploadTime = "uploadTime";
        //
        String deviceId = "deviceId";
        //
        String deviceOwnerId = "deviceOwnerId";

        String  status = "status";

        String likeNum = "likeNum";

        String height = "height";

        String gender = "gender";

        String age = "age";

        String phiz = "phiz";

        String videoLength = "videoLength";

    }
}