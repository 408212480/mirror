package com.zxwl.web.bean;
import com.zxwl.web.bean.po.GenericPo;
/**
* 视频存储
* Created by generator Jul 21, 2017 3:46:39 AM
*/
public class VideoStorage extends GenericPo<String>{
  		//视频地址
        private String videoUrl;
  		//上传时间
        private java.util.Date uploadTime;
  		//设备ID
        private String deviceId;
  		//设备拥有者ID
        private String deviceOwnerId;

        /**
        * 获取 视频地址
        * @return String 视频地址
        */
        public String getVideoUrl(){
			return this.videoUrl;
        }

        /**
        * 设置 视频地址
        */
        public void setVideoUrl(String videoUrl){
        	this.videoUrl=videoUrl;
        }
        /**
        * 获取 上传时间
        * @return java.util.Date 上传时间
        */
        public java.util.Date getUploadTime(){
			return this.uploadTime;
        }

        /**
        * 设置 上传时间
        */
        public void setUploadTime(java.util.Date uploadTime){
        	this.uploadTime=uploadTime;
        }
        /**
        * 获取 设备ID
        * @return String 设备ID
        */
        public String getDeviceId(){
			return this.deviceId;
        }

        /**
        * 设置 设备ID
        */
        public void setDeviceId(String deviceId){
        	this.deviceId=deviceId;
        }
        /**
        * 获取 设备拥有者ID
        * @return String 设备拥有者ID
        */
        public String getDeviceOwnerId(){
			return this.deviceOwnerId;
        }

        /**
        * 设置 设备拥有者ID
        */
        public void setDeviceOwnerId(String deviceOwnerId){
        	this.deviceOwnerId=deviceOwnerId;
        }
      
      public interface Property extends GenericPo.Property{
                //视频地址
                 String videoUrl="videoUrl";
                //上传时间
                 String uploadTime="uploadTime";
                //设备ID
                 String deviceId="deviceId";
                //设备拥有者ID
                 String deviceOwnerId="deviceOwnerId";
    	}
}