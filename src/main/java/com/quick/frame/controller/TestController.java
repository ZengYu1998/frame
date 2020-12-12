package com.quick.frame.controller;

import com.quick.frame.entity.UserInfo;
import com.quick.frame.service.IUserInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <pre>
 * 对象功能
 * 开发人员：曾煜
 * 创建时间：2020/12/10 21:51
 * </pre>
 **/
@RestController
@RequestMapping
public class TestController {
    @Resource
    private IUserInfoService userInfoService;

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/test_1")
    public String test_1(){
        return "test_1";
    }

    @GetMapping("/test_2")
    public String test_2(){
        return "test_2";
    }


    @ApiOperation("事务测试")
    @GetMapping("/test_3")
    public String test_3(){
        userInfoService.asdfadas();
        return "success";
    }
}
