import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.develcarl.autoconfigure.WeChatAppProperties;
import org.develcarl.common.enums.TradeTypeEnum;
import org.develcarl.exception.OrderNotifyException;
import org.develcarl.request.WeChatRequest;
import org.develcarl.request.body.RequestBodyHelper;
import org.develcarl.request.body.UnifiedOrderRequestBody;
import org.develcarl.response.body.UnifiedOrderResponseBody;
import org.develcarl.response.dto.PaymentDTO;
import org.develcarl.utils.SignVerifyUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author yichen
 * @description
 * @date 2018/7/30
 **/
public class UnifiedOrderTest {

    private UnifiedOrderRequestBody body;

    private WeChatAppProperties properties;

    private WeChatRequest request;

    private RequestBodyHelper helper;

    private String openid;

    @Before
    public void init(){
        properties = new WeChatAppProperties();
        properties.setAppid("wx3c1e249f4d1020b1");
        properties.setSecret("1ddb73ef999186a61e23d211a280279e");
        properties.setKey("5E8D9596D76B94B093DE4F528067F3C8");
        properties.setMch_id("1510572111");
        properties.setNotify_url("http://www.baidu.com");

        request = new WeChatRequest(properties);
        helper = new RequestBodyHelper(properties);

        openid = "oz2lo5P_dECzIlSPf-epRkT74sQM";
    }

    @Test
    public void testUnifiedOrder(){
//        try {
//            UnifiedOrderRequestBody body = helper.getUnifiedOrderRequestBodyInstance(openid, TradeTypeEnum.JSAPI, 1, "101.37.31.60", "20180000001", "201800000002", "body");
//            System.out.println(body.getSign());
////            UnifiedOrderResponseBody responseBody = request.unifiedOrder(body);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testPaymentString(){
        String xml = "<xml><return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "<return_msg><![CDATA[OK]]></return_msg>\n" +
                "<appid><![CDATA[wx3c1e249f4d1020b1]]></appid>\n" +
                "<mch_id><![CDATA[1510572111]]></mch_id>\n" +
                "<nonce_str><![CDATA[AtWr3oKeVc5S6CCd]]></nonce_str>\n" +
                "<sign><![CDATA[48DAE61D36DCE1D1C5927DC0E82A1577]]></sign>\n" +
                "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "<prepay_id><![CDATA[wx011340116725914bc3e60bb43218121100]]></prepay_id>\n" +
                "<trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "</xml>";

        XmlMapper mapper = new XmlMapper();
        try {
            UnifiedOrderResponseBody body = mapper.readValue(xml, UnifiedOrderResponseBody.class);

            System.out.println(SignVerifyUtils.verifySignWhenDataIsEntity(body, properties.getKey(), body.getSign()));

            PaymentDTO paymentDTO = new PaymentDTO(properties.getAppid(),(System.currentTimeMillis()/1000) + "", body.getPrepay_id(), properties.getKey());
            System.out.println(JSON.toJSON(paymentDTO));
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testSignVerify() throws IOException, OrderNotifyException {
        String xml = "<xml><appid><![CDATA[wx3c1e249f4d1020b1]]></appid>\n" +
                "<bank_type><![CDATA[CFT]]></bank_type>\n" +
                "<cash_fee><![CDATA[1]]></cash_fee>\n" +
                "<fee_type><![CDATA[CNY]]></fee_type>\n" +
                "<is_subscribe><![CDATA[N]]></is_subscribe>\n" +
                "<mch_id><![CDATA[1510572111]]></mch_id>\n" +
                "<nonce_str><![CDATA[c240aeecd69e4452862c929e73d724c0]]></nonce_str>\n" +
                "<openid><![CDATA[oz2lo5P_dECzIlSPf-epRkT74sQM]]></openid>\n" +
                "<out_trade_no><![CDATA[dd656bed72fa49ce80bb94824cc31aed]]></out_trade_no>\n" +
                "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "<return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "<sign><![CDATA[09940F6BEE79E73F44B3BC3BC3103A98]]></sign>\n" +
                "<time_end><![CDATA[20180809173638]]></time_end>\n" +
                "<total_fee>1</total_fee>\n" +
                "<trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "<transaction_id><![CDATA[4200000141201808094623878210]]></transaction_id>\n" +
                "</xml>";

//        System.out.println(SignVerifyUtils.verifySignWhenDataIsXML(xml, properties.getKey()));
        request.notifyOrder(xml);
    }

}
