package com.quick.frame.config.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quick.frame.dao.PermissionDao;
import com.quick.frame.dao.RoleDao;
import com.quick.frame.dao.UserInfoDao;
import com.quick.frame.entity.Permission;
import com.quick.frame.entity.Role;
import com.quick.frame.entity.UserInfo;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 处理用户登录认证逻辑
 * @author: znegyu
 * @create: 2020-12-08 10:38
 **/
@Component
public class DefaultUserDetailsService implements UserDetailsService {

    @Resource
    private UserInfoDao userInfoDao;
    @Resource
    private RoleDao roleDao;
    @Resource
    private PermissionDao permissionDao;

    /**
     * 通过账号从数据库查询出当前登录用户的信息,返回一个UserDetails
     * (Spring-Security需要根据这个返回的对象来判断用户是否登录成功!)
     * @param accountNumber -登录用户的账号
     * @return -返回一个UserDetails对象
     * @throws UsernameNotFoundException -用户不存在
     */
    @Override
    public UserDetails loadUserByUsername(String accountNumber) throws UsernameNotFoundException {
        //1.获取用户信息
        if(accountNumber==null || accountNumber.trim().equals(""))
            throw new UsernameNotFoundException("账号为空!");
        UserInfo userInfo = userInfoDao.getUserInfoInAccountNumber(accountNumber);
        if(userInfo==null)
            throw new UsernameNotFoundException("账号不存在!");
        //2.获取用户角色信息
        List<Role> roleList = roleDao.getRoleListInAccountNumber(accountNumber);
        //角色也是一种特殊的权限
        List<String> allPermission= roleList.stream().map(T->"ROLE_"+T.getRoleName()).collect(Collectors.toList());
        //3.获取用户权限信息
        List<Permission> permissionList = permissionDao.getPermissionListInAccountNumber(accountNumber);
        //角色权限和普通权限合并
        allPermission.addAll(
                permissionList.stream().map(Permission::getPermissionName).collect(Collectors.toList())
        );
        //返回用户信息和用户权限合集给security管理
        return new LoginUserDetails(userInfo,
                AuthorityUtils.commaSeparatedStringToAuthorityList(
                String.join(",",allPermission))
        );
    }
}
