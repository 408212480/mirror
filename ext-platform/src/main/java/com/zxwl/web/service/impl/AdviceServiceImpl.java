package com.zxwl.web.service.impl;

import com.zxwl.web.bean.Advice;
import com.zxwl.web.dao.AdviceMapper;
import com.zxwl.web.dao.GenericMapper;
import com.zxwl.web.service.AdviceService;
import com.zxwl.web.service.GenericService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/8/25.
 */
@Service("adviceService")
class AdviceServiceImpl extends AbstractServiceImpl<Advice, String> implements AdviceService {

    @Resource
    private AdviceMapper adviceMapper;

    @Override
    protected GenericMapper<Advice, String> getMapper() {
        return this.adviceMapper;
    }

}
