package com.quick.frame.commons.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: Jwt拦截器
 * @author: znegyu
 * @create: 2020-11-28 09:06
 **/
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 调用时间：Controller方法处理之前
     * @param request -request
     * @param response -response
     * @param handler -handler
     * @return 若返回 false，则中断执行，注意：不会进入afterCompletion
     * @throws Exception -Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(token==null||token.trim().equals(""))
        if(true)
        throw new ServiceException(1003,"操作失败!请先登录!");
        return true;
    }

    /**
     * Controller方法处理完之后，DispatcherServlet进行视图的渲染之前，条件是 preHandle返回true 也就是说在这个方法中你可以对ModelAndView进行操作(前后端分离的项目不需要)
     * @param request -request
     * @param response -response
     * @param handler -handler
     * @param modelAndView - modelAndView
     * @throws Exception  -Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * DispatcherServlet进行视图的渲染之后执行 条件是 preHandle返回true(前后端分离的项目不需要)
     * @param request -request
     * @param response -response
     * @param handler -handler
     * @param ex -ex
     * @throws Exception -Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
