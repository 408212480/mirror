package com.zxwl.web.service.impl;

import com.zxwl.pay.common.util.str.StringUtils;
import com.zxwl.web.bean.api.PagerGoodsCommentList;
import com.zxwl.web.bean.common.PagerResult;
import com.zxwl.web.bean.common.QueryParam;
import com.zxwl.web.bean.GoodsComment;
import com.zxwl.web.dao.GoodsCommentMapper;
import com.zxwl.web.service.impl.AbstractServiceImpl;
import com.zxwl.web.service.GoodsCommentService;
import com.zxwl.web.util.ResourceUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 商品评价表 服务类实现
 * Created by generator
 */
@Service("goodsCommentService")
public class GoodsCommentServiceImpl extends AbstractServiceImpl<GoodsComment, String> implements GoodsCommentService {

    @Resource
    protected GoodsCommentMapper goodsCommentMapper;

    @Override
    protected GoodsCommentMapper getMapper() {
        return this.goodsCommentMapper;
    }

    @Override
    public String insert(GoodsComment data) {
        return super.insert(data);
    }

    @Override
    public int update(GoodsComment data) {
        return super.update(data);
    }

    @Override
    public int update(List<GoodsComment> data) {
        return super.update(data);
    }

    @Override
    public PagerResult<Map> getPagerGoodsCommentList(PagerGoodsCommentList pagerGoodsCommentList, String goodsId, HttpServletRequest req) {
        PagerResult<Map> pagerResult = new PagerResult<>();
        int total = getMapper().totalComment(goodsId);
        pagerResult.setTotal(total);
        if (total == 0) {
            pagerResult.setData(new ArrayList<>());
        } else {
            List<Map> mapLists = getMapper().commentList(pagerGoodsCommentList);
            if (mapLists != null) {
                for (Map map : mapLists) {
                    String[] cImglist = null;
                    String videoimg = (String) map.get("cimglist");
                    if (!StringUtils.isEmpty(videoimg)) {
                        cImglist = convertStrToArray(videoimg);
                        StringBuffer stringBuffer = new StringBuffer();
                        for (int i = 0; i < cImglist.length; i++) {
                            String img = ResourceUtil.resourceBuildPath(req, String.valueOf(cImglist[i]).trim());
                            stringBuffer.append(img + ",");
                        }
                        map.put("cimglist", stringBuffer);
                    } else {
                        map.put("cimglist", "");
                    }
                    String userImgPath = (String) map.get("userimgpath");
                    if (!StringUtils.isEmpty(userImgPath)) {
                        map.put("userimgpath", ResourceUtil.resourceBuildPath(req, String.valueOf(map.get("userimgpath")).trim()));
                    } else {
                        map.put("userimgpath", "");
                    }
                }
            }
            //根据实际记录数量重新指定分页参数
            pagerResult.setData(mapLists);
        }
        return pagerResult;
    }

    //字符串逗号分割
    public static String[] convertStrToArray(String str) {
        String[] strArray = null;
        strArray = str.split(","); //拆分字符为"," ,然后把结果交给数组strArray
        return strArray;
    }

    @Override
    public int totalGoodsComment(String goodsId) {
        return getMapper().totalComment(goodsId);
    }

    @Override
    public PagerResult<Map> getPagerUserGoodsCommentList(PagerGoodsCommentList pagerGoodsCommentList, String userId, HttpServletRequest req) {
        PagerResult<Map> pagerResult = new PagerResult<>();
        int total = getMapper().totalUserComment(userId);
        pagerResult.setTotal(total);
        if (total == 0) {
            pagerResult.setData(new ArrayList<>());
        } else {
            List<Map> userCommentListLists = getMapper().userCommentList(pagerGoodsCommentList);
            if (userCommentListLists != null) {
                for (Map map : userCommentListLists) {
                    String[] coImglist = null;
                    String cimglistimg = (String) map.get("cimglist");
                    //字符串转换成字符串数组

                    if (!StringUtils.isEmpty(cimglistimg)) {
                        coImglist = convertStrToArray(cimglistimg);
                        StringBuffer stringBuffer = new StringBuffer();
                        for (int i = 0; i < coImglist.length; i++) {
                            String imgUrl = ResourceUtil.resourceBuildPath(req, String.valueOf(coImglist[i]).trim());
                            stringBuffer.append(imgUrl + ",");
                        }
                        map.put("cimglist", stringBuffer);
                    } else {
                        map.put("cimglist", "");
                    }
                    String shopImg = (String) map.get("shopimg");
                    if (!StringUtils.isEmpty(shopImg)) {
                        map.put("shopimg", ResourceUtil.resourceBuildPath(req, String.valueOf(map.get("shopimg")).trim()));
                    } else {
                        map.put("shopimg", "");
                    }
                }
            }
            //根据实际记录数量重新指定分页参数
            pagerResult.setData(userCommentListLists);
        }
        return pagerResult;
    }

    @Override
    public int totalUserGoodsComment(String userId) {
        return 0;
    }

    @Override
    public GoodsComment selectByOrderId(String orderId) {
        return createQuery().where(GoodsComment.Property.orderdId,orderId).single();
    }
}
