package com.quick.frame.service.impl;

import com.quick.frame.commons.other.ServiceException;
import com.quick.frame.commons.result.ResponseTip;
import com.quick.frame.commons.util.JwtUtil;
import com.quick.frame.config.security.LoginBean;
import com.quick.frame.entity.UserInfo;
import com.quick.frame.dao.UserInfoDao;
import com.quick.frame.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zengyu
 * @since 2020-12-08
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfo> implements IUserInfoService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private JwtUtil jwtUtil;
    @Override
    public ResponseTip<String> authentication(LoginBean loginBean) {
        //先判断请求参数是否为正确
        if(loginBean==null)
            throw new ServiceException(1000,"登录参数不能为空!");
        if(loginBean.getAccountNumber()==null)
            throw new ServiceException(1000,"账号不能为空!");
        if(loginBean.getPassword()==null)
            throw new ServiceException(1000,"密码不能为空!");
        //通过账号密码构建令牌
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(loginBean.getAccountNumber(),loginBean.getPassword());
        //security通过UserDetails进行认证
        authenticationManager.authenticate(authenticationToken);
        //无异常,证明认证成功,创建令牌给前端
        String token= jwtUtil.createToken(loginBean.getAccountNumber());
        ResponseTip<String> stringResponseTip = new ResponseTip<>(200, "登录认证成功!", token);
        return stringResponseTip;
    }
}
