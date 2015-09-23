package com.bbv.javadojo.optional;

/**
 * Created by gro on 23/09/15.
 */
public class ArgsException extends RuntimeException {
    public Object errorMessage() {
        return super.getMessage();
    }
}
