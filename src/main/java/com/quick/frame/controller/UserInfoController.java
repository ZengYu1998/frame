package com.quick.frame.controller;


import com.quick.frame.commons.result.ResponseTip;
import com.quick.frame.config.security.LoginBean;
import com.quick.frame.service.IUserInfoService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zengyu
 * @since 2020-12-08
 */
@RestController
@RequestMapping("/api/userInfo")
public class UserInfoController {

    @Resource
    private IUserInfoService userInfoService;


    @PostMapping("/login")
    public ResponseTip<String> login(@RequestBody LoginBean loginBean){
        return userInfoService.authentication(loginBean);
    }

}

