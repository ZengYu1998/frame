package com.quick.frame.commons.result;

import lombok.Data;

/**
 * @description: 返回给前端包含数据的提示
 * @author: znegyu
 * @create: 2020-11-28 10:59
 **/
@Data
public class ResponseTip<T> extends Tip {

    private T data;

    public ResponseTip(boolean flag) {
        super(flag);
    }

    public ResponseTip(Integer code,String message,T data){
        super(code,message);
        this.data=data;
    }

    public ResponseTip(ResultCode resultCode){
        super(resultCode.getCode(),resultCode.getMessage());
    }
}
