package com.zxwl.web.bean;
import com.zxwl.web.bean.po.GenericPo;
/**
* 商品规格表
* Created by generator Jul 21, 2017 9:00:37 AM
*/
public class GoodsSpecifications extends GenericPo<String>{
  		//尺寸
        private int size;
  		//颜色
        private int color;
  		//商品ID
        private String goodsId;
  		//创建时间
        private java.util.Date gmtCreate;
  		//修改时间
        private java.util.Date gmtModify;
  		//最后修改用户
        private String lastChangeUser;

        /**
        * 获取 尺寸
        * @return int 尺寸
        */
        public int getSize(){
			return this.size;
        }

        /**
        * 设置 尺寸
        */
        public void setSize(int size){
        	this.size=size;
        }
        /**
        * 获取 颜色
        * @return int 颜色
        */
        public int getColor(){
			return this.color;
        }

        /**
        * 设置 颜色
        */
        public void setColor(int color){
        	this.color=color;
        }
        /**
        * 获取 商品ID
        * @return String 商品ID
        */
        public String getGoodsId(){
			return this.goodsId;
        }

        /**
        * 设置 商品ID
        */
        public void setGoodsId(String goodsId){
        	this.goodsId=goodsId;
        }
        /**
        * 获取 创建时间
        * @return java.util.Date 创建时间
        */
        public java.util.Date getGmtCreate(){
			return this.gmtCreate;
        }

        /**
        * 设置 创建时间
        */
        public void setGmtCreate(java.util.Date gmtCreate){
        	this.gmtCreate=gmtCreate;
        }
        /**
        * 获取 修改时间
        * @return java.util.Date 修改时间
        */
        public java.util.Date getGmtModify(){
			return this.gmtModify;
        }

        /**
        * 设置 修改时间
        */
        public void setGmtModify(java.util.Date gmtModify){
        	this.gmtModify=gmtModify;
        }
        /**
        * 获取 最后修改用户
        * @return String 最后修改用户
        */
        public String getLastChangeUser(){
			return this.lastChangeUser;
        }

        /**
        * 设置 最后修改用户
        */
        public void setLastChangeUser(String lastChangeUser){
        	this.lastChangeUser=lastChangeUser;
        }
      
      public interface Property extends GenericPo.Property{
                //尺寸
                 String size="size";
                //颜色
                 String color="color";
                //商品ID
                 String goodsId="goodsId";
                //创建时间
                 String gmtCreate="gmtCreate";
                //修改时间
                 String gmtModify="gmtModify";
                //最后修改用户
                 String lastChangeUser="lastChangeUser";
    	}
}