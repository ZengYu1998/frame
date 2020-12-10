package com.quick.frame.commons.result;

import lombok.Data;

public enum ResultCode {

    OPERATION_FAILED(400,"操作失败");


    private Integer code;

    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
