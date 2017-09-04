package com.zxwl.web.bean;
import com.zxwl.web.bean.po.BasePo;
import com.zxwl.web.bean.po.GenericPo;

import java.math.BigDecimal;

/**
* 商品百分比
* Created by generator Jul 26, 2017 2:16:24 AM
*/
public class GoodsPercentage extends BasePo<String> {
  		//商品ID
        private String goodsId;
  		//用户ID
        private String userId;
  		//百分比
        private float percentage;
        //商品价格
        private java.math.BigDecimal price;
        //商品总价
        private java.math.BigDecimal total;

        private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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
        * 获取 用户ID
        * @return String 用户ID
        */
        public String getUserId(){
			return this.userId;
        }

        /**
        * 设置 用户ID
        */
        public void setUserId(String userId){
        	this.userId=userId;
        }
        /**
        * 获取 百分比
        * @return java.math.BigDecimal 百分比
        */
        public float getPercentage(){
			return this.percentage;
        }

        /**
        * 设置 百分比
        */
        public void setPercentage(float percentage){
        	this.percentage=percentage;
        }

      public interface Property extends GenericPo.Property{
                //商品ID
                 String goodsId="goodsId";
                //用户ID
                 String userId="userId";
                //百分比
                 String percentage="percentage";

                 String price="price";

                 String total="total";

                 int status = 0;
    	}
}