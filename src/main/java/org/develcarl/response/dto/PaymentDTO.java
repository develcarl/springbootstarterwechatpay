package org.develcarl.response.dto;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.codec.digest.DigestUtils;
import org.develcarl.common.constant.CommonConstant;
import org.develcarl.utils.HttpRequestUtils;
import org.develcarl.utils.NonceStringUtils;

/**
 * @author yichen
 * @description
 * @date 2018/7/31
 **/
public class PaymentDTO {

    private String appId;

    private String timeStamp;

    private String nonceStr;

    @JSONField(name = "package")
    private String packageStr;

    private String signType;

    private String sign;

    public PaymentDTO(String appId, String timeStamp, String prepare_id, String key) throws IllegalAccessException {
        this.appId = appId;
        this.timeStamp = timeStamp;
        this.nonceStr = NonceStringUtils.GenerateNonceString();
        this.packageStr = CommonConstant.PREPAY_ID + prepare_id;
        this.signType = CommonConstant.MD5;
        this.sign = DigestUtils.md5Hex(HttpRequestUtils.parseObject2UrlPairWithKey(this, key));
    }

    public String getAppId() {
        return appId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public String getPackageStr() {
        return packageStr;
    }

    public String getSignType() {
        return signType;
    }

    public String getSign() {
        return sign;
    }
}
