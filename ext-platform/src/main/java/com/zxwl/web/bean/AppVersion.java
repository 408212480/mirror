package com.zxwl.web.bean;
import com.zxwl.web.bean.po.GenericPo;
/**
* 版本信息
* Created by generator Aug 25, 2017 7:04:30 AM
*/
public class AppVersion extends GenericPo<String>{
  		//
        private java.util.Date releaseTime;
  		//
        private String version;
  		//
        private String describe;
  		//
        private String createUserId;
        //
        private String url;
  		//客户端类型
        private int type;
  		//
        private java.util.Date gmtCreate;
  		//
        private java.util.Date gmtModify;
  		//
        private String lastChangeUser;

        /**
        * 获取 
        * @return java.util.Date 
        */
        public java.util.Date getReleaseTime(){
			return this.releaseTime;
        }

        /**
        * 设置 
        */
        public void setReleaseTime(java.util.Date releaseTime){
        	this.releaseTime=releaseTime;
        }
        /**
        * 获取 
        * @return String 
        */
        public String getVersion(){
			return this.version;
        }

        /**
        * 设置 
        */
        public void setVersion(String version){
        	this.version=version;
        }
        /**
        * 获取 
        * @return String 
        */
        public String getDescribe(){
			return this.describe;
        }

        /**
        * 设置 
        */
        public void setDescribe(String describe){
        	this.describe=describe;
        }
        /**
        * 获取 
        * @return String 
        */
        public String getCreateUserId(){
			return this.createUserId;
        }

        /**
        * 设置 
        */
        public void setCreateUserId(String createUserId){
        	this.createUserId=createUserId;
        }
        /**
        * 获取 客户端类型
        * @return int 客户端类型
        */
        public int getType(){
			return this.type;
        }

        /**
        * 设置 客户端类型
        */
        public void setType(int type){
        	this.type=type;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public interface Property extends GenericPo.Property{
                //
                 String releaseTime="releaseTime";
                //
                 String version="version";
                //
                 String describe="describe";
                //
                 String createUserId="createUserId";
                 //
                 String url="url";
                //客户端类型
                 String type="type";
                //
                 String gmtCreate="gmtCreate";
                //
                 String gmtModify="gmtModify";
                //
                 String lastChangeUser="lastChangeUser";
    	}
}