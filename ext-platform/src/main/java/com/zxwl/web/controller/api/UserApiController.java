package com.zxwl.web.controller.api;

import com.google.common.collect.ImmutableMap;
import com.zxwl.pay.common.util.str.StringUtils;
import com.zxwl.web.bean.MetaDataRel;
import com.zxwl.web.bean.UserInfo;
import com.zxwl.web.bean.po.user.User;
import com.zxwl.web.controller.GenericController;
import com.zxwl.web.core.authorize.annotation.Authorize;
import com.zxwl.web.core.logger.annotation.AccessLogger;
import com.zxwl.web.core.message.ResponseMessage;
import com.zxwl.web.core.utils.RandomUtil;
import com.zxwl.web.core.utils.WebUtil;
import com.zxwl.web.service.GenericService;
import com.zxwl.web.service.MetaDataRelService;
import com.zxwl.web.service.UserInfoService;
import com.zxwl.web.service.resource.FileService;
import com.zxwl.web.service.user.UserService;
import com.zxwl.web.util.SMSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.zxwl.web.bean.po.GenericPo.createUID;


/**
 * Created by Administrator on 2017/8/7.
 */
@RestController
@RequestMapping(value = "/api/userInfo")
@AccessLogger("移动端用户管理")
//@Authorize(module = "user")
public class UserApiController extends GenericController<User, String> {

    Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0,5-9]))\\d{8}$");

    @Resource
    private UserService userService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private FileService fileService;
    @Resource
    private MetaDataRelService metaDataRelService;


    @Autowired
    private SMSClient smsClient;
    /**
     * 获取此Controller需要的服务类,由子类实现
     *
     * @return 通用服务类
     */
    @Override
    protected GenericService<User, String> getService() {
        return this.userService;
    }

    /**
     * 请求添加数据，请求必须以POST方式
     *
     * @param user 请求添加的对象
     * @return 被添加数据的主键值
     * @throws ValidationException 验证数据格式错误
     */
    @RequestMapping(method = RequestMethod.POST)
    @AccessLogger("新增")
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public ResponseMessage add(@RequestBody User user) {
        if (StringUtils.isBlank(user.getPhone()))
            return ResponseMessage.error("手机号码不能为空");

        return ResponseMessage.created(userInfoService.insertApiUser(user));
    }

    @RequestMapping(value = "/check/{code}", method = RequestMethod.GET)
    @AccessLogger("校验短信验证码")
    public ResponseMessage verifySMS(@PathVariable("code") String code,HttpServletRequest req) {
        String sCode = String.valueOf(req.getSession().getAttribute("verifyCode"));
        String telephone = String.valueOf(req.getSession().getAttribute("telephone"));
        if (StringUtils.isEmpty(sCode) || !sCode.equals(code.trim())) {
            return ResponseMessage.error("验证码错误");
        }
        if (StringUtils.isEmpty(telephone)) {
            return ResponseMessage.error("超时，请重新获取验证码");
        }
        return ResponseMessage.ok();
    }


    @RequestMapping(value = "/setPaymentPassword",method = RequestMethod.PUT)
    @AccessLogger("设置支付密码")
    @Authorize(action = "U")
    public ResponseMessage setPaymentPassword(UserInfo userInfo){
        userInfo.setUserId(WebUtil.getLoginUser().getId());
        return  ResponseMessage.ok(userInfoService.setPaymentPassword(userInfo));
    }


    @RequestMapping(value = "/name/{newName}",method = RequestMethod.PUT)
    @AccessLogger("设置昵称")
    @Authorize(action = "U")
    public ResponseMessage setName(@PathVariable("newName") String name){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(WebUtil.getLoginUser().getId());
        userInfo.setName(name);
        return  ResponseMessage.ok(userInfoService.setName(userInfo));
    }

    @RequestMapping(value = "/setImg",method = RequestMethod.POST)
    @AccessLogger("设置头像")
    @Authorize(action = "U")
    public ResponseMessage setImg(@RequestParam("file") MultipartFile file) throws IOException {
        UserInfo userInfo=new UserInfo();
        String fileName = file.getOriginalFilename();
        String recordId=createUID();
        MetaDataRel metaDataRel=new MetaDataRel();
        metaDataRel.setDataId(fileService.saveFile(file.getInputStream(), fileName).getId());
        metaDataRel.setRecordId(recordId);
        metaDataRelService.insert(metaDataRel);
        userInfo.setUserId(WebUtil.getLoginUser().getId());
        userInfo.setAvatarId(recordId);
        return  ResponseMessage.ok(userInfoService.setImg(userInfo));
    }

    /**
     * 向服务端获取 短信验证码
     *
     * @param telephone 接收短信验证码的手机号
     * @param req
     * @return 返回 json 消息对象
     */
    @RequestMapping(value = "/sms/{telephone}", method = RequestMethod.GET)
    @AccessLogger("获取短信验证码")
    public ResponseMessage pushSMS(@PathVariable("telephone") String telephone, HttpServletRequest req) throws IOException {
        // SMSClient sms=SMSClient.getClient();
        // 验证手机号格式是否正确
        Matcher m = p.matcher(telephone);
        if (!m.matches()) {
            return ResponseMessage.error("手机号格式不正确");
        }
        // 生成随机的 6 位数字验证码
        String verifyCode = RandomUtil.randomNumber(4);
        String content="尊贵的用户您好！您的验证码为："+verifyCode+"【知晓物联】";
        // 调用短信接口发送短信到手机号

//        smsClient.sendSms(telephone, content);
        req.getSession().setAttribute("verifyCode", verifyCode);
        req.getSession().setAttribute("telephone", telephone);
        return ResponseMessage.ok(ImmutableMap.of("verifyCode", verifyCode));
    }

    /**
     * 校验客户端短信验证码
     * <br/> 校验短信验证码是否正确
     * <br/> 验证码正确时，获取账户的密码
     * <br/> 如果请求验证码的手机号没有注册账户, 则自动为该手机号注册账号
     * <br/> 获取用户信息，并返回 账户密码 给客户端缓存做接口登陆信息校验
     *
     * @param code 验证码
     * @param req
     * @param res
     * @return 返回 json 消息对象
     */
    @RequestMapping(value = "/verify/{code}", method = RequestMethod.GET)
    @AccessLogger("校验 SMS 验证码")
    public ResponseMessage verifyCode(@PathVariable("code") String code, HttpServletRequest req, HttpServletResponse res) {

        String sCode = String.valueOf(req.getSession().getAttribute("verifyCode"));
        String telephone = String.valueOf(req.getSession().getAttribute("telephone"));

        if (StringUtils.isEmpty(sCode) || !sCode.equals(code.trim())) {
            return ResponseMessage.error("验证码错误");
        }

        if (StringUtils.isEmpty(telephone)) {
            return ResponseMessage.error("超时，请重新获取验证码");
        }

        Map<String, Object> data = new HashMap<>();

        User user = userService.selectByUserName(telephone.trim());
        if (user == null) {
            String password = RandomUtil.randomChar(6);

            user = new User();
            user.setName(telephone);
            user.setUsername(telephone);
            user.setPassword(password);
            user.setPhone(telephone);

            userInfoService.insertApiUser(user);


            data.put("password", password);
        } else {
            data.put("password", user.getPassword());
        }

        req.getSession().setAttribute("verifyCode", null);
        req.getSession().setAttribute("telephone", null);

        return ResponseMessage.ok(data);
    }
}
