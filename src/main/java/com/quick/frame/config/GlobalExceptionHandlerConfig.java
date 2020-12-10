package com.quick.frame.config;

import com.quick.frame.commons.other.ServiceException;
import com.quick.frame.commons.result.Tip;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 全局异常拦截配置
 * @author: znegyu
 * @create: 2020-11-28 10:00
 **/
@ControllerAdvice
public class GlobalExceptionHandlerConfig {

    /**
     * 优先拦截自定义的业务异常,这样可以给前端更友好的错误提示
     * @param request -request
     * @param response -response
     * @param e -抛出的业务异常
     * @return -状态码和业务异常消息的Json字符串提示
     */
    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public Tip serviceExceptionHandler(HttpServletRequest request, HttpServletResponse response, ServiceException e){
        return new Tip(e.getCode(),e.getMessage());
    }

    /**
     * 没被业务异常拦截,可以在这里进行拦截
     * @param request -request
     * @param response -response
     * @param e -抛出的系统异常
     * @return -状态码(500)和业务异常消息的Json字符串提示
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public Tip exceptionHandler(HttpServletRequest request,HttpServletResponse response, Exception e){
        return new Tip(500,e.getMessage());
    }
}
