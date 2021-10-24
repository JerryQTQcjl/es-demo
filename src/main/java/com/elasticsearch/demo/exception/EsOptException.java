package com.elasticsearch.demo.exception;

/**
 * @author jerry chan
 * @date 2021/10/13
 */
public class EsOptException extends RuntimeException {
    private static final long serialVersionUID = -5796957700341519686L;

    public EsOptException() {
    }

    public EsOptException(String message) {
        super(message);
    }

    public EsOptException(Throwable e) {
        super(e);
    }
}
