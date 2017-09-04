package com.zxwl.web.bean;
import com.zxwl.web.bean.po.GenericPo;
/**
* 视频发布
* Created by generator Aug 1, 2017 3:49:06 AM
*/
public class VideoPost extends GenericPo<String>{
  		//
        private String userId;
  		//
        private String videoId;
  		//发布时间
        private java.util.Date postTime;
  		//点赞数量
        private int likeNum;

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
        * @return String 
        */
        public String getVideoId(){
			return this.videoId;
        }

        /**
        * 设置 
        */
        public void setVideoId(String videoId){
        	this.videoId=videoId;
        }
        /**
        * 获取 发布时间
        * @return java.util.Date 发布时间
        */
        public java.util.Date getPostTime(){
			return this.postTime;
        }

        /**
        * 设置 发布时间
        */
        public void setPostTime(java.util.Date postTime){
        	this.postTime=postTime;
        }
        /**
        * 获取 点赞数量
        * @return int 点赞数量
        */
        public int getLikeNum(){
			return this.likeNum;
        }

        /**
        * 设置 点赞数量
        */
        public void setLikeNum(int likeNum){
        	this.likeNum=likeNum;
        }
      
      public interface Property extends GenericPo.Property{
                //
                 String userId="userId";
                //
                 String videoId="videoId";
                //发布时间
                 String postTime="postTime";
                //点赞数量
                 String likeNum="likeNum";
    	}
}