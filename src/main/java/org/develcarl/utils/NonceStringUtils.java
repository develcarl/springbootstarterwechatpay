package org.develcarl.utils;

import java.util.UUID;

/**
 * @author yichen
 * @description
 * @date 2018/7/30
 **/
public class NonceStringUtils {

    public static String GenerateNonceString(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
