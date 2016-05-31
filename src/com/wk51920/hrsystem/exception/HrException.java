package com.wk51920.hrsystem.exception;

/**
 * Created by wk51920 on 16/5/30.
 */
public class HrException extends RuntimeException {
    public HrException(){}

    public HrException(String msg){
        super(msg);
    }
}
