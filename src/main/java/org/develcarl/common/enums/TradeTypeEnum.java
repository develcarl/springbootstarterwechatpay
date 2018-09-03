package org.develcarl.common.enums;

/**
 * @author yichen
 * @description
 * @date 2018/7/30
 **/
public enum TradeTypeEnum {
    /**
     * 公众号支付
     */
    JSAPI("JSAPI"),

    /**
     * 原生扫码支付
     */
    NATIVE("NATIVE"),

    /**
     * app支付
     */
    APP("APP");

    private String type;

    TradeTypeEnum(String type) {

        this.type = type;

    }

    public String get(){
        return this.type;
    }
}
