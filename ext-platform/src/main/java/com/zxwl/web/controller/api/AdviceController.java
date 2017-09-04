package com.zxwl.web.controller.api;

import com.zxwl.web.bean.Advice;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.core.utils.WebUtil;
import com.zxwl.web.service.AdviceService;
import com.zxwl.web.service.GenericService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by wuei on 2017/8/25 10:00.
 */

@RestController
@RequestMapping(value = "/api/advice")
@AccessLogger("意见反馈")
public class AdviceController extends GenericController<Advice, String>{

    @Resource
    private AdviceService adviceService;

    @Override
    protected GenericService<Advice, String> getService() {
        return this.adviceService;
    }

    @RequestMapping(value = "/content", method = RequestMethod.POST)
    @AccessLogger("新建")
    @Authorize(action = "C")
    @Transactional
    public ResponseMessage add(@RequestParam("advice") String content){
        if(content == null ||"".equals(content)){
            return ResponseMessage.error("意见内容为空，反馈无效！", 500);
        }
        Advice advice = new Advice();
        advice.setAdvice(content);
        advice.setUserId(WebUtil.getLoginUser().getId());
        advice.setGmtCreate(new Date());
        advice.setStatus(0);
        String id = this.getService().insert(advice);
        if(id == null){
            return ResponseMessage.error("数据保存出错，请稍后重试", 500);
        }
        return ResponseMessage.ok();
    }


}
