package com.zxwl.web.bean;
import com.zxwl.web.bean.po.GenericPo;
/**
* 店铺-商店关系表
* Created by generator Aug 7, 2017 9:56:59 AM
*/
public class ShopGoods extends GenericPo<String>{
  		//
        private String shopId;
  		//
        private String goodsId;

        /**
        * 获取 
        * @return String 
        */
        public String getShopId(){
			return this.shopId;
        }

        /**
        * 设置 
        */
        public void setShopId(String shopId){
        	this.shopId=shopId;
        }
        /**
        * 获取 
        * @return String 
        */
        public String getGoodsId(){
			return this.goodsId;
        }

        /**
        * 设置 
        */
        public void setGoodsId(String goodsId){
        	this.goodsId=goodsId;
        }
      
      public interface Property extends GenericPo.Property{
                //
                 String shopId="shopId";
                //
                 String goodsId="goodsId";
    	}
}