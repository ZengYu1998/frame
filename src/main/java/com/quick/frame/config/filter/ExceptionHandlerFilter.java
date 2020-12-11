package com.quick.frame.config.filter;

import com.quick.frame.commons.other.DefaultAuthenticationException;
import com.quick.frame.commons.result.Tip;
import com.quick.frame.commons.util.ServletUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <pre>
 * 对象功能 - Filter异常铺抓
 * 开发人员：曾煜
 * 创建时间：2020/12/11 23:09
 * </pre>
 **/
@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            if(e instanceof DefaultAuthenticationException)
                ServletUtils.render(request,response,new Tip(1000,e.getMessage()));
            else
                ServletUtils.render(request,response,new Tip(500,e.getMessage()));
        }
    }
}
