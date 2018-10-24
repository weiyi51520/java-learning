package com.wey.juc_2.sempahore.current_limiting;

/**
 * @author Yale.Wei
 * @date 2018/10/24 15:30
 */
public class SemaphoreGetException extends RuntimeException{

    private static final long serialVersionUID = -4528280099596208630L;

    public SemaphoreGetException() {
    }

    public SemaphoreGetException(String message) {
        super(message);
    }

    public SemaphoreGetException(String message, Throwable cause) {
        super(message, cause);
    }

    public SemaphoreGetException(Throwable cause) {
        super(cause);
    }

    public SemaphoreGetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
