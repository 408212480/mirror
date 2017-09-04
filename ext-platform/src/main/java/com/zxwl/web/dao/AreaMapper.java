package com.zxwl.web.dao;

import com.zxwl.web.bean.Area;
import com.zxwl.web.dao.GenericMapper;

import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 */
public interface AreaMapper extends GenericMapper<Area,String> {

    List<Area> selectByParentId(String id);

}
