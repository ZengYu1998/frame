package com.quick.frame.config;

import com.quick.frame.config.filter.JwtFilter;
import com.quick.frame.config.filter.ExceptionHandlerFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @description: SpringSecurity配置类
 * @author: znegyu
 * @create: 2020-12-08 09:12
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] AUTH_LIST = {
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/doc.html",
            "/druid/**",
    };

    @Resource
    private JwtFilter jwtFilter;
    @Resource
    private ExceptionHandlerFilter exceptionHandlerFilter;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            //添加jwt过滤器
            .addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class)
            //添加异常铺抓Failure为首个Failure,这样才能捕获所有的Failure链里的异常
            .addFilterBefore(exceptionHandlerFilter,jwtFilter.getClass())
            .csrf().disable()
            //所有请求都要认证
            .authorizeRequests()
            //放开登录接口
            .antMatchers("/api/userInfo/login").permitAll()
            .antMatchers(AUTH_LIST).permitAll()
            //自定义访问规则
            .anyRequest().access("@rbacService.hasPermission(request,authentication)")
            //去除session
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
