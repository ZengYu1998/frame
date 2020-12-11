package com.quick.frame.commons.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.AuthenticationException;

/**
 * <pre>
 * 对象功能 自定义认证异常类
 * 开发人员：曾煜
 * 创建时间：2020/12/11 22:38
 * </pre>
 **/
@Data
public class DefaultAuthenticationException extends AuthenticationException {
    private Integer code;

    public DefaultAuthenticationException(Integer code,String message) {
        super(message);
        this.code=code;
    }
}
