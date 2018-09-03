package org.develcarl.exception;

/**
 * @author yichen
 * @description
 * @date 2018/8/8
 **/
public class WeChatPayException extends Exception{

    public WeChatPayException() {
    }

    public WeChatPayException(String message) {
        super(message);
    }

    public WeChatPayException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeChatPayException(Throwable cause) {
        super(cause);
    }

    public WeChatPayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
