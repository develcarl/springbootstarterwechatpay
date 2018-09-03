package org.develcarl.exception;

/**
 * @author yichen
 * @description
 * @date 2018/8/8
 **/
public class RequestException extends WeChatPayException{

    public RequestException() {
        super();
    }

    public RequestException(String message) {
        super(message);
    }
}
