package com.zxwl.web.bean;
import com.zxwl.web.bean.po.GenericPo;
/**
* 视频商品关联表
* Created by generator Aug 22, 2017 9:19:17 AM
*/
public class VideoGoods extends GenericPo<String>{
  		//商品规格id
        private String goodsspecId;
  		//
        private String videoId;
  		//
        private java.util.Date gmtCreate;
  		//
        private java.util.Date gmtModify;
  		//
        private String lastChangeUser;

        /**
        * 获取 商品规格id
        * @return String 商品规格id
        */
        public String getGoodsspecId(){
			return this.goodsspecId;
        }

        /**
        * 设置 商品规格id
        */
        public void setGoodsspecId(String goodsspecId){
        	this.goodsspecId=goodsspecId;
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
      
      public interface Property extends GenericPo.Property{
                //商品规格id
                 String goodsspecId="goodsspecId";
                //
                 String videoId="videoId";
                //
                 String gmtCreate="gmtCreate";
                //
                 String gmtModify="gmtModify";
                //
                 String lastChangeUser="lastChangeUser";
    	}
}