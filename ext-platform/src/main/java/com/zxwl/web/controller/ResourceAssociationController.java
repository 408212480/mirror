package com.zxwl.web.controller;

import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.bean.ResourceAssociation;
import org.springframework.web.bind.annotation.RestController;
import com.zxwl.web.service.ResourceAssociationService;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 视频存储控制器
 * Created by generator
 */
@RestController
@RequestMapping(value = "/ResourceAssociation")
@AccessLogger("视频存储")
@Authorize(module = "ResourceAssociation")
public class ResourceAssociationController extends GenericController<ResourceAssociation, String> {

    @Resource
    private  ResourceAssociationService resourceAssociationService;

    @Override
    public  ResourceAssociationService getService() {
        return this.resourceAssociationService;
    }
}
