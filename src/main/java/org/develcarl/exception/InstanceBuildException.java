package org.develcarl.exception;

/**
 * @author yichen
 * @description
 * @date 2018/8/8
 **/
public class InstanceBuildException extends WeChatPayException{

    public InstanceBuildException() {
        super();
    }

    public InstanceBuildException(String message) {
        super(message);
    }

    public InstanceBuildException(String message, Throwable cause) {
        super(message, cause);
    }

    public InstanceBuildException(Throwable cause) {
        super(cause);
    }
}
