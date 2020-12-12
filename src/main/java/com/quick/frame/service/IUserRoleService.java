package com.quick.frame.service;

import com.quick.frame.commons.result.Tip;
import com.quick.frame.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cache.annotation.CacheEvict;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengyu
 * @since 2020-12-08
 */
public interface IUserRoleService extends IService<UserRole> {

    /**
     * 给指定用户添加角色
     * @param userRole -用户角色关联实体类
     * @return -成功返回 true 失败返回 false
     */
    Tip addRoleToUser(UserRole userRole,String accountNumber);
}
