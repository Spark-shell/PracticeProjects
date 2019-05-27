package com.gsau.weichat.weichat_server.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaUserServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.gsau.weichat.weichat_server.config.WxMaConfiguration;
import com.gsau.weichat.weichat_server.config.WxMaProperties;
import com.gsau.weichat.weichat_server.utils.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("weiChat")
public class WeiChatLoginController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private WxMaProperties properties;

    @GetMapping("/getUserId")
    public String getCode(@RequestParam("code") String code,String encryptedData) throws WxErrorException {
        if (StringUtils.isBlank(code)) {
            return "Code";
        }

        final WxMaService wxService = WxMaConfiguration.getMaService("wxff2d688511171c96");
        try {

            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            this.logger.info(session.getSessionKey());
            this.logger.info(session.getOpenid());


            return JsonUtils.toJson(session);
        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            return e.toString();
        }
    }

    @GetMapping("check_student")
    public String check_student(@RequestParam("userid") String userid) {
        String code1 = userid;
        System.out.println("userid:" + userid);
        return code1;
    }
    /**
     * 获取用户信息接口
     */
    @GetMapping("/info")
    public String info(String sessionKey,String signature, String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService("wxff2d688511171c96");
        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }
        // 解密用户信息
        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
        WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);
        System.out.println("userInfo:" + JsonUtils.toJson(userInfo));
        return JsonUtils.toJson(userInfo);
    }

    /**
     * <pre>
     * 获取用户绑定手机号信息
     * </pre>
     */
    @GetMapping("phone")
    public String phone(@PathVariable String appid, String sessionKey, String signature, String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }
        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);

        return JsonUtils.toJson(phoneNoInfo);
    }
}
