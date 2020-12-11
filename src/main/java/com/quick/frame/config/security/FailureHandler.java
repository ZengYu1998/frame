package com.quick.frame.config.security;

import com.quick.frame.commons.result.Tip;
import com.quick.frame.commons.util.ServletUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 认证失败处理类
 * @author: znegyu
 * @create: 2020-12-11 15:57
 **/
@Component
public class FailureHandler implements AuthenticationEntryPoint,AccessDeniedHandler   {

    /**
     * 认证失败处理方法(因为是在filter中出现的异常,全局拦截器拦截不到,filter前链在拦截器前执行)
     * @param request -request 请求
     * @param response -response 响应
     * @param e -异常
     * @throws IOException -IOException
     * @throws ServletException -ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        ServletUtils.render(request,response,new Tip(403,e.getMessage()));
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        ServletUtils.render(request,response,new Tip(405,e.getMessage()));
    }
}
