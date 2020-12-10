package com.quick.frame.config.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quick.frame.dao.UserInfoDao;
import com.quick.frame.entity.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 处理用户登录认证逻辑
 * @author: znegyu
 * @create: 2020-12-08 10:38
 **/
@Component
public class DefaultUserDetailsService implements UserDetailsService {

    @Resource
    private UserInfoDao userInfoDao;

    /**
     * 通过账号从数据库查询出当前登录用户的信息,返回一个UserDetails
     * (Spring-Security需要根据这个返回的对象来判断用户是否登录成功!)
     * @param accountNumber -登录用户的账号
     * @return -返回一个UserDetails对象
     * @throws UsernameNotFoundException -用户不存在
     */
    @Override
    public UserDetails loadUserByUsername(String accountNumber) throws UsernameNotFoundException {
        if(accountNumber==null || accountNumber.trim().equals(""))
            throw new UsernameNotFoundException("账号为空!");
        UserInfo userInfo = userInfoDao.getUserInfoInAccountNumber(accountNumber);
        if(userInfo==null)
            throw new UsernameNotFoundException("账号不存在!");
        return new LoginUserDetails(userInfo,null);
    }
}
