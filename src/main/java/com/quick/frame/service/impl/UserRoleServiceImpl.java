package com.quick.frame.service.impl;

import com.quick.frame.commons.other.ServiceException;
import com.quick.frame.commons.result.Tip;
import com.quick.frame.commons.util.JwtUtil;
import com.quick.frame.entity.UserRole;
import com.quick.frame.dao.UserRoleDao;
import com.quick.frame.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zengyu
 * @since 2020-12-08
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements IUserRoleService {
    @Resource
    private UserRoleDao userRoleDao;
    @Resource
    private JwtUtil jwtUtil;

    @Override
    @CacheEvict(value = "UserDetailsCache", key = "#accountNumber")
    public Tip addRoleToUser(UserRole userRole,String accountNumber) {
        if(userRole==null)
            throw new ServiceException(1000,"不能添加空角色!");
        if(userRole.getUserId()==null || userRole.getUserId().trim().equals(""))
            throw new ServiceException(1000,"不能给空用户添加角色!");
        if(userRole.getRoleId()==null || userRole.getRoleId().trim().equals(""))
            throw new ServiceException(1000,"不能给用户添加空角色!");
        if(userRoleDao.insert(userRole)==0)
            throw new ServiceException(1000,"添加角色失败!");
        return new Tip(200,"添加角色成功!");
    }
}
