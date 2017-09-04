package com.zxwl.web.bean;
import com.zxwl.web.bean.po.GenericPo;
/**
* 视频商品符合表
* Created by generator Aug 14, 2017 7:24:41 AM
*/
public class VideoGoodsFit extends GenericPo<String>{
  		//视频商品归属id
        private String videoGoodId;
  		//是否相符
        private int isFit;
  		//举荐人
        private String reportUserId;
  		//举荐时间
        private java.util.Date reportTime;
  		//
        private java.util.Date gmtModify;
  		//
        private String lastChangeUser;

        /**
        * 获取 视频商品归属id
        * @return String 视频商品归属id
        */
        public String getVideoGoodId(){
			return this.videoGoodId;
        }

        /**
        * 设置 视频商品归属id
        */
        public void setVideoGoodId(String videoGoodId){
        	this.videoGoodId=videoGoodId;
        }
        /**
        * 获取 是否相符
        * @return int 是否相符
        */
        public int getIsFit(){
			return this.isFit;
        }

        /**
        * 设置 是否相符
        */
        public void setIsFit(int isFit){
        	this.isFit=isFit;
        }
        /**
        * 获取 举荐人
        * @return String 举荐人
        */
        public String getReportUserId(){
			return this.reportUserId;
        }

        /**
        * 设置 举荐人
        */
        public void setReportUserId(String reportUserId){
        	this.reportUserId=reportUserId;
        }
        /**
        * 获取 举荐时间
        * @return java.util.Date 举荐时间
        */
        public java.util.Date getReportTime(){
			return this.reportTime;
        }

        /**
        * 设置 举荐时间
        */
        public void setReportTime(java.util.Date reportTime){
        	this.reportTime=reportTime;
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
        /**
        * 获取 
        * @return String 
        */
        public String getLastChangeUser(){
			return this.lastChangeUser;
        }

        /**
        * 设置 
        */
        public void setLastChangeUser(String lastChangeUser){
        	this.lastChangeUser=lastChangeUser;
        }
      
      public interface Property extends GenericPo.Property{
                //视频商品归属id
                 String videoGoodId="videoGoodId";
                //是否相符
                 String isFit="isFit";
                //举荐人
                 String reportUserId="reportUserId";
                //举荐时间
                 String reportTime="reportTime";
                //
                 String gmtModify="gmtModify";
                //
                 String lastChangeUser="lastChangeUser";
    	}
}