package com.quick.frame.config.security;

import lombok.Data;

/**
 * @description: 登录模型实体
 * @author: znegyu
 * @create: 2020-12-11 14:47
 **/
@Data
public class LoginBean {
    private String accountNumber;
    private String password;
}
