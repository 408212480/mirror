package com.zxwl.web.bean;
import com.zxwl.web.bean.po.GenericPo;
/**
* 视频存储
* Created by generator Jul 21, 2017 3:48:41 AM
*/
public class ResourceAssociation extends GenericPo<String>{
  		//图片id，与 s_resource 表 u_id 关联
        private String dataId;
  		//与 视频表 | 店铺图片 等 主键关联
        private String recordId;
  		//图片类型： 1 设备广告，2 产品，3 评价图，4 视频图片
        private int dataType;
  		//类型： 0 图片，1 视频
        private int type;

        /**
        * 获取 图片id，与 s_resource 表 u_id 关联
        * @return String 图片id，与 s_resource 表 u_id 关联
        */
        public String getDataId(){
			return this.dataId;
        }

        /**
        * 设置 图片id，与 s_resource 表 u_id 关联
        */
        public void setDataId(String dataId){
        	this.dataId=dataId;
        }
        /**
        * 获取 与 视频表 | 店铺图片 等 主键关联
        * @return String 与 视频表 | 店铺图片 等 主键关联
        */
        public String getRecordId(){
			return this.recordId;
        }

        /**
        * 设置 与 视频表 | 店铺图片 等 主键关联
        */
        public void setRecordId(String recordId){
        	this.recordId=recordId;
        }
        /**
        * 获取 图片类型： 1 设备广告，2 产品，3 评价图，4 视频图片
        * @return int 图片类型： 1 设备广告，2 产品，3 评价图，4 视频图片
        */
        public int getDataType(){
			return this.dataType;
        }

        /**
        * 设置 图片类型： 1 设备广告，2 产品，3 评价图，4 视频图片
        */
        public void setDataType(int dataType){
        	this.dataType=dataType;
        }
        /**
        * 获取 类型： 0 图片，1 视频
        * @return int 类型： 0 图片，1 视频
        */
        public int getType(){
			return this.type;
        }

        /**
        * 设置 类型： 0 图片，1 视频
        */
        public void setType(int type){
        	this.type=type;
        }
      
      public interface Property extends GenericPo.Property{
                //图片id，与 s_resource 表 u_id 关联
                 String dataId="dataId";
                //与 视频表 | 店铺图片 等 主键关联
                 String recordId="recordId";
                //图片类型： 1 设备广告，2 产品，3 评价图，4 视频图片
                 String dataType="dataType";
                //类型： 0 图片，1 视频
                 String type="type";
    	}
}