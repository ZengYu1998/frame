package com.quick.frame.config.filter;

import com.quick.frame.commons.other.ServiceException;
import com.quick.frame.commons.result.Tip;
import com.quick.frame.commons.util.JwtUtil;
import com.quick.frame.commons.util.ServletUtils;
import com.quick.frame.config.security.DefaultUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @description: JWT请求过滤器
 * @author: znegyu
 * @create: 2020-12-11 10:11
 **/
@Component
public class JwtFilter extends OncePerRequestFilter {
    //继承OncePerRequestFilter来保证一次请求只通过一次filter，而不需要重复执行(为了兼容不同的web container)

    @Resource
    private JwtUtil jwtUtil;
    @Resource
    private DefaultUserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //先获取请求头中的token
        String jwtToken=request.getHeader("jwtToken");
        if(jwtToken!=null && !jwtToken.trim().equals("")){
            //throw new ServiceException(1000,"您尚未登录!请先登录!");
            //校验token的正确性并获取用户唯一标识符(这里的唯一标识符就是用户的账号)
            String accountNumber= "";
            Map<String, Object> verify = jwtUtil.verify(jwtToken,true);
            if((boolean)verify.get("success")) accountNumber=verify.get("data").toString();
            else
                ServletUtils.render(request,response,new Tip(1000,verify.get("data").toString()));
            //SecurityContextHolder 中保存的是当前访问者的信息
            //登录用户没有认证时，先进行认证
            if(SecurityContextHolder.getContext().getAuthentication()==null){
                //获取当前用户的信息
                UserDetails userDetails = userDetailsService.loadUserByUsername(accountNumber);
                //授权
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request,response);
    }
}
