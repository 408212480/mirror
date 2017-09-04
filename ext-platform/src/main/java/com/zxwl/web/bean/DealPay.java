package com.zxwl.web.bean;
import com.zxwl.web.bean.po.GenericPo;
/**
* 交易记录
* Created by generator Aug 2, 2017 2:31:58 AM
*/
public class DealPay extends GenericPo<String>{
  		//订单id
        private String orderId;
  		//哪个用户的交易记录
        private String creatorId;
  		//交易时间
        private java.util.Date dealTime;
  		//交易类型 1 支出，2 充值
        private int dealType;
  		//交易金额
        private java.math.BigDecimal dealMoney;
  		//
        private java.util.Date gmtCreate;
  		//
        private java.util.Date gmtModify;

        /**
        * 获取 订单id
        * @return String 订单id
        */
        public String getOrderId(){
			return this.orderId;
        }

        /**
        * 设置 订单id
        */
        public void setOrderId(String orderId){
        	this.orderId=orderId;
        }
        /**
        * 获取 哪个用户的交易记录
        * @return String 哪个用户的交易记录
        */
        public String getCreatorId(){
			return this.creatorId;
        }

        /**
        * 设置 哪个用户的交易记录
        */
        public void setCreatorId(String creatorId){
        	this.creatorId=creatorId;
        }
        /**
        * 获取 交易时间
        * @return java.util.Date 交易时间
        */
        public java.util.Date getDealTime(){
			return this.dealTime;
        }

        /**
        * 设置 交易时间
        */
        public void setDealTime(java.util.Date dealTime){
        	this.dealTime=dealTime;
        }
        /**
        * 获取 交易类型 1 支出，2 充值
        * @return int 交易类型 1 支出，2 充值
        */
        public int getDealType(){
			return this.dealType;
        }

        /**
        * 设置 交易类型 1 支出，2 充值
        */
        public void setDealType(int dealType){
        	this.dealType=dealType;
        }
        /**
        * 获取 交易金额
        * @return java.math.BigDecimal 交易金额
        */
        public java.math.BigDecimal getDealMoney(){
			return this.dealMoney;
        }

        /**
        * 设置 交易金额
        */
        public void setDealMoney(java.math.BigDecimal dealMoney){
        	this.dealMoney=dealMoney;
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
                //订单id
                 String orderId="orderId";
                //哪个用户的交易记录
                 String creatorId="creatorId";
                //交易时间
                 String dealTime="dealTime";
                //交易类型 1 支出，2 充值
                 String dealType="dealType";
                //交易金额
                 String dealMoney="dealMoney";
                //
                 String gmtCreate="gmtCreate";
                //
                 String gmtModify="gmtModify";
    	}
}