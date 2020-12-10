package com.quick.frame.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
