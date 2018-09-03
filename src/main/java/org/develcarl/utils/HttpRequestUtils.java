package org.develcarl.utils;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author yichen
 * @description
 * @date 2018/7/25
 **/
public class HttpRequestUtils {

    private static final String EQUAL = "=";

    private static final String AND = "&";

    private static final String QUEST = "?";

    private static final String AND_KEY_EQUALS = "&key=";

    private static final String PACKAGE_STR = "packageStr";

    private static final String SIGN = "sign";

    /**
     * 将实体转换成url键值对
     * @param o
     * @return
     * @throws IllegalAccessException
     */
    public static String parseObject2UrlPair(Object o) throws IllegalAccessException {
        Class clazz = o.getClass();
        Field fields[] = clazz.getDeclaredFields();
        Map<String, Object> paramsMap = new TreeMap<String, Object>();
        for (Field f : fields){
            f.setAccessible(true);
            String name = f.getName();
            Object value = f.get(o);
            if (value != null){
                paramsMap.put(name.equals(PACKAGE_STR) ? name.replaceAll("Str", "") : name, value);
            }
        }

        return parseMap2UrlPair(paramsMap);
    }

    /**
     * 转换map为url键值对
     * @param paramsMap
     * @return
     */
    public static String parseMap2UrlPair(Map<String, Object> paramsMap){
        Iterator<Map.Entry<String, Object>> i = paramsMap.entrySet().iterator();
        StringBuilder builder = new StringBuilder();
        while (i.hasNext()){
            Map.Entry<String, Object> entry = i.next();
            if (entry.getKey().equals(SIGN)){
                continue;
            }
            builder.append(entry.getKey()).append(EQUAL).append(entry.getValue()).append(AND);
        }

        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    /**
     * 拼接url和url键值对参数
     * @param url
     * @param params
     * @return
     */
    public static String concatUrlAndParams(String url, String params){
        return new StringBuilder().append(url).append(QUEST).append(params).toString();
    }

    /**
     * 拼接url键值对和key
     * @param o
     * @param key
     * @return
     * @throws IllegalAccessException
     */
    public static String parseObject2UrlPairWithKey(Object o, String key) throws IllegalAccessException {
        String params = parseObject2UrlPair(o);
        return new StringBuilder().append(params).append(AND_KEY_EQUALS).append(key).toString();
    }

    /**
     * 拼接map转成的url键值对和key
     * @param paramsMap
     * @param key
     * @return
     */
    public static String parseMap2UrlPairWithKey(Map<String, Object> paramsMap, String key){
        String params = parseMap2UrlPair(paramsMap);
        return new StringBuilder().append(params).append(AND_KEY_EQUALS).append(key).toString();
    }

}
