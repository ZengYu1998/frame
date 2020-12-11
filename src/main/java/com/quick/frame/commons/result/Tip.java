package com.quick.frame.commons.result;

import lombok.Data;

/**
 * @description: 返回给前端的提示
 * @author: znegyu
 * @create: 2020-11-28 10:50
 **/
@Data
public class Tip {
    protected int code;
    protected String message;

    public Tip() {

    }

    public Tip(boolean flag) {
        if (flag) {
            this.code = 200;
            this.message = "操作成功";
        } else {
            this.code = ResultCode.OPERATION_FAILED.getCode();
            this.message = ResultCode.OPERATION_FAILED.getMessage();
        }
    }


    public Tip(Integer code, String message) {
        this.code=code;
        this.message=message;
    }
}
