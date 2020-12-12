package com.quick.frame.service;

import com.quick.frame.commons.result.ResponseTip;
import com.quick.frame.commons.result.Tip;
import com.quick.frame.config.security.LoginBean;
import com.quick.frame.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengyu
 * @since 2020-12-08
 */
public interface IUserInfoService extends IService<UserInfo> {

    ResponseTip<String> authentication(LoginBean loginBean);


    Tip addUser();

    //@Transactional
    Tip asdfadas();


}
