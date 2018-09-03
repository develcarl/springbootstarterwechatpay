package org.develcarl.exception;

/**
 * @author yichen
 * @description
 * @date 2018/7/26
 **/
public class LoginException extends WeChatPayException {

    public LoginException(){
        super();
    }

    public LoginException(String msg){
        super(msg);
    }

    public LoginException(String msg, Throwable cause){
        super(msg, cause);
    }
}
