package com.quick.frame.commons.other;

import com.quick.frame.commons.util.JwtUtil;

import javax.annotation.Resource;

/**
 * @description: 控制器父类, 包含常用方法和返回封装值
 * @author: znegyu
 * @create: 2020-11-26 15:06
 **/
public class BaseController {
    @Resource
    private JwtUtil jwtUtil;

    protected String getUserAccountNumber(){
        return jwtUtil.getUniqueIdentifier();
    }

}
