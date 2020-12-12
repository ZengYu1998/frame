package com.quick.frame.controller;


import com.quick.frame.commons.other.BaseController;
import com.quick.frame.commons.result.Tip;
import com.quick.frame.entity.UserRole;
import com.quick.frame.service.IUserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("用户角色接口")
@RestController
@RequestMapping("/userRole")
public class UserRoleController extends BaseController {
    @Resource
    private IUserRoleService userRoleService;

    @ApiOperation(value = "为指定用户添加角色",notes  = "为指定用户添加角色")
    @RequestMapping("/addRoleToUser")
    public Tip addRoleToUser(@RequestBody UserRole userRole){
        return userRoleService.addRoleToUser(userRole,getUserAccountNumber());
    }
}

