package com.zxwl.web.util;

import com.zxwl.web.bean.Area;
import com.zxwl.web.bean.Shop;
import com.zxwl.web.dao.AreaMapper;
import com.zxwl.web.dao.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuei on 2017/8/15.
 */
@Service
public class AreaShopUtil{


    //把选中节点下的所有shopid都找出来
    public static ArrayList<String> findAllShopByAreaId(String areaId, AreaMapper areaMapper, ShopMapper shopMapper){
        ArrayList<String> shopIds = new ArrayList<>();
        if(areaId == null || "".equals(areaId)){
            return shopIds;
        }
        if(!"area_tree".equals(areaId)){    //如果area_tree没有选中的节点，则shopId为“area_tree",此时应该返回所有设备，故不对param做处理
            Area area = areaMapper.selectByPk(areaId);  //如果shopId是店铺id，则area不为空
            if(area == null){   //说明传入的是店铺id
                shopIds.add(areaId);
            }
            else{       //处理传入shopId实际为区域id的情况，需要把区域和子区域内所有店铺设备id都查出来
                ArrayList<String> childrenAreaId = new ArrayList<>();
                childrenAreaId.add(areaId);
                getChildAreaId(childrenAreaId, areaId, areaMapper);
                for(String aId:childrenAreaId){
                    List<Shop> shops = shopMapper.selectByAreaId(aId);
                    for(Shop shop:shops){
                        //把条件加入查询条件
                        shopIds.add(shop.getId());
                    }
                }
            }
        }
        return shopIds;
    }


    //获取所有
    public static void getChildAreaId(List<String> areaIds, String areaId, AreaMapper areaMapper){
        List<Area> areaList = areaMapper.selectByParentId(areaId);
        if(areaList == null){
            return ;
        }
        else{
            for(Area area:areaList){
                String id = area.getId();
                areaIds.add(id);
                getChildAreaId(areaIds, id, areaMapper);
            }
        }
    }
}
