package org.develcarl.exception;

/**
 * @author yichen
 * @description
 * @date 2018/8/9
 **/
public class OrderNotifyException extends WeChatPayException{

    public OrderNotifyException() {
        super();
    }

    public OrderNotifyException(String message) {
        super(message);
    }

    public OrderNotifyException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderNotifyException(Throwable cause) {
        super(cause);
    }

    public OrderNotifyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
