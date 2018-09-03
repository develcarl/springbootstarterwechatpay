package org.develcarl.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.develcarl.autoconfigure.WeChatAppProperties;
import org.develcarl.common.constant.FieldConstant;
import org.develcarl.common.constant.RequestStateConstant;
import org.develcarl.common.constant.RequestUrlConstant;
import org.develcarl.exception.LoginException;
import org.develcarl.exception.OrderNotifyException;
import org.develcarl.exception.RequestException;
import org.develcarl.exception.UnifiedOrderException;
import org.develcarl.request.body.LoginCodeVerifyRequestBody;
import org.develcarl.request.body.NotifyOrderRequestBody;
import org.develcarl.request.body.UnifiedOrderRequestBody;
import org.develcarl.response.body.ErrorResponseBody;
import org.develcarl.response.body.LoginResponseBody;
import org.develcarl.response.body.UnifiedOrderResponseBody;
import org.develcarl.response.dto.PaymentDTO;
import org.develcarl.utils.HttpRequestUtils;
import org.develcarl.utils.SignVerifyUtils;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;


/**
 * @author yichen
 * @description
 * @date 2018/7/25
 **/
public class WeChatRequest {

    private WeChatAppProperties weChatAppProperties;

    public WeChatRequest(WeChatAppProperties weChatAppProperties) {
        this.weChatAppProperties = weChatAppProperties;
    }

    /**
     * 登录接口
     * @param loginCodeVerifyRequestBody
     * @return
     * @throws LoginException
     */
    public LoginResponseBody loginCodeVerify(LoginCodeVerifyRequestBody loginCodeVerifyRequestBody) throws LoginException {
        LoginResponseBody success = null;
        try {
            String params = HttpRequestUtils.parseObject2UrlPair(loginCodeVerifyRequestBody);
            String responseStr = Unirest.get(HttpRequestUtils.concatUrlAndParams(RequestUrlConstant.JSCODE2SESSION, params)).asString().getBody();
            JSONObject jsonObject = JSON.parseObject(responseStr);
            if (jsonObject != null){
                if (jsonObject.get(ErrorResponseBody.ERRCODE) != null){
                    throw new LoginException(String.format("login failed %s %s", jsonObject.get(ErrorResponseBody.ERRMSG), jsonObject.get(ErrorResponseBody.ERRCODE)));
                }else {
                    success = JSON.parseObject(responseStr, LoginResponseBody.class);
                    return success;
                }
            }
        }catch (IllegalAccessException | UnirestException ex){
            throw new LoginException(ex.getMessage(), ex);
        }
        return success;
    }

    /**
     * 统一下单
     * @return
     */
    public PaymentDTO unifiedOrder(UnifiedOrderRequestBody unifiedOrderRequestBody) throws UnifiedOrderException, RequestException {
        PaymentDTO paymentDTO;
        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(unifiedOrderRequestBody);
            String unifiedResponse = Unirest.post(RequestUrlConstant.UNIFIED_ORDER).body(xml).asString().getBody();
            UnifiedOrderResponseBody responseBody = xmlMapper.readValue(unifiedResponse, UnifiedOrderResponseBody.class);
            if (RequestStateConstant.FAIL.equals(responseBody.getReturn_code())){
                throw new RequestException(String.format("通信失败 %s", responseBody.getReturn_msg()));
            }else if (RequestStateConstant.FAIL.equals(responseBody.getResult_code())){
                throw new UnifiedOrderException(String.format("业务失败，请对照err_code查看错误信息 https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=9_1 %s:%s", responseBody.getErr_code(), responseBody.getErr_code_des()));
            }else {
                if (SignVerifyUtils.verifySignWhenDataIsEntity(responseBody, weChatAppProperties.getKey(), responseBody.getSign())){
                    paymentDTO = new PaymentDTO(weChatAppProperties.getAppid(),(System.currentTimeMillis()/1000) + "", responseBody.getPrepay_id(), weChatAppProperties.getKey());
                }else {
                    throw new UnifiedOrderException("返回签名匹配异常！");
                }
            }
        }catch (IOException | IllegalAccessException | UnirestException ex) {
            throw new UnifiedOrderException("出现异常！", ex);
        }
        return paymentDTO;
    }

    /**
     * 微信支付回调方法
     */
    public NotifyOrderRequestBody notifyOrder(String xml) throws OrderNotifyException {
        NotifyOrderRequestBody requestBody = null;
        try {
            XmlMapper xmlMapper = new XmlMapper();
            Map<String, Object> map = xmlMapper.readValue(xml, TreeMap.class);

            if (RequestStateConstant.FAIL.equals(map.get(FieldConstant.RETURN_CODE))){
                throw new OrderNotifyException((String) map.get(FieldConstant.RETURN_MSG));
            }

            boolean signIsRight = SignVerifyUtils.verifySignWhenDataIsMap(map, weChatAppProperties.getKey());

            if (signIsRight){
                requestBody = xmlMapper.readValue(xml, NotifyOrderRequestBody.class);
            }else {
                throw new OrderNotifyException("签名异常");
            }
        } catch (IOException e){
            throw new OrderNotifyException("订单回调异常 xml解析异常", e);
        }
        return requestBody;
    }
}
