package org.develcarl.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yichen
 * @description
 * @date 2018/7/25
 **/
@ConfigurationProperties(prefix = WeChatAppProperties.PROPERTIES_PREFIX)
public class WeChatAppProperties {

    public static final String PROPERTIES_PREFIX = "wechat";

    private String appid;

    private String secret;

    private String mch_id;

    private String key;

    private String notify_url;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }
}
