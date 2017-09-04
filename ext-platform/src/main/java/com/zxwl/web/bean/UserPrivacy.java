package com.zxwl.web.bean;
import com.zxwl.web.bean.po.GenericPo;
/**
* 用户隐私设置
* Created by generator Jul 24, 2017 12:43:21 AM
*/
public class UserPrivacy extends GenericPo<String>{
  		//试衣视频公开设置
        private int videoAccess;
  		//亮衣圈显示设置
        private int showAccess;
  		//
        private String userId;

        private String videoId;
  		//
        private java.util.Date gmtCreate;
  		//
        private java.util.Date gmtModify;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    /**
        * 获取 试衣视频公开设置
        * @return int 试衣视频公开设置
        */
        public int getVideoAccess(){
			return this.videoAccess;
        }

        /**
        * 设置 试衣视频公开设置
        */
        public void setVideoAccess(int videoAccess){
        	this.videoAccess=videoAccess;
        }
        /**
        * 获取 亮衣圈显示设置
        * @return int 亮衣圈显示设置
        */
        public int getShowAccess(){
			return this.showAccess;
        }

        /**
        * 设置 亮衣圈显示设置
        */
        public void setShowAccess(int showAccess){
        	this.showAccess=showAccess;
        }
        /**
        * 获取 
        * @return String 
        */
        public String getUserId(){
			return this.userId;
        }

        /**
        * 设置 
        */
        public void setUserId(String userId){
        	this.userId=userId;
        }
        /**
        * 获取 
        * @return java.util.Date 
        */
        public java.util.Date getGmtCreate(){
			return this.gmtCreate;
        }

        /**
        * 设置 
        */
        public void setGmtCreate(java.util.Date gmtCreate){
        	this.gmtCreate=gmtCreate;
        }
        /**
        * 获取 
        * @return java.util.Date 
        */
        public java.util.Date getGmtModify(){
			return this.gmtModify;
        }

        /**
        * 设置 
        */
        public void setGmtModify(java.util.Date gmtModify){
        	this.gmtModify=gmtModify;
        }
      
      public interface Property extends GenericPo.Property{
                //试衣视频公开设置
                 String videoAccess="videoAccess";
                //亮衣圈显示设置
                 String showAccess="showAccess";
                //
                 String videoId="videoId";

                 String userId="userId";
                //
                 String gmtCreate="gmtCreate";
                //
                 String gmtModify="gmtModify";
    	}
}