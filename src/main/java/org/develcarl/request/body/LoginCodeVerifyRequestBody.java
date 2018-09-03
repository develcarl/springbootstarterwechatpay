package org.develcarl.request.body;

import org.develcarl.common.constant.CommonConstant;

/**
 * @author yichen
 * @description
 * @date 2018/7/25
 **/
public class LoginCodeVerifyRequestBody{

    private String appid;

    private String secret;

    private String js_code;

    private String grant_type = CommonConstant.DEFAULT_GRANT_TYPE;

    protected LoginCodeVerifyRequestBody(String appid, String secret, String js_code) {
        this.js_code = js_code;
        this.appid = appid;
        this.secret = secret;
    }
}
