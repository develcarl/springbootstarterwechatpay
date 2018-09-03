package org.develcarl.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author yichen
 * @description
 * @date 2018/8/8
 **/
public class MD5Utils {

    public static String MD5(String source){
        return DigestUtils.md5Hex(source).toUpperCase();
    }

}
