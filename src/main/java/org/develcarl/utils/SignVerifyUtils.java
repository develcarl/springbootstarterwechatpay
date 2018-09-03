package org.develcarl.utils;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author yichen
 * @description
 * @date 2018/8/8
 **/
public class SignVerifyUtils {

    private static final String SIGN = "sign";

    public static boolean verifySignWhenDataIsXML(String data, String key) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        Map<String, Object> map = xmlMapper.readValue(data, TreeMap.class);
        return verifySignWhenDataIsMap(map, key);
    }

    public static boolean verifySignWhenDataIsEntity(Object data, String key, String sign) throws IllegalAccessException {
        String urlPair = HttpRequestUtils.parseObject2UrlPairWithKey(data, key);
        if (StringUtils.isEmpty(urlPair) || StringUtils.isEmpty(sign)){
            return false;
        }
        return MD5Utils.MD5(urlPair).equals(sign);
    }

    public static boolean verifySignWhenDataIsMap(Map<String, Object> map, String key){
        String urlPair = HttpRequestUtils.parseMap2UrlPairWithKey(map, key);
        String sign = (String) map.get(SIGN);
        if (StringUtils.isEmpty(urlPair) || StringUtils.isEmpty(sign)){
            return false;
        }
        return MD5Utils.MD5(urlPair).equals(sign);
    }

}
