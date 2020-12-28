package com.cyw.info_submit.exception;

/**
 * 自定义服务器异常类
 */
public class ServerErrorException extends RuntimeException {
    public ServerErrorException(String message) {
        super(message);
    }

    public ServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
