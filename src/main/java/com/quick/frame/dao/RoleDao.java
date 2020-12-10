package com.quick.frame.dao;

import com.quick.frame.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zengyu
 * @since 2020-12-08
 */
public interface RoleDao extends BaseMapper<Role> {
    /**
     * 获取指定账号的所有角色信息
     * @param accountNumber -账号
     * @return -角色信息列表
     */
    List<Role> getRoleListInAccountNumber(String accountNumber);

}
