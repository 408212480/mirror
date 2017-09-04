package com.zxwl.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/8/30.
 */

@Component
@ConfigurationProperties(prefix = "zxwl.sms")
public class SMSConfig {
    private String ip;
    private String username;
    private String password;

    public String getIp() {
        return "http://"+ip+":8860";
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
