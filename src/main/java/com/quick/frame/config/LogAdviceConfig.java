package com.quick.frame.config;

import com.alibaba.fastjson.JSONObject;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @description: 日志切面配置
 * @author: znegyu
 * @create: 2020-12-14 08:30
 **/
@Aspect
@Component
@Slf4j
public class LogAdviceConfig {
    private long beforeTime;

    @Resource
    private HttpServletRequest request;

    /**
     * 定义切点路径
     */
    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.quick.frame.controller.*.*(..))";

    @Before(AOP_POINTCUT_EXPRESSION)
    public void doBefore(JoinPoint joinPoint) {
        //开始计时
        beforeTime = new Date().getTime();
    }


    @AfterReturning(returning = "ret", pointcut = AOP_POINTCUT_EXPRESSION)
    public void doAfterReturning(Object ret) {
        JSONObject parse = (JSONObject) JSONObject.toJSON(ret);
        Integer code = (Integer) parse.get("code");
        StringBuilder info = getRequestRecordInfo(code);
        info.append("[")
                .append(System.currentTimeMillis() - beforeTime)
                .append("ms]");
        //处理完请求后，返回内容
        log.info(info.toString());
    }

    /**
     * @description 请求记录写入日志
     * @author yangxianghua  2020年10月30日 上午09:34:09 周五
     */
    public StringBuilder getRequestRecordInfo(Integer statusCode) {
        //打印请求的内容
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));//获取请求头中的User-Agent
        StringBuilder info = new StringBuilder();
        info.append("【")
                .append(request.getMethod())
                .append("】  ")
                .append("=== ")
                .append(request.getRemoteAddr())
                .append(" ===  ")
                .append(statusCode)
                .append("  ")
                .append(request.getRequestURI())
                .append("  ")
                .append(userAgent.getBrowser().toString())
                .append("  ")
                .append(userAgent.getBrowserVersion())
                .append("  ")
                .append(userAgent.getOperatingSystem().toString())
                .append("  ");
        return info;
    }
}
