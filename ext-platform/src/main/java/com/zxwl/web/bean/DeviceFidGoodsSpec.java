package com.zxwl.web.bean;
import com.alibaba.fastjson.annotation.JSONField;
import com.zxwl.web.bean.po.GenericPo;
/**
* 射频商品规格关联表
* Created by generator Jul 27, 2017 3:38:39 AM
*/
public class DeviceFidGoodsSpec extends GenericPo<String>{
  		//射频id
        private String fidId;
  		//商品规格id
        private String specId;

        //商品规格id
        private String shopId;
  		//
        private java.util.Date gmtCreate;
  		//
        private java.util.Date gmtModify;
  		//
        private String lastChangeUser;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    /**
        * 获取 射频id
        * @return String 射频id
        */
        public String getFidId(){
			return this.fidId;
        }

        /**
        * 设置 射频id
        */
        public void setFidId(String fidId){
        	this.fidId=fidId;
        }
        /**
        * 获取 商品规格id
        * @return String 商品规格id
        */
        public String getSpecId(){
			return this.specId;
        }

        /**
        * 设置 商品规格id
        */
        public void setSpecId(String specId){
        	this.specId=specId;
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
                //射频id
                 String fidId="fidId";
                //商品规格id
                 String specId="specId";

                 String shopId="shopId";
                //
                 String gmtCreate="gmtCreate";
                //
                 String gmtModify="gmtModify";
                //
                 String lastChangeUser="lastChangeUser";
    	}
}