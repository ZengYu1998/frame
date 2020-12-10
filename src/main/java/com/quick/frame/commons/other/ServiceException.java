package com.quick.frame.commons.other;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @description: 自定义异常
 * @author: znegyu
 * @create: 2020-11-24 09:24
 **/
@Data
@AllArgsConstructor
public class ServiceException extends RuntimeException {

    private Integer code;
    private String message;
}
