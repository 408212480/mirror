package com.zxwl.web.bean;
import com.zxwl.web.bean.po.GenericPo;
/**
* 账户余额
* Created by generator Jul 24, 2017 12:46:22 AM
*/
public class UserAccount extends GenericPo<String>{
  		//钱包余额
        private java.math.BigDecimal balance;
  		//
        private String userId;
  		//
        private java.util.Date gmtCreate;
  		//
        private java.util.Date gmtModify;

        /**
        * 获取 钱包余额
        * @return java.math.BigDecimal 钱包余额
        */
        public java.math.BigDecimal getBalance(){
			return this.balance;
        }

        /**
        * 设置 钱包余额
        */
        public void setBalance(java.math.BigDecimal balance){
        	this.balance=balance;
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
                //钱包余额
                 String balance="balance";
                //
                 String userId="userId";
                //
                 String gmtCreate="gmtCreate";
                //
                 String gmtModify="gmtModify";
    	}
}