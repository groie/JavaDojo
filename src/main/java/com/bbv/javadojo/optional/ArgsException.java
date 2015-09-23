package com.bbv.javadojo.optional;

/**
 * Created by gro on 23/09/15.
 */
public class ArgsException extends RuntimeException {
    public ArgsException(String message) {
        super(message);
    }

    public ArgsException(String message, Exception cause) {
        super(message, cause);
    }

    public Object errorMessage() {
        return super.getMessage();
    }
}
