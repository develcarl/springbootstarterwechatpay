package org.develcarl.request.body;

import org.develcarl.autoconfigure.WeChatAppProperties;
import org.develcarl.common.enums.TradeTypeEnum;
import org.develcarl.exception.InstanceBuildException;
import org.develcarl.utils.NonceStringUtils;

/**
 * @author yichen
 * @description
 * @date 2018/7/28
 **/
public class RequestBodyHelper {

    private WeChatAppProperties properties;

    public RequestBodyHelper(WeChatAppProperties properties) {
        this.properties = properties;
    }

    public LoginCodeVerifyRequestBody getLoginCodeVerifyRequsetBodyInstance(String js_code){
        return new LoginCodeVerifyRequestBody(properties.getAppid(), properties.getSecret(), js_code);
    }

    public UnifiedOrderRequestBody getUnifiedOrderRequestBodyInstance(String open_id, TradeTypeEnum tradeTypeEnum, int total_fee, String ip, String detail, String out_trade_no, String itemBody) throws InstanceBuildException {
        UnifiedOrderRequestBody body = new UnifiedOrderRequestBody(properties.getAppid(), properties.getMch_id(), properties.getNotify_url(), open_id);
        body.setNonce_str(NonceStringUtils.GenerateNonceString());
        body.setSign_type("MD5");
        body.setTrade_type(tradeTypeEnum.get());
        body.setTotal_fee(total_fee);
        body.setSpbill_create_ip(ip);
        body.setDetail(detail);
        body.setOut_trade_no(out_trade_no);
        body.setBody(itemBody);
        try {
            body.sign(properties.getKey());
        } catch (IllegalAccessException e) {
            throw new InstanceBuildException("实体创建异常", e);
        }
        return body;
    }

    public UnifiedOrderRequestBody getUnifiedOrderRequestBodyInstance(String open_id, TradeTypeEnum tradeTypeEnum, int total_fee, String ip, String detail, String out_trade_no, String itemBody, String time_start, String time_expire) throws InstanceBuildException {
        UnifiedOrderRequestBody body = getUnifiedOrderRequestBodyInstance(open_id, tradeTypeEnum, total_fee, ip, detail, out_trade_no, itemBody);
        body.setTime_start(time_start);
        body.setTime_expire(time_expire);
        try {
            body.sign(properties.getKey());
        } catch (IllegalAccessException e) {
            throw new InstanceBuildException("实体创建异常", e);
        }
        return body;
    }
}
