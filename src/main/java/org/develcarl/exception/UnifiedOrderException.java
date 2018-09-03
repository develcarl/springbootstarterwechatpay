package org.develcarl.exception;

/**
 * @author yichen
 * @description
 * @date 2018/8/8
 **/
public class UnifiedOrderException extends WeChatPayException{

    public UnifiedOrderException() {
        super();
    }

    public UnifiedOrderException(String message) {
        super(message);
    }

    public UnifiedOrderException(Throwable cause) {
        super(cause);
    }

    public UnifiedOrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
