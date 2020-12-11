package com.quick.frame.commons.util;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.quick.frame.commons.other.DefaultAuthenticationException;
import com.quick.frame.commons.other.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @description: Jwt工具类
 * @author: znegyu
 * @create: 2020-11-23 15:34
 **/
@Component
public class JwtUtil {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    //过期时间
    @Value("${project.jwt.EXPIRE_TIME}")
    private Long EXPIRE_TIME;

    //加密密钥
    @Value("${project.jwt.SECRET}")
    private String SECRET;

    //发行人
    @Value("${project.jwt.ISSUER}")
    private String ISSUER;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;


    /**
     * 创建加密Token
     * @param uniqueIdentifier -唯一用户标识符
     * @return  -加密后的Token
     * @throws UnsupportedEncodingException -编码异常
     */
    public String createToken(String uniqueIdentifier){
        if(uniqueIdentifier==null || uniqueIdentifier.trim().equals("")){
            throw new ServiceException(1000,"无法获取用户唯一标识信息!");
        }
        Algorithm algorithm = getAlgorithm();
        //创建和签名令牌的Token构建器
        String token = JWT.create()
                .withIssuer(ISSUER) //设置发布者
                //添加自定义内容 (这里为添加userId)
                .withClaim("uniqueIdentifier", uniqueIdentifier)
                .withIssuedAt(new Date()) //生成时间
                //到期时间
                //.withExpiresAt(date) //这里不设置到期,通过Redis来判断是否到期
                //指定算法进行签名
                .sign(algorithm);
        //将Token放在Redis上,并设置到期时间
        redisTemplate.opsForValue().set(uniqueIdentifier,token,EXPIRE_TIME, TimeUnit.HOURS);
        return token;
    }


    /**
     * 校验token
     * @param token  要校验的Token
     * @param isFilter  是否是在Filter中认证 true-是 false-否
     * @return -校验成功 返回用户唯一标识符 否则抛出异常
     */
    /**
     * 校验token
     * @param token 要校验的Token
     * @return -校验成功时 返回用户唯一标识符
     */
    public String verify(String token){
            Algorithm algorithm = getAlgorithm();
            //创建带有用于验证令牌签名的算法的构建器
            JWTVerifier verifier = JWT.require(algorithm) //设置签名
                    .build();
            //验证 token

        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new DefaultAuthenticationException(1000,"token不正確!");
        }
        //验证通过后获取用户唯一标识符
            String uniqueIdentifier= jwt.getClaims().get("uniqueIdentifier").asString();
            if(uniqueIdentifier==null || uniqueIdentifier.trim().equals("")){
                throw new DefaultAuthenticationException(1000,"token验证失败!无法获取用户唯一标识符!");
            }
            //通过redis判断是否过期
            String tokenRedis = (String) redisTemplate.opsForValue().get(uniqueIdentifier);
            if(tokenRedis==null){
                throw new DefaultAuthenticationException(1000,"登录失效,请重新登录!");//token过期
            }
            if(!token.equals(tokenRedis)){
                throw new DefaultAuthenticationException(1000,"账号在其他地方登录!请尝试更改密码或重新登录!");
            }
            return uniqueIdentifier;
    }

    /**
     * 获取签名
     * @return 签名对象
     */
    private Algorithm getAlgorithm(){
        Algorithm algorithm = null; //使用HMAC256算法生成签名
        try {
            algorithm = Algorithm.HMAC256(SECRET);
            return  algorithm;
        } catch (UnsupportedEncodingException e) {
            throw new DefaultAuthenticationException(1000,"签名生成失败("+e.getMessage()+")");
        }
    }
}
